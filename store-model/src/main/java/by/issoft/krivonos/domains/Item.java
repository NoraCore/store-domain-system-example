package by.issoft.krivonos.domains;

import by.issoft.krivonos.exceptions.WrongNamingException;
import by.issoft.krivonos.exceptions.WrongValueException;

import java.util.Objects;
import java.util.UUID;

public class Item {
    private final UUID id;
    private String nameItem;
    private int cost;

    public Item(UUID id, String nameItem, int cost) {
        this.nameItem = nameItem;
        this.cost = cost;
        this.id = id;
    }

    public String getNameItem() {
        return nameItem;
    }

    public UUID getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public void setNameItem(String nameItem) throws WrongNamingException {
        if (nameItem.length() > 255 || nameItem.isEmpty()) {
            throw new WrongNamingException("Name of item should be less than 255 symbols");
        }
        this.nameItem = nameItem;
    }

    public void setCost(int cost) throws WrongValueException {
        if (cost <= 0) {
            throw new WrongValueException("Name of item should be less than 255 symbols");
        }
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                "nameItem='" + nameItem + '\'' +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return cost == item.cost && Objects.equals(nameItem, item.nameItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameItem, cost);
    }
}
