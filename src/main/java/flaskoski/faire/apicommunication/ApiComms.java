package flaskoski.faire.apicommunication;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public interface ApiComms{

    String headerId = "X-FAIRE-ACCESS-TOKEN";

    String getApiKeyHeader();
    void setApiKeyHeader(String key);

    static WebTarget getTarget(){
        Client client = ClientBuilder.newClient();
        return client.target("https://www.faire-stage.com");
    }
}
