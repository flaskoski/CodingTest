package flaskoski.faire.apicommunication;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import flaskoski.faire.model.Order;
import flaskoski.faire.model.OrderState;
import flaskoski.faire.model.Product;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

public class OrderApiComms extends AbstractApiComms implements ApiReader{
    public OrderApiComms(String apiKeyHeader){
        super(apiKeyHeader);
    }

    @Override
    public Map<String, Order> getAllItems() {
        WebTarget target = ApiComms.getTarget();
        Map<String, Order> ordersOfPage;
        Map<String, Order> ordersMap = new HashMap<>();
        Integer page = 1;
        Integer limit=50;
        do
        {
            String content = target.path("/orders")
                    .queryParam("page", page++)
                    .queryParam("limit", limit).request()
                    .header(headerId, this.apiKeyHeader)
                    .get(String.class);

            ordersOfPage = getItensOfOnePage(content);
            ordersMap.putAll(ordersOfPage);

        }while (ordersOfPage.size() == limit);

        return ordersMap;

    }

    @Override
    public Map getItensOfOnePage(String content) {
        JsonObject jsonObject = new JsonParser().parse(content).getAsJsonObject();

        List<Order> ordersOfPage = gson.fromJson(jsonObject.get("orders"), new TypeToken<List<Order>>() {}.getType());
        Map<String, Order> ordersMap = new HashMap<>();
        ordersOfPage.stream().forEach(o -> ordersMap.put(o.getId(), o));
        if (ordersOfPage == null)
            return new HashMap();
        return ordersMap;
    }

    public void process(Order order) {
        if(order.getId() == null) throw new NullPointerException("There is no order to process");

        order.setState(OrderState.PROCESSING.name());

        Invocation.Builder invocationBuilder = ApiComms.getTarget()
                .path("/orders/"+order.getId()+"/processing")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("X-FAIRE-ACCESS-TOKEN", apiKeyHeader);
        Response response = invocationBuilder.put(Entity.entity(gson.toJson(order.getID()), MediaType.APPLICATION_JSON_TYPE));
//        String orderS = response.readEntity(String.class);
    }



}
