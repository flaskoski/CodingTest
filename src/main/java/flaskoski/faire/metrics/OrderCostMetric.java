package flaskoski.faire.metrics;

import flaskoski.faire.model.Order;

import java.util.Map;

public class OrderCostMetric implements OrderMetric {
    @Override
    public Map.Entry<Order, Integer> process(int resultOrder, Map<String, Order> orderMap) {
        return null;
    }
}
