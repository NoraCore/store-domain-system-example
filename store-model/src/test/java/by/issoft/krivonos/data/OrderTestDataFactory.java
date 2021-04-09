package by.issoft.krivonos.data;

import by.issoft.krivonos.domains.*;

import java.util.Date;
import java.util.UUID;

import static by.issoft.krivonos.data.BasketTestDataFactory.anyValidBasket;
import static by.issoft.krivonos.data.UserTestDataFactory.anyValidUser;

public class OrderTestDataFactory {

    public static Order anyValidOrder() {
        String testAddress = "testTest street 42";
        return createTestOrder(anyValidUser(), anyValidBasket(), new Date(), testAddress);
    }

    public static Order createTestOrder(User user, Basket basket, Date date, String address) {
        return createTestOrder(user, basket, date, address, OrderStatus.AWAITING, PaymentMethod.CREDIT_CARD);
    }

    public static Order createTestOrder(User user,
                                        Basket basket,
                                        Date date,
                                        String address,
                                        OrderStatus orderStatus,
                                        PaymentMethod paymentMethod) {
        Order order = new Order(UUID.randomUUID(), user, basket, date, address, orderStatus, paymentMethod);
        return order;
    }

    public static Order anyOrder() {
        return anyValidOrder();
    }
}