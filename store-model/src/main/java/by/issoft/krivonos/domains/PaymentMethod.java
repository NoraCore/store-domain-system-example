package by.issoft.krivonos.domains;

import java.util.HashMap;
import java.util.Map;

public enum PaymentMethod {
    ONLINE(1),
    CASH(2),
    CREDIT_CARD(3);

    private final int id;

    private static Map<Integer, PaymentMethod> idToOrderStatus = new HashMap<>();

    static {
        for (PaymentMethod c : PaymentMethod.values()) {
            idToOrderStatus.put(c.id, c);
        }
    }

    PaymentMethod(int id) {
        this.id = id;
    }

    public PaymentMethod getById(int id) {
        return idToOrderStatus.get(id);
    }
}
