import flaskoski.faire.apicommunication.OrderApiComms;
import flaskoski.faire.apicommunication.ProductApiComms;
import flaskoski.faire.model.Order;
import flaskoski.faire.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;
import java.util.List;

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
        List<Product> productList= productApiComms.getItemsByBrand("b_d2481b88");
        for(Product p : productList){
            Assert.assertEquals("b_d2481b88", p.getBrand_id());
        }
    }

    @Test
    public void getAllItemsNonNull(){
        List<Product> productList= productApiComms.getItemsByBrand("b_d2481b88");
        assertTrue(productList.size() > 10);
        for(Product p : productList){
            Assert.assertNotNull(p.getId());
        }
    }

}
