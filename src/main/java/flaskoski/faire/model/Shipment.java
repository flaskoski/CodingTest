package flaskoski.faire.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Shipment {
    
    @Id
    private String id;
    private String order_id;
    private Integer maker_cost_cents;
    private String carrier;
    private String tracking_code;
    private String created_at;

    public Shipment(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Integer getMaker_cost_cents() {
        return maker_cost_cents;
    }

    public void setMaker_cost_cents(Integer maker_cost_cents) {
        this.maker_cost_cents = maker_cost_cents;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getTracking_code() {
        return tracking_code;
    }

    public void setTracking_code(String tracking_code) {
        this.tracking_code = tracking_code;
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
