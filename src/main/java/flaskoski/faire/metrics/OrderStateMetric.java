package flaskoski.faire.metrics;

import flaskoski.faire.model.Order;
import flaskoski.faire.model.OrderItem;
import flaskoski.faire.model.OrderState;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OrderStateMetric implements OrderMetric {
    @Override
    public Pair<Order, Integer> process(int resultOrder, Map<String, Order> orderMap) {
        Map<String, Integer> orderStateCounter = new HashMap<>();
        Arrays.asList(OrderState.values()).forEach(s -> orderStateCounter.put(s.name(), 0));
        int mostCommonOrderStateOccurencies = 0;
        Order mostCommonStateOrder = null;
        for(Order order : orderMap.values()){
            orderStateCounter.put(order.getState(), (orderStateCounter.get(order.getState())+1));
            if(orderStateCounter.get(order.getState()) > mostCommonOrderStateOccurencies) {
                mostCommonOrderStateOccurencies = orderStateCounter.get(order.getState());
                mostCommonStateOrder = order;
            }

        }
        return new Pair<Order, Integer>(mostCommonStateOrder, mostCommonOrderStateOccurencies);
    }
}
