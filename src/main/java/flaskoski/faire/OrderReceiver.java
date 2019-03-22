package flaskoski.faire;

import flaskoski.faire.model.Order;
import flaskoski.faire.model.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class OrderReceiver {
    public static final String dbSchema = "fairedb";

    public Boolean processOrder(Order order){
        OrderItemDAO dbItems = new OrderItemDAO();
        dbItems.checkIfItemsAvailable(order.getItems());
        return true;
    }

}
