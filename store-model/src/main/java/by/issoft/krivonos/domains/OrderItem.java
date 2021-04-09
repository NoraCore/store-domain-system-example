package by.issoft.krivonos.domains;

import java.util.Objects;

public class OrderItem {

    private final Item item;
    private final int amount;

    public OrderItem(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return amount == orderItem.amount && Objects.equals(item, orderItem.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item) * 23;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "item=" + item +
                ", amount=" + amount +
                '}';
    }
}
