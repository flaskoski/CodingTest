package flaskoski.faire.api;

import flaskoski.faire.model.Product;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.List;

public interface ApiReader {

    String headerId = "X-FAIRE-ACCESS-TOKEN";

    String getApiKeyHeader();
    void setApiKeyHeader(String key);
    public List<Product> getAllItems();

    static WebTarget getTarget(){
        Client client = ClientBuilder.newClient();
        return client.target("https://www.faire-stage.com");
    }
}
