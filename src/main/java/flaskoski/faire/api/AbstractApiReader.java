package flaskoski.faire.api;

import flaskoski.faire.model.Product;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.List;

public abstract class AbstractApiReader implements ApiReader{

    String headerId = "X-FAIRE-ACCESS-TOKEN";
    protected String apiKeyHeader;

    public AbstractApiReader(String apiKeyHeader) {
        this.apiKeyHeader = apiKeyHeader;
    }

    public String getApiKeyHeader(){
        return this.apiKeyHeader;
    }
    public void setApiKeyHeader(String key){
        this.apiKeyHeader = key;
    }

    static WebTarget getTarget(){
        Client client = ClientBuilder.newClient();
        return client.target("https://www.faire-stage.com");
    }
}
