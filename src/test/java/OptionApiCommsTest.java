import flaskoski.faire.apicommunication.OptionApiComms;
import flaskoski.faire.model.Option;
import flaskoski.faire.model.Order;
import flaskoski.faire.model.OrderItem;
import org.junit.Before;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class OptionApiCommsTest implements ApiCommsTest {
    private OptionApiComms optionApiComms;
    private List<Option> options;

    @Before
    public void setup(){
        optionApiComms = new OptionApiComms(apiKey);
        options = new ArrayList<>();
        options.add(new Option("po_1abc", "p_aaaa", true, 10));
        options.add(new Option("po_2abc", "p_aaaa", true, 10));
        options.add(new Option("po_3abc", "p_aaaa", true, 10));
    }

    @Test
    public void confirmItemsAreAvailableOrNot(){
        //OptionApiComms mockOptionApiComms = mock(OptionApiComms.class);
        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem("oi_111", "po_1abc", 5));
        items.add(new OrderItem("oi_111", "po_2abc", 5));

        options.add(new Option("3abc", "aaaa", true, 10));

        optionApiComms.setDb(options);

        //5 left for each of them
        assertTrue(optionApiComms.checkIfItemsAvailable(items));

        //15 > 10
        items.add(new OrderItem("oi_111", "po_3abc", 15));

        assertFalse(optionApiComms.checkIfItemsAvailable(items));
    }
//
//    @Test
//    public void getAllItemsNonNull(){
//        List<Product> productList= productApiComms.getItemsByBrand("b_d2481b88");
//        assertTrue(productList.size() > 10);
//        for(Product p : productList){
//            Assert.assertNotNull(p.getId());
//        }
//    }

}
