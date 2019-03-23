package flaskoski.faire.metrics;

import flaskoski.faire.model.Order;

import java.util.Map;

public interface OrderMetric {
    public static final int HIGHEST = 1;
    public static final int LOWEST = 0;

    default Map.Entry<Order, Integer>  process(Map<String, Order> orderMap) {
        return process(HIGHEST, orderMap);
    }

    Map.Entry<Order, Integer> process(int resultOrder, Map<String, Order> orderMap);
}
