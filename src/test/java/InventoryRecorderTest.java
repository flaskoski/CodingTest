import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class InventoryRecorderTest implements  ApiCommsTest{
    private WebTarget target;

    @Before
    public void setup(){
        Client client = ClientBuilder.newClient();
        target = client.target(faireApiUri);
    }


    @Test
    public void getAllProductsFromGivenBrand(){
        String content = target.path("/products").request()
                .header(faireAcessTokenHeader, "HQLA9307HSLQYTC24PO2G0LITTIOHS2MJC8120PVZ83HJK4KACRZJL91QB7K01NWS2TUCFXGCHQ8HVED8WNZG0KS6XRNBFRNGY71")
                .get(String.class);
        Assert.assertTrue(content.toString().contains("{\"products\""));
        System.out.println(content);
    }

}
