package flaskoski.faire.apicommunication;

import com.google.gson.Gson;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public abstract class AbstractApiComms implements ApiComms {
    protected Gson gson = new Gson();

    String headerId = "X-FAIRE-ACCESS-TOKEN";
    protected String apiKeyHeader;

    public AbstractApiComms(String apiKeyHeader) {
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
