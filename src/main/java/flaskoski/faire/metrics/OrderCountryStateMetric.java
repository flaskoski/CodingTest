package flaskoski.faire.metrics;

import flaskoski.faire.model.Order;
import flaskoski.faire.model.OrderState;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OrderCountryStateMetric implements OrderMetric {
    @Override
    public Map.Entry<Order, Integer> process(int resultOrder, Map<String, Order> orderMap) {
        Map<String, Integer> orderCountryStateCounter = new HashMap<>();

        int answerCommonOrderCountryStateOccurrences = (resultOrder == OrderMetric.HIGHEST? 0 : Integer.MAX_VALUE);;
        Order answerCommonCountryStateOrder = null;

        for(Order order : orderMap.values()){
            if(! orderCountryStateCounter.containsKey(order.getAddress().getState()))
                orderCountryStateCounter.put(order.getAddress().getState(), 1);
            else
                orderCountryStateCounter.put(order.getState(), (orderCountryStateCounter.get(order.getAddress().getState())+1));
            if(resultOrder == OrderMetric.HIGHEST) {
                if (orderCountryStateCounter.get(order.getAddress().getState()) > answerCommonOrderCountryStateOccurrences) {
                    answerCommonOrderCountryStateOccurrences = orderCountryStateCounter.get(order.getAddress().getState());
                    answerCommonCountryStateOrder = order;
                }
            }
            else if (orderCountryStateCounter.get(order.getAddress().getState()) < answerCommonOrderCountryStateOccurrences) {
                answerCommonOrderCountryStateOccurrences = orderCountryStateCounter.get(order.getAddress().getState());
                answerCommonCountryStateOrder = order;
            }
        }
        return new HashMap.SimpleEntry<>(answerCommonCountryStateOrder, answerCommonOrderCountryStateOccurrences);
    }
}
