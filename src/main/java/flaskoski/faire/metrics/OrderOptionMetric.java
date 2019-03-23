package flaskoski.faire.metrics;

import flaskoski.faire.model.Option;
import flaskoski.faire.model.Order;

import java.util.Map;

import static flaskoski.faire.metrics.OrderMetric.HIGHEST;

public interface OrderOptionMetric {
    Map.Entry<Option, Integer>  process(Map<String, Order> orderMap);
}
