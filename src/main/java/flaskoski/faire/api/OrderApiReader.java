package flaskoski.faire.api;

import flaskoski.faire.model.Product;

import java.util.List;

public class OrderApiReader implements ApiReader {
    @Override
    public String getApiKeyHeader() {
        return null;
    }

    @Override
    public void setApiKeyHeader(String apiKeyHeader) {

    }

    @Override
    public List<Product> getAllItems() {
        return null;
    }
}
