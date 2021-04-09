package by.issoft.krivonos.domains;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {
    DELIVERED(1),
    AWAITING(2),
    PROCESSED(3),
    CANCELED(4);

    private final int id;

    private static Map<Integer, OrderStatus> idToOrderStatus = new HashMap<>();

    static {
        for (OrderStatus c : OrderStatus.values()) {
            idToOrderStatus.put(c.id, c);
        }
    }

    OrderStatus(int id) {
        this.id = id;
    }

    public OrderStatus getById(int id) {
        return idToOrderStatus.get(id);
    }
}
