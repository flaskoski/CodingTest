package flaskoski.faire;

import flaskoski.faire.apicommunication.*;
import flaskoski.faire.model.*;
import org.glassfish.jersey.internal.util.collection.KeyComparator;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

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
        Map<String, Product> products = ((ProductApiComms) productsReader).getItemsByBrand("b_d2481b88");
        Map<String, Product> idToProduct = new HashMap<>();

        if(products != null && products.size()>0)
            System.out.println("Products from brand b_d2481b88 obtained.");

        //------2. recording the inventory levels for each product option
        List<Option> optionItems = new ArrayList<>();

        OptionApiComms optionApiComms = new OptionApiComms(apiKeyHeader);
        optionApiComms.recordInventory(products.values());

        //------Consumes all orders
        List<Order> orderList = new OrderApiComms(apiKeyHeader).getAllItems();
        List<Order> processedOrders = new ArrayList<>();//orderList.stream().filter(o -> o.getState().equals(OrderState.PROCESSING.name())).collect(Collectors.toList());
        //------accepting the order if there is inventory to fulfill the order otherwise it marks the items that don’t have enough inventory as backordered
        //Update the inventory levels of product options as each order is moved to processing
        for(Order order : orderList){
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
        Map<String, Integer> orderStateCounter = new HashMap<>();
        Arrays.asList(OrderState.values()).forEach(s -> orderStateCounter.put(s.name(), 0));
        Integer mostCommonOrderStateOccurencies = 0;
        String mostCommonOrderState = "";

        for(Order order : processedOrders){
            Integer counter;
            orderStateCounter.put(order.getState(), orderStateCounter.get(order.getState())+1);
            if(orderStateCounter.get(order.getState()) > mostCommonOrderStateOccurencies) {
                mostCommonOrderStateOccurencies = orderStateCounter.get(order.getState());
                mostCommonOrderState = order.getState();
            }

            orderValue=0;
            //For each order item
            for(OrderItem item : order.getItems()) {
                Option optionProcessed = item.getOptionItemInfo();

                orderValue += products.get(item.getProduct_id()).getRetail_price_cents();

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

        if(mostCommonOrderStateOccurencies > 0)
            System.out.println("The most commom order state is: "+mostCommonOrderState+" with "+ mostCommonOrderStateOccurencies+  " occurencies");
    }
}

