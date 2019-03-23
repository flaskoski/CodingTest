package flaskoski.faire.metrics;

import flaskoski.faire.model.Order;
import flaskoski.faire.model.OrderState;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OrderStateMetric implements OrderMetric {
    @Override
    public Map.Entry<Order, Integer> process(int resultOrder, Map<String, Order> orderMap) {
        Map<String, Integer> orderStateCounter = new HashMap<>();
        Arrays.asList(OrderState.values()).forEach(s -> orderStateCounter.put(s.name(), 0));
        int answerCommonOrderStateOccurencies = (resultOrder == OrderMetric.HIGHEST? 0 : Integer.MAX_VALUE);;
        Order answerCommonStateOrder = null;
        for(Order order : orderMap.values()){
            orderStateCounter.put(order.getState(), (orderStateCounter.get(order.getState())+1));
            if(resultOrder == OrderMetric.HIGHEST) {
                if (orderStateCounter.get(order.getState()) > answerCommonOrderStateOccurencies) {
                    answerCommonOrderStateOccurencies = orderStateCounter.get(order.getState());
                    answerCommonStateOrder = order;
                }
            }
            else if (orderStateCounter.get(order.getState()) < answerCommonOrderStateOccurencies) {
                answerCommonOrderStateOccurencies = orderStateCounter.get(order.getState());
                answerCommonStateOrder = order;
            }
        }
        return new HashMap.SimpleEntry<>(answerCommonStateOrder, answerCommonOrderStateOccurencies);
    }
}
