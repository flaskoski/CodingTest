import flaskoski.faire.apicommunication.OptionApiComms;
import flaskoski.faire.apicommunication.OrderApiComms;
import flaskoski.faire.apicommunication.UpdateOptionStrategy.UpdateOptionDontProcess;
import flaskoski.faire.apicommunication.UpdateOptionStrategy.UpdateOptionProcess;
import flaskoski.faire.model.Order;
import flaskoski.faire.model.OrderItem;
import flaskoski.faire.model.OrderState;
import org.junit.Before;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class OrderTest {

    Order order;

    @Before
    public void setup(){
        order = new Order();
        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem("oi_111", "po_1abc", 5));
        items.add(new OrderItem("oi_111", "po_2abc", 5));
        items.add(new OrderItem("oi_111", "po_3abc", 5));
        order.setItems(items);
        order.setId("o_testestes");
    }

    @Test
    public void ifAvailableThenProcess(){
        OptionApiComms optionApiMock = mock(OptionApiComms.class);
        order.setState(OrderState.NEW.name());
        when(optionApiMock.checkIfItemsAvailable(order.getItems())).thenReturn(true);

        order.processOrder(optionApiMock, new OrderApiComms(""));
        verify(optionApiMock, times(1)).processOptions(any(List.class),  any(UpdateOptionProcess.class));
    }

    @Test
    public void ifNotAvailableThenDontProcess(){
        OptionApiComms optionApiMock = mock(OptionApiComms.class);
        order.setState(OrderState.NEW.name());
        when(optionApiMock.checkIfItemsAvailable(order.getItems())).thenReturn(false);

        order.processOrder(optionApiMock, new OrderApiComms(""));
        verify(optionApiMock, times(1)).processOptions(any(List.class),  any(UpdateOptionDontProcess.class));
    }

}
