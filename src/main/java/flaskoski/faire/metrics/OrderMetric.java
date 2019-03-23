package flaskoski.faire.metrics;

import flaskoski.faire.model.Order;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public interface OrderMetric {
    public static final int HIGHEST = 1;
    public static final int LOWEST = 0;

    default Pair<Order, Integer>  process(Map<String, Order> orderMap) {
        return process(HIGHEST, orderMap);
    }

    Pair<Order, Integer> process(int resultOrder, Map<String, Order> orderMap);
}
