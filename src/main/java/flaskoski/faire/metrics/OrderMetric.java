package flaskoski.faire.metrics;

import flaskoski.faire.model.Order;

import java.util.List;

public interface OrderMetric {
    <T> T process(List<Order> orderList);
}
