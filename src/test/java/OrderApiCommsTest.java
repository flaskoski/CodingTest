import flaskoski.faire.apicommunication.OrderApiComms;
import flaskoski.faire.model.Order;
import flaskoski.faire.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class OrderApiCommsTest implements  ApiCommsTest{

    private OrderApiComms orderApiComms;

    @Before
    public void setup(){
        orderApiComms = new OrderApiComms(apiKey);
    }

    @Test
    public void getAllItemsNonNull(){
        Map<String, Order> orderList = orderApiComms.getAllItems();
        assertTrue(orderList.size() > 50);
        for(Order o : orderList.values()){
            assertNotNull(o.getId());
        }
    }

    //TODO
    @Test
    public void addToInventory(){
        List<Product> products = new ArrayList<>();
    }
}
