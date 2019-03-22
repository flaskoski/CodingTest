package flaskoski.faire;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import flaskoski.faire.model.Order;
import flaskoski.faire.model.Product;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.List;

public class Application {

    public static void main(String args[]){
        String apiKeyHeader = "";
        try{
            apiKeyHeader = args[0];
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://www.faire-stage.com");
        String content = target.path("/api/v1/products").request()
                .header("X-FAIRE-ACCESS-TOKEN", apiKeyHeader)
                .get(String.class);

        JsonObject jsonObject = new JsonParser().parse(content).getAsJsonObject();

        Gson gson = new Gson();
        List<Product> productList = gson.fromJson(jsonObject.get("products"), new TypeToken<List<Product>>() {}.getType());
        for(Product product : productList){
            System.out.println(product.getBrand_id());
        }

        List<Order> orderList;
    }
}
