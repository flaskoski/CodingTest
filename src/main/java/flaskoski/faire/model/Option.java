package flaskoski.faire.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "option_table")
public class Option {

    @Id
    private
    String id;
    private String product_id;
    private Boolean active;
    private String name;
    private String sku;
    private Integer available_quantity;
    private String backordered_until;
    private String created_at;

    public Option(){}

    public Option(String id, String product_id, boolean active, int available_quantity) {
        this.id = id;
        this.product_id = product_id;
        this.active = active;
        this.available_quantity = available_quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(Integer available_quantity) {
        this.available_quantity = available_quantity;
    }

    public String getBackordered_until() {
        return backordered_until;
    }

    public void setBackordered_until(String backordered_until) {
        this.backordered_until = backordered_until;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    String updated_at;
}
