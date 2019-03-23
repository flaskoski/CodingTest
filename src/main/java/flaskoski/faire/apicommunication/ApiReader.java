package flaskoski.faire.apicommunication;

import flaskoski.faire.model.Order;

import java.util.Map;

public interface ApiReader {
    Map<String, Order> getAllItems();
    Map getItensOfOnePage(String content);
}
