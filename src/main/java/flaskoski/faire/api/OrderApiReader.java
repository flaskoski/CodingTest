package flaskoski.faire.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import flaskoski.faire.OrderReceiver;
import flaskoski.faire.model.Order;
import flaskoski.faire.model.Product;

import java.util.List;

public class OrderApiReader extends AbstractApiReader{
    private String apiKeyHeader;

    OrderApiReader(String apiKeyHeader){
        super(apiKeyHeader);
    }

    @Override
    public List<Order> getAllItems() {
        String ordersString = ApiReader.getTarget().path("/api/v1/orders").request()
                .header("X-FAIRE-ACCESS-TOKEN", apiKeyHeader)
                .get(String.class);

        JsonObject ordersJsonObject = new JsonParser().parse(ordersString).getAsJsonObject();

        OrderReceiver receiver = new OrderReceiver();

        Gson gson = new Gson();
        List<Order> orderList = gson.fromJson(ordersJsonObject.get("orders"), new TypeToken<List<Order>>() {}.getType());
        return orderList;
    }
}
