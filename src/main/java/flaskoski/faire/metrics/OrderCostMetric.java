package flaskoski.faire.metrics;

import flaskoski.faire.model.Order;
import flaskoski.faire.model.OrderItem;

import java.util.HashMap;
import java.util.Map;

public class OrderCostMetric implements OrderMetric {
    @Override
    public Map.Entry<Order, Integer> process(int resultOrder, Map<String, Order> orderMap) {
        //For checking the most valuable order
        Order answerValuableOrder = null;
        Integer answerOrderValue =(resultOrder == OrderMetric.HIGHEST? 0 : Integer.MAX_VALUE);
        Integer orderValue;

        for(Order order : orderMap.values()){
            Integer counter;

            orderValue=0;
            //For each order item
            for(OrderItem item : order.getItems()) {
                orderValue += item.getPrice_cents()*item.getQuantity();
            }
            if(resultOrder == OrderMetric.HIGHEST) {
                if (orderValue > answerOrderValue) {
                    answerValuableOrder = order;
                    answerOrderValue = orderValue;
                }
            }else
                if(orderValue < answerOrderValue) {
                    answerValuableOrder = order;
                    answerOrderValue = orderValue;
                }
        }
        return new HashMap.SimpleEntry<>(answerValuableOrder, answerOrderValue);
    }
}
