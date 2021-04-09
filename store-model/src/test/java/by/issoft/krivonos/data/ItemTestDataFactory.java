package by.issoft.krivonos.data;

import by.issoft.krivonos.domains.Item;

import java.util.UUID;

public class ItemTestDataFactory {

    public static Item anyValidItem() {
        return createTestItem("testChocolate111", 55);
    }

    public static Item createTestItem(String name, int cost) {
        Item item = new Item(UUID.randomUUID(), name, cost);
        return item;
    }

    public static Item anyItem() {
        return anyValidItem();
    }
}
