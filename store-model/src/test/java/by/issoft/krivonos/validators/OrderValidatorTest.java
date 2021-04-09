package by.issoft.krivonos.validators;

import by.issoft.krivonos.domains.Basket;
import by.issoft.krivonos.domains.Order;
import by.issoft.krivonos.domains.OrderItem;
import by.issoft.krivonos.domains.User;
import by.issoft.krivonos.exceptions.WrongValueException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static by.issoft.krivonos.data.BasketTestDataFactory.anyValidBasket;
import static by.issoft.krivonos.data.BasketTestDataFactory.createTestBasket;
import static by.issoft.krivonos.data.ItemTestDataFactory.createTestItem;
import static by.issoft.krivonos.data.OrderTestDataFactory.anyValidOrder;
import static by.issoft.krivonos.data.OrderTestDataFactory.createTestOrder;
import static by.issoft.krivonos.data.UserTestDataFactory.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderValidatorTest {

    private final OrderValidator orderValidator = new OrderValidator(10, 10000);

    @Test
    public void validateWithValidOrderTest() throws WrongValueException {
        Order validOrder = anyValidOrder();
        boolean result = orderValidator.validate(validOrder);
        assertThat(result, is(equalTo(true)));
    }

    @Test
    public void validateWithNullUserTest() {
        Order invalidOrder = createTestOrder(null, anyValidBasket(), new Date(), "anyVaidAddress");
        assertThrows(WrongValueException.class, () -> orderValidator.validate(invalidOrder));
    }

    @Test
    public void validateWithNullBasketTest() {
        Order invalidOrder = createTestOrder(anyValidUser(), null, new Date(), "anyVaidAddress");
        assertThrows(WrongValueException.class, () -> orderValidator.validate(invalidOrder));
    }

    @Test
    public void validateWithBasketWithCostLessThanMinTest() {
        Set<OrderItem> orderSetWithMaxCost = new HashSet<>(Arrays.asList(
                new OrderItem(createTestItem("TestChoco1", 5), 0),
                new OrderItem(createTestItem("TestChoco2", 5), 0)));

        Order invalidOrder = createTestOrder(anyValidUser(),
                createTestBasket(orderSetWithMaxCost),
                new Date(),
                "anyVaidAddress");
        assertThrows(WrongValueException.class, () -> orderValidator.validate(invalidOrder));
    }

    @Test
    public void validateWithBasketWithCostOverThanMaxTest() {
        Set<OrderItem> orderSetWithMaxCost = new HashSet<>(Arrays.asList(
                new OrderItem(createTestItem("TestChoco1", 6000), 3),
                new OrderItem(createTestItem("TestChoco2", 70000), 4)));

        Order invalidOrder = createTestOrder(anyValidUser(),
                createTestBasket(orderSetWithMaxCost),
                new Date(),
                "anyVaidAddress");

        assertThrows(WrongValueException.class, () -> orderValidator.validate(invalidOrder));
    }


    @Test
    public void validateWithNullDateTest() {
        Order invalidOrder = createTestOrder(anyValidUser(), anyValidBasket(), null, "anyVaidAddress");
        assertThrows(WrongValueException.class, () -> orderValidator.validate(invalidOrder));
    }

    @Test
    public void validateWithNullAddressTest() {
        Order invalidOrder = createTestOrder(anyValidUser(), anyValidBasket(), new Date(), null);
        assertThrows(WrongValueException.class, () -> orderValidator.validate(invalidOrder));
    }

    @Test
    public void validateWithEmptyAddressTest() {
        Order invalidOrder = createTestOrder(anyValidUser(), anyValidBasket(), new Date(), "");
        assertThrows(WrongValueException.class, () -> orderValidator.validate(invalidOrder));
    }

}