package flaskoski.faire;

import flaskoski.faire.DAO.ItemDAO;
import flaskoski.faire.api.ApiReader;
import flaskoski.faire.api.ProductApiReader;
import flaskoski.faire.model.Product;

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
//
//
//        List<Order> orderList = gson.fromJson(ordersJsonObject.get("orders"), new TypeToken<List<Order>>() {}.getType());
//        for(Order order : orderList){
//            System.out.println(order.getAddress().getCity());
//            receiver.processOrder(order);
//        }
    }
}
