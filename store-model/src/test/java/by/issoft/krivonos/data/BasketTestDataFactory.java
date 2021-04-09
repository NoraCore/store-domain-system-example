package by.issoft.krivonos.data;

import by.issoft.krivonos.domains.Basket;
import by.issoft.krivonos.domains.OrderItem;

import java.util.*;

import static by.issoft.krivonos.data.ItemTestDataFactory.createTestItem;

public class BasketTestDataFactory {

    public static Basket anyValidBasket() {
        Set<OrderItem> orderSet = new HashSet<>(Arrays.asList(
                new OrderItem(createTestItem("TestChoco1", 58), 3),
                new OrderItem(createTestItem("TestChoco2", 70), 4)));
        return createTestBasket(orderSet);
    }

    public static Basket createTestBasket(Set<OrderItem> orderItems) {
        return new Basket(orderItems);
    }

    public static Basket anyBasket() {
        return anyValidBasket();
    }
}
