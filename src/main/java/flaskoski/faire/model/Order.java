package flaskoski.faire.model;

import flaskoski.faire.apicommunication.OptionApiComms;
import flaskoski.faire.apicommunication.OrderApiComms;
import flaskoski.faire.apicommunication.UpdateOptionStrategy.UpdateOptionDontProcess;
import flaskoski.faire.apicommunication.UpdateOptionStrategy.UpdateOptionProcess;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Order_table")
public class Order {

    public Order(){}
    @Id
    private String id;
    @Column(name = "order_id2")
    private String ID;

    private String state;
    private String ship_after;

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

    public boolean processOrder(OptionApiComms dbOptions, OrderApiComms dbOrders){
        if(dbOptions.checkIfItemsAvailable(this.getItems()))
        {
//            dbOptions.processOptions(this.getItems(), new UpdateOptionProcess());
//            dbOrders.process(this);
            return true;
        }
//        dbOptions.processOptions(this.getItems(), new UpdateOptionDontProcess());
        return false;
    }

    @Override
    public String toString(){
        return "Order Id: "+ this.getId();
    }


}
