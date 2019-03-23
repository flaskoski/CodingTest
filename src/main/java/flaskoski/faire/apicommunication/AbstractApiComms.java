package flaskoski.faire.apicommunication;

import com.google.gson.Gson;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public abstract class AbstractApiComms implements ApiComms {
    protected Gson gson = new Gson();

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

}
