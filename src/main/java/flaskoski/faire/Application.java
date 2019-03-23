package flaskoski.faire;

import flaskoski.faire.apicommunication.*;
import flaskoski.faire.metrics.OrderCostMetric;
import flaskoski.faire.metrics.OrderMetric;
import flaskoski.faire.metrics.OrderMetrics;
import flaskoski.faire.model.*;
import javafx.util.Pair;

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
        for(Order order : orderList.values()){
            Boolean processed = order.processOrder(optionApiComms, new OrderApiComms(apiKeyHeader));
            if(processed)
                processedOrders.add(order);
            System.out.println(String.format("order %s processed? %s", order.getId(), processed.toString()));
        }


        //------------------------------------------------------------------------------------------------
        //-----METRICS------------------------------------------------------------------------------------

        //For checking the best selling product
        List<Option> optionsSold = new ArrayList<>();

        //For checking the most valuable order
        Order mostValuableOrder = null;
        Integer mostValuableOrderValue =-1, orderValue=0;

        //For checking the state which is the most common in all orders
        OrderMetrics orderMetrics = new OrderMetrics(orderList);
        Pair<Order, Integer> orderStateMostCommom = orderMetrics.checkOrdethatHas(OrderMetric.HIGHEST, new OrderCostMetric());
        if(orderStateMostCommom != null)
            System.out.println("The most commom order state is: "+ orderStateMostCommom.getKey().getState() +" with "+ orderStateMostCommom.getValue() +  " occurencies");
        
        for(Order order : processedOrders){
            Integer counter;

            orderValue=0;
            //For each order item
            for(OrderItem item : order.getItems()) {
                Option optionProcessed = item.getOptionItemInfo();

                orderValue += item.getPrice_cents()*item.getQuantity();

                counter = 0;
                for (Option optionAdded : optionsSold) {
                    if (optionAdded.getId().equals(optionProcessed.getId())) {
                        optionAdded.setAvailable_quantity(optionAdded.getAvailable_quantity() + optionProcessed.getAvailable_quantity());
                        break;
                    }
                    counter++;
                }
                if(optionsSold.size() == counter)
                    optionsSold.add(optionProcessed);
            }
            if(orderValue > mostValuableOrderValue) {
                mostValuableOrder = order;
                mostValuableOrderValue = orderValue;
            }
        }
        optionsSold.sort(Comparator.comparing(Option::getAvailable_quantity).reversed());

        if(!optionsSold.isEmpty()) {
            Option bestSelling = optionsSold.get(0);
            System.out.println("There were "+ processedOrders.size()
                    +" orders processed.\nThe most selling product is "
                    + bestSelling.getId()+" with "+ bestSelling.getAvailable_quantity() + " items.");
        }
        else System.out.println("No order was processed!");

        if(mostValuableOrder != null)
            System.out.println("The most valuable order is " + mostValuableOrder.getId() + " and cost: "+ mostValuableOrderValue);
    }
}

