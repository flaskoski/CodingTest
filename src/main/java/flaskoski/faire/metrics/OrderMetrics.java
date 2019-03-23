package flaskoski.faire.metrics;

import flaskoski.faire.model.Order;
import flaskoski.faire.model.Product;
import javafx.util.Pair;

import java.util.Map;

public class OrderMetrics {
    private Map<String, Order> orderMap;

    public OrderMetrics(Map<String, Order> orderMap) {
        this.orderMap = orderMap;
    }

    public Pair<Order, Integer> checkOrdethatHas(int resultStrategy, OrderMetric orderMetric) {
        return orderMetric.process(resultStrategy, orderMap);
    }
}
