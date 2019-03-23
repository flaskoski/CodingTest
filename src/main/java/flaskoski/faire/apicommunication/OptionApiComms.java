package flaskoski.faire.apicommunication;

import com.google.gson.reflect.TypeToken;
import flaskoski.faire.apicommunication.UpdateOptionStrategy.IUpdateOption;
import flaskoski.faire.model.InventoryItem;
import flaskoski.faire.model.Option;
import flaskoski.faire.model.OrderItem;
import flaskoski.faire.model.Product;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

public class OptionApiComms extends AbstractApiComms{


    static private List<Option> db = new ArrayList(){};

    public OptionApiComms(String apiKeyHeader) {
        super(apiKeyHeader);
    }

    public static List getDb() {
        return db;
    }

    public static void setDb(List<Option> _db) {
        db = _db;
    }

    public int update(String ID, Integer quantity) {
        if(ID == null || quantity == null) return -1;

        String updateJson = "{\"available_units\":"+ quantity.toString() + "}";

        Response response = ApiComms.getTarget()
                .path("/api/v1/products/options").path(ID).request(MediaType.APPLICATION_JSON)
                .header(headerId, apiKeyHeader)
                .property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true)
                .method("PATCH", Entity.json(updateJson));
        return response.getStatus();
    }


    public Boolean checkIfItemsAvailable(List<OrderItem> items) {
        items.sort(Comparator.comparing(OrderItem::getProduct_option_id));

        Iterator<OrderItem> itItem = items.iterator();
        OrderItem item = itItem.next();
        boolean processOrNot = true;

        for(Option opt : db){
            if(opt.getId() != null && opt.getId().equals(item.getProduct_option_id())){

                if(opt.getAvailable_quantity() == null || opt.getAvailable_quantity() < item.getQuantity())//discontinue
                    processOrNot = false;

                if(!itItem.hasNext()) break;
                item = itItem.next();
            }
        }
        return processOrNot;
    }

    public void processOptions(List<OrderItem> items, IUpdateOption updateStrategy) {
        items.sort(Comparator.comparing(OrderItem::getProduct_option_id));
        List<InventoryItem> optionsToUpdate = new ArrayList<>();
        Iterator<OrderItem> itItem = items.iterator();
        OrderItem item = itItem.next();

        for(Option opt : db){
            if(opt.getId() != null && opt.getId().equals(item.getProduct_option_id())){
                updateStrategy.setOption(opt, item.getQuantity());
                optionsToUpdate.add(new InventoryItem(opt.getSku(),
                        opt.getAvailable_quantity(),
                        opt.getActive(),
                        opt.getBackordered_until()));

                if(!itItem.hasNext()) break;
                item = itItem.next();
            }
        }
        updateMultiple(optionsToUpdate);
    }

    public void updateInventory(List<OrderItem> items) {
        for(OrderItem item : items){

        }
    }

    public <InventoryItem> void updateMultiple(List inventoryItems) {
        String invetoryJson = "{\"inventories\":" + gson.toJson(inventoryItems,
                new TypeToken<List<InventoryItem>>(){}.getType())+ "}";


        Response response = ApiComms.getTarget()
                .path("/api/v1/products/options/inventory-levels").request(MediaType.APPLICATION_JSON)
                .header(headerId, apiKeyHeader)
                .property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true)
                .method("PATCH", Entity.json(invetoryJson));
    }

    public List<Option> recordInventory(List<Product> products) {
        List<Option> optionItems = new ArrayList<>();
        List<InventoryItem> inventoryItems = new ArrayList<>();
        for(Product product : products){
            for(Option opt : product.getOptions()){
                InventoryItem item = new InventoryItem(opt.getSku(),
                        opt.getAvailable_quantity(),
                        opt.getActive(),
                        opt.getBackordered_until());
                optionItems.add(opt);
                inventoryItems.add(item);
                //         optionApiComms.update(opt.getId(), item.getCurrent_quantity());
            }
        }
        db.addAll(optionItems);
        db.sort(Comparator.comparing(opt -> opt.getId(), Comparator.nullsFirst(Comparator.naturalOrder())));

        //---This is yielding status 404 for some reason
        updateMultiple(inventoryItems);

        return optionItems;
    }
}
