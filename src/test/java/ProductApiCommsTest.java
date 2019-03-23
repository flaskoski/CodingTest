import flaskoski.faire.apicommunication.OrderApiComms;
import flaskoski.faire.apicommunication.ProductApiComms;
import flaskoski.faire.model.Order;
import flaskoski.faire.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ProductApiCommsTest implements ApiCommsTest {
    private ProductApiComms productApiComms;

    @Before
    public void setup(){
        productApiComms = new ProductApiComms(apiKey);
    }

    @Test
    public void getItemsFromSpecificBrand(){
        Map<String, Product> productList= productApiComms.getItemsByBrand("b_d2481b88");
        for(Product p : productList.values()){
            Assert.assertEquals("b_d2481b88", p.getBrand_id());
        }
    }

    @Test
    public void getAllItemsNonNull(){
        Map<String, Product> productList= productApiComms.getItemsByBrand("b_d2481b88");
        assertTrue(productList.size() > 10);
        for(Product p : productList.values()){
            Assert.assertNotNull(p.getId());
        }
    }

}
