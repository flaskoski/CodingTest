package flaskoski.faire;

import flaskoski.faire.apicommunication.*;
import flaskoski.faire.metrics.*;
import flaskoski.faire.model.*;

import java.net.HttpURLConnection;
import java.util.*;

public class Application {

    public static void main(String args[]) throws Exception {
        String apiKeyHeader = "";
        try{
            apiKeyHeader = args[0];

        }
        catch (Exception e){
            e.printStackTrace();
        }

        //------------------------------------------------------------------------------------------------
        //------1. Consumes all products for a given brand*2
        ApiComms productsReader = new ProductApiComms(apiKeyHeader);
        Map<String, Product> products = ((ProductApiComms) productsReader).getItemsByBrand("b_d2481b88");
        if(products != null && products.size()>0)
            System.out.println("Products from brand b_d2481b88 obtained!");
        else throw new Exception("Products from brand b_d2481b88 obtained!");

        //------------------------------------------------------------------------------------------------
        //------2. recording the inventory levels for each product option---------------------------------
        List<Option> optionItems = new ArrayList<>();
        //bo_bv39h1pcid
        OptionApiComms optionApiComms = new OptionApiComms(apiKeyHeader);
        int responseCode = optionApiComms.recordInventory(products.values()).getStatus();
        if (responseCode == HttpURLConnection.HTTP_OK)
            System.out.println("Inventory updated!");
        //else throw new Exception("Inventory could not be updated! HTTP "+ responseCode);

        //------------------------------------------------------------------------------------------------
        //------3. Get all orders-------------------------------------------------------------------------
        Map<String, Order> orderList = new OrderApiComms(apiKeyHeader).getAllItems();
        if(orderList.size() > 0)
            System.out.println("Orders obtained!");
        else throw new Exception("Orders could not be obtained!");
        List<Order> processedOrders = new ArrayList<>();

        //------------------------------------------------------------------------------------------------
        //------4. accepting the order if there is inventory to fulfill the order otherwise it marks the items that don’t have enough inventory as backordered
        //Update the inventory levels of product options as each order is moved to processing
        for(Order order : orderList.values()) {
            int processed = order.processOrder(optionApiComms, new OrderApiComms(apiKeyHeader));
            if (processed == Order.PROCESSED){
                processedOrders.add(order);
                System.out.println(String.format("order \"%s\" processed.", order.getId()));
            }
            else
                System.out.println(String.format("order \"%s\" not processed because %s",
                        order.getId(),
                        (processed==1? "item not available": "order state is not \"NEW\"")));
        }


        //------------------------------------------------------------------------------------------------
        //-----5. METRICS------------------------------------------------------------------------------------

        //a. Check the best selling product
        OrderMetrics orderMetrics = new OrderMetrics(orderList);

        Map.Entry<Option, Integer> bestSellingOption = orderMetrics.checkOptionThatHas(new OrderBestSellingProductMetric());
        if(bestSellingOption != null)
            System.out.println("There were "+ processedOrders.size()
                    +" orders processed.\nThe most selling product is "
                    + bestSellingOption.getKey().getId()+" with "+ bestSellingOption.getValue() + " items.");


        //b. Check the most valuable order
        Map.Entry<Order, Integer> mostValuableOrder = orderMetrics.checkOrdethatHas(OrderMetric.HIGHEST, new OrderCostMetric());
        if(mostValuableOrder != null)
            System.out.println("The most common state is: "+ mostValuableOrder.getKey().getId() +" with "+ mostValuableOrder.getValue()/100.0 +  " dollars.");

        //c. Check the most common order state
        Map.Entry<Order, Integer> orderStateMostCommom = orderMetrics.checkOrdethatHas(OrderMetric.HIGHEST, new OrderStateMetric());
        if(orderStateMostCommom != null)
            System.out.println("The most commom order state is: "+ orderStateMostCommom.getKey().getState() +" with "+ orderStateMostCommom.getValue() +  " occurencies");

//        //c. Check the most common order country state
//        Map.Entry<Order, Integer> orderStateMostCommom = orderMetrics.checkOrdethatHas(OrderMetric.HIGHEST, new OrderStateMetric());
//        if(orderStateMostCommom != null)
//            System.out.println("The most commom order state is: "+ orderStateMostCommom.getKey().getState() +" with "+ orderStateMostCommom.getValue() +  " occurencies");

    }
}

