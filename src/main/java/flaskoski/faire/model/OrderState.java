package flaskoski.faire.model;

public enum OrderState {
    NEW,
    PROCESSING,
    PRE_TRANSIT,
    IN_TRANSIT,
    DELIVERED,
    BACKORDERED,
    CANCELED
}
