package flaskoski.faire.apicommunication;

import com.google.gson.Gson;
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
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderApiComms extends AbstractApiComms implements ApiReader{
    public OrderApiComms(String apiKeyHeader){
        super(apiKeyHeader);
    }

    @Override
    public List<Order> getAllItems() {
        WebTarget target = ApiComms.getTarget();
        List<Order> ordersOfPage;
        List<Order> orders = new ArrayList<>();
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
            orders.addAll(ordersOfPage);

        }while (ordersOfPage.size() == limit);

        return orders;

    }

    @Override
    public List getItensOfOnePage(String content) {
        JsonObject jsonObject = new JsonParser().parse(content).getAsJsonObject();

        List<Product> ordersOfPage = gson.fromJson(jsonObject.get("orders"), new TypeToken<List<Order>>() {
        }.getType());
        if (ordersOfPage == null)
            return Collections.EMPTY_LIST;
        return ordersOfPage;
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
