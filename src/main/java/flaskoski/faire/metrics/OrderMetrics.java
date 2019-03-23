package flaskoski.faire.metrics;

import flaskoski.faire.model.Order;

import java.util.Map;

public class OrderMetrics {
    private Map<String, Order> orderMap;

    public OrderMetrics(Map<String, Order> orderMap) {
        this.orderMap = orderMap;
    }

    public Map.Entry<Order, Integer> checkOrdethatHas(int resultStrategy, OrderMetric orderMetric) {
        return orderMetric.process(resultStrategy, orderMap);
    }
}
