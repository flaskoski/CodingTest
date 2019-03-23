package flaskoski.faire;

import flaskoski.faire.apicommunication.*;
import flaskoski.faire.model.Option;
import flaskoski.faire.model.Order;
import flaskoski.faire.model.Product;

import java.util.ArrayList;
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
        //------1. Consumes all products for a given brand*2
        ApiComms productsReader = new ProductApiComms(apiKeyHeader);
        List<Product> products = ((ProductApiComms) productsReader).getItemsByBrand("b_d2481b88");
        if(products != null && products.size()>0)
            System.out.println("Products from brand b_d2481b88 obtained.");

        //------2. recording the inventory levels for each product option
        List<Option> optionItems = new ArrayList<>();

        OptionApiComms optionApiComms = new OptionApiComms(apiKeyHeader);
        optionApiComms.recordInventory(products);

        //------Consumes all orders
        List<Order> orderList = new OrderApiComms(apiKeyHeader).getAllItems();

        //------accepting the order if there is inventory to fulfill the order otherwise it marks the items that don’t have enough inventory as backordered
        //Update the inventory levels of product options as each order is moved to processing
        for(Order order : orderList){
            Boolean processed = order.processOrder(optionApiComms, new OrderApiComms(apiKeyHeader));
            System.out.println(String.format("order %s processed? %s", order.getId(), processed.toString()));
        }
    }
}
