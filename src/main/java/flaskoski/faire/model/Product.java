package flaskoski.faire.model;


import java.util.List;

public class Product {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWholesale_price_cents() {
        return wholesale_price_cents;
    }

    public void setWholesale_price_cents(Integer wholesale_price_cents) {
        this.wholesale_price_cents = wholesale_price_cents;
    }

    public Integer getRetail_price_cents() {
        return retail_price_cents;
    }

    public void setRetail_price_cents(Integer retail_price_cents) {
        this.retail_price_cents = retail_price_cents;
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

    public Integer getUnit_multiplier() {
        return unit_multiplier;
    }

    public void setUnit_multiplier(Integer unit_multiplier) {
        this.unit_multiplier = unit_multiplier;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
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

    public Product(){}

    String id;
    String brand_id;
    String short_description;
    String description;
    Integer wholesale_price_cents;
    Integer retail_price_cents;
    Boolean active;
    String name;
    Integer unit_multiplier;
    List<Option> options;
    String created_at;
    String updated_at;
}
