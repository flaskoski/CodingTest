package flaskoski.faire.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import flaskoski.faire.model.Product;

import javax.ws.rs.client.WebTarget;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductApiReader extends AbstractApiReader {

    public ProductApiReader(String apiKeyHeader){
        super(apiKeyHeader);
    }

    public List<Product> getItemsByBrand(String brandId) {

        WebTarget target = ApiReader.getTarget();
        List<Product> productsOfPage = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        Integer page = 1;
        Integer limit=50;
        do
        {
            target.queryParam("page", page++);
            //target.queryParam("limit", limit);
            String content = target.path("/api/v1/products").request()
                    .header(headerId, apiKeyHeader)
                    .get(String.class);

            productsOfPage = getProducts(content);
            products.addAll(productsOfPage.stream().filter(p -> brandId.equals(p.getBrand_id())).collect(Collectors.toList()));

        }while (productsOfPage.size() == limit);

        return products;
    }

    private List getProducts(String content) {
        JsonObject jsonObject = new JsonParser().parse(content).getAsJsonObject();

        Gson gson = new Gson();
        List<Product> productsOfPage = gson.fromJson(jsonObject.get("products"), new TypeToken<List<Product>>() {
        }.getType());
        if (productsOfPage == null)
            return Collections.EMPTY_LIST;
        return productsOfPage;
    }

    public List<Product> getAllItems() {

        WebTarget target = ApiReader.getTarget();
        List<Product> productsOfPage = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        Integer page = 1;
        Integer limit=50;
        do
        {
            target.queryParam("page", page++);
            //target.queryParam("limit", limit);
            String content = target.path("/api/v1/products").request()
                    .header(headerId, this.apiKeyHeader)
                    .get(String.class);

            productsOfPage = getProducts(content);
            products.addAll(productsOfPage);

        }while (productsOfPage.size() == limit);

        return products;
    }
}
