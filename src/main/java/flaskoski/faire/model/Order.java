package flaskoski.faire.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Order_table")
public class Order {

    public Order(){}
    @Id
    String id;
    @Column(name = "order_id2")
    String ID;

    String state;
    String ship_after;

    @OneToMany(cascade = CascadeType.PERSIST)
    List<OrderItem> items;
    @OneToMany(cascade = CascadeType.PERSIST)
    List<Shipment> shipments;

    @Embedded
    @AttributeOverride(name = "state", column = @Column(name = "state_of_residence"))
    Address address;
    String created_at;
    String updated_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getShip_after() {
        return ship_after;
    }

    public void setShip_after(String ship_after) {
        this.ship_after = ship_after;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public List<Shipment> getShipments() {
        return shipments;
    }

    public void setShipments(List<Shipment> shipments) {
        this.shipments = shipments;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
}
