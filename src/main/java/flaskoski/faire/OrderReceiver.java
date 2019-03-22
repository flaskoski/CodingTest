package flaskoski.faire;

import flaskoski.faire.DAO.OrderItemDAO;
import flaskoski.faire.model.Order;

public class OrderReceiver {
    public static final String dbSchema = "fairedb";

    public Boolean processOrder(Order order){
        OrderItemDAO dbItems = new OrderItemDAO();
        dbItems.checkIfItemsAvailable(order.getItems());
        return true;
    }

}
