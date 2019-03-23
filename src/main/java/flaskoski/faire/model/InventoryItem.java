package flaskoski.faire.model;

import java.time.LocalDateTime;

public class InventoryItem {
    public InventoryItem(String sku, Integer available_quantity, Boolean active, String backordered_until){
        this.sku = sku;
        this.current_quantity = available_quantity;
        this.discontinued = active;
        this.backordered_until = backordered_until;
    }

    private String sku;
    private Integer current_quantity;
    private Boolean discontinued;
    private String backordered_until;


    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getCurrent_quantity() {
        return current_quantity;
    }

    public void setCurrent_quantity(Integer current_quantity) {
        this.current_quantity = current_quantity;
    }

    public Boolean getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Boolean discontinued) {
        this.discontinued = discontinued;
    }

    public String getBackordered_until() {
        return backordered_until;
    }

    public void setBackordered_until(String backordered_until) {
        this.backordered_until = backordered_until;
    }

}
