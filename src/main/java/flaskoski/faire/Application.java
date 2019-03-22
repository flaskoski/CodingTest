package flaskoski.faire;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import flaskoski.faire.api.ApiReader;
import flaskoski.faire.api.ProductApiReader;
import flaskoski.faire.model.Order;
import flaskoski.faire.model.Product;
import flaskoski.faire.model.Shipment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.File;
import java.util.List;

public class Application {

    public static void main(String args[]){
        String apiKeyHeader = "";
        String dbSchema = "fairedb";
        try{
            apiKeyHeader = args[0];

        }
        catch (Exception e){
            e.printStackTrace();
        }

        ItemDAO db = new ItemDAO();

        ApiReader productsReader = new ProductApiReader(apiKeyHeader);

        List<Product> products = ((ProductApiReader) productsReader).getItemsByBrand("b_d2481b88");
       // db.addAll(products);
        for(Product product : products){
            System.out.println(product.getBrand_id());
        }

//        String ordersString = target.path("/api/v1/orders").request()
//                .header("X-FAIRE-ACCESS-TOKEN", apiKeyHeader)
//                .get(String.class);
//
//        JsonObject ordersJsonObject = new JsonParser().parse(ordersString).getAsJsonObject();
//
//        OrderReceiver receiver = new OrderReceiver();
//
//        List<Order> orderList = gson.fromJson(ordersJsonObject.get("orders"), new TypeToken<List<Order>>() {}.getType());
//        for(Order order : orderList){
//            System.out.println(order.getAddress().getCity());
//            receiver.processOrder(order);
//        }
    }
}
