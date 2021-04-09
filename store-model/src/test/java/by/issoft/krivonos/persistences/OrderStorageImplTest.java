package by.issoft.krivonos.persistences;


import by.issoft.krivonos.domains.Order;
import by.issoft.krivonos.domains.OrderStatus;
import by.issoft.krivonos.domains.PaymentMethod;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static by.issoft.krivonos.data.BasketTestDataFactory.anyValidBasket;
import static by.issoft.krivonos.data.OrderTestDataFactory.anyValidOrder;
import static by.issoft.krivonos.data.OrderTestDataFactory.createTestOrder;
import static by.issoft.krivonos.data.UserTestDataFactory.anyValidUser;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderStorageImplTest {

    private static OrderStorage orderStorage = new OrderStorageImpl();

    @Test
    void save() {
        Order validOrder = anyValidOrder();

        final Order order = orderStorage.save(validOrder);
        assertThat(validOrder, notNullValue());

        final Order loaded = orderStorage.get(order.getId().toString()).get();
        assertThat(loaded, is(equalTo(order)));
    }

    @Test
    public void saveNullItemTest() {
        assertThrows(NullPointerException.class, () -> orderStorage.save(null));
    }

    @Test
    void update() {
        //given
        Order validOrderForUpdate = anyValidOrder();
        Order validOrderAfterUpdate = anyValidOrder();

        //when
        orderStorage.save(validOrderForUpdate);
        final Order loaded = orderStorage.get(validOrderForUpdate.getId().toString()).get();
        assertThat(loaded, notNullValue());

        final Order orderAfterUpdate = orderStorage.update(loaded, validOrderAfterUpdate);

        //then
        assertThat(loaded.getId(), is(equalTo(orderAfterUpdate.getId())));
    }

    @Test
    void delete() {
        //given
        Order validOrder = anyValidOrder();

        //when
        orderStorage.save(validOrder);
        final Order loaded = orderStorage.get(validOrder.getId().toString()).get();
        assertThat(loaded, notNullValue());
        orderStorage.delete(loaded);

        //then
        assertThat(loaded, nullValue());
    }


    @Test
    void findByUserId() {
        //given
        Order anyOrder = anyValidOrder();

        //when
        orderStorage.save(anyOrder);
        final Optional<Order> loaded = orderStorage.get(anyOrder.getId().toString());
        assertThat(loaded.get(), notNullValue());

        final List<Optional<Order>> itemsByCosts = orderStorage
                .findByUserId(loaded
                        .get()
                        .getUser()
                        .getId()
                        .toString());

        //then
        assertThat(itemsByCosts, hasItem(loaded));
    }

    @Test
    void findByDates() {
        //given
        Date dateTest1 = new Date(3_000_000);
        Date dateTest2 = new Date(1_000_000);

        Order anyOrder1 = createTestOrder(anyValidUser(), anyValidBasket(), dateTest1, "testAddress1");
        Order anyOrder2 = createTestOrder(anyValidUser(), anyValidBasket(), dateTest2, "testAddress2");

        //when
        orderStorage.save(anyOrder1);
        final Optional<Order> loaded1 = orderStorage.get(anyOrder1.getId().toString());
        assertThat(loaded1.get(), notNullValue());


        orderStorage.save(anyOrder2);
        final Optional<Order> loaded2 = orderStorage.get(anyOrder2.getId().toString());
        assertThat(loaded2.get(), notNullValue());

        final List<Optional<Order>> orderByDates = orderStorage
                .findByDates(
                        loaded1.get().getDate(),
                        loaded2.get().getDate());

        //then
        assertThat(orderByDates, hasItems(loaded1, loaded2));
    }

    @Test
    void findByAddress() {
        //given
        String testAddress1 = "testAddress1";
        String testAddress2 = "testAddress2";

        Order anyOrder1 = createTestOrder(anyValidUser(), anyValidBasket(), new Date(), testAddress1);
        Order anyOrder2 = createTestOrder(anyValidUser(), anyValidBasket(), new Date(), testAddress2);

        //when
        orderStorage.save(anyOrder1);
        final Optional<Order> loaded1 = orderStorage.get(anyOrder1.getId().toString());
        assertThat(loaded1.get(), notNullValue());


        orderStorage.save(anyOrder2);
        final Optional<Order> loaded2 = orderStorage.get(anyOrder2.getId().toString());
        assertThat(loaded2.get(), notNullValue());

        final List<Optional<Order>> orderByAddress = orderStorage
                .findByAddress(
                        loaded1.get().getAddress(),
                        loaded2.get().getAddress());

        //then
        assertThat(orderByAddress, hasItems(loaded1, loaded2));
    }

    @Test
    void findByStatuses() {
        //given

        Order anyOrder1 = createTestOrder(anyValidUser(), anyValidBasket(), new Date(), "testAddress1", OrderStatus.CANCELED, PaymentMethod.CASH);
        Order anyOrder2 = createTestOrder(anyValidUser(), anyValidBasket(), new Date(), "testAddress2", OrderStatus.DELIVERED, PaymentMethod.ONLINE);

        //when
        orderStorage.save(anyOrder1);
        final Optional<Order> loaded1 = orderStorage.get(anyOrder1.getId().toString());
        assertThat(loaded1.get(), notNullValue());


        orderStorage.save(anyOrder2);
        final Optional<Order> loaded2 = orderStorage.get(anyOrder2.getId().toString());
        assertThat(loaded2.get(), notNullValue());

        final List<Optional<Order>> orderByStatuses = orderStorage
                .findByStatuses(
                        OrderStatus.CANCELED,
                        OrderStatus.DELIVERED);

        //then
        assertThat(orderByStatuses, hasItems(loaded1, loaded2));
    }
}