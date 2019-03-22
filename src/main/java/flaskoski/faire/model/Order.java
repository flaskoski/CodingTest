package flaskoski.faire.model;

public class Order {

    String id;
    String ID;
    String state;
    String ship_after;
    List<OrderItem> items;
    List<Shipment> items;
    Address address;
    String created_at;
    String updated_at;
}
