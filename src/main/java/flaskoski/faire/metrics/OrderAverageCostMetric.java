package flaskoski.faire.metrics;

import flaskoski.faire.model.Order;
import flaskoski.faire.model.OrderItem;

import java.util.HashMap;
import java.util.Map;

public class OrderAverageCostMetric{

    public Integer process(Map<String, Order> orderMap) {
        //For checking the most valuable order
        Integer avgValue = 0;
        Integer orderValue;

        for(Order order : orderMap.values()){
            Integer counter;

            orderValue=0;
            //For each order item
            for(OrderItem item : order.getItems()) {
                orderValue += item.getPrice_cents()*item.getQuantity();
            }
            avgValue += orderValue;
        }
        avgValue /= orderMap.size();
        return avgValue;
    }
}
