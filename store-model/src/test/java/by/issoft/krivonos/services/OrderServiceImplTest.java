package by.issoft.krivonos.services;

import by.issoft.krivonos.domains.*;
import by.issoft.krivonos.exceptions.WrongValueException;
import by.issoft.krivonos.persistences.OrderStorage;
import by.issoft.krivonos.validators.OrderValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static by.issoft.krivonos.data.OrderTestDataFactory.anyOrder;
import static by.issoft.krivonos.data.OrderTestDataFactory.anyValidOrder;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    OrderValidator orderValidator;

    @Mock
    OrderStorage orderStorage;

    private final PaymentMethod testPayment = PaymentMethod.CASH;

    private final Order orderTestDataForMockReturn = anyValidOrder();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(orderStorage.save(any()))
                .thenReturn(orderTestDataForMockReturn)
                .thenThrow(new IllegalArgumentException("It is impossible save the same order twice"));

        when(orderStorage.findByUserId(any()))
                .thenReturn(Collections.singletonList(
                        Optional.of(orderTestDataForMockReturn)));

        when(orderStorage.findByStatuses(any()))
                .thenReturn(Collections.singletonList(
                        Optional.of(orderTestDataForMockReturn)));

        when(orderStorage.update(any(), any()))
                .thenReturn(orderTestDataForMockReturn);
    }

    @Test
    void createInvalidOrder() throws WrongValueException {

        Order order = anyOrder();
        orderIsNotValid(order);
        assertThrows(IllegalArgumentException.class,
                () -> orderService.createOrder(order, testPayment));

        verify(orderStorage, never()).save(any());
    }

    @Test
    void createOrderTest() throws WrongValueException {

        Order order = orderTestDataForMockReturn;
        orderIsValid(order);

        final Order orderCreate = orderService.createOrder(order, testPayment);

        assertThat(orderCreate, is(equalTo(order)));
        assertThat(orderCreate.getStatus(), is(equalTo(OrderStatus.AWAITING)));
        assertThat(orderCreate.getPayment(), is(equalTo(testPayment)));
        verify(orderStorage,
                times(1).description("The method should be called only once"))
                .save(order);
    }

    @Test
    void createOrderTwiceTest() throws WrongValueException {
        Order order = orderTestDataForMockReturn;
        orderIsValid(order);

        final Order orderCreated = orderService.createOrder(order, testPayment);
        assertThat(orderCreated, is(equalTo(order)));
        orderIsExist(orderCreated);

        assertThrows(IllegalStateException.class,
                () -> orderService.createOrder(order, testPayment));
        verify(orderStorage,
                times(1)
                        .description("The 'save' should be called only once"))
                .save(order);
    }

    @Test
    void changeOrderStatus() throws CloneNotSupportedException {

        OrderStatus testStatus = OrderStatus.CANCELED;
        Order order = orderTestDataForMockReturn;
        Order orderWithNewStatus = order.clone();
        orderWithNewStatus.setStatus(testStatus);

        assertThat(order.getStatus(), not(equalTo(testStatus)));

        orderIsExist(order.getId().toString());
        orderService.changeOrderStatus(order, testStatus);

        verify(orderStorage,
                times(1).description("The UPDATE should be called only once"))
                .update(order, orderWithNewStatus);
    }

    @Test
    void changeCanceledOrderStatus() throws CloneNotSupportedException {
        OrderStatus testStatus = OrderStatus.CANCELED;
        Order orderCanceled = orderTestDataForMockReturn;
        orderCanceled.setStatus(testStatus);
        OrderStatus testNewStatus = OrderStatus.AWAITING;


        assertThat(orderCanceled.getStatus(), not(equalTo(testNewStatus)));

        orderIsExist(orderCanceled.getId().toString());

        assertThrows(IllegalStateException.class,
                () -> orderService.changeOrderStatus(orderCanceled, testNewStatus));

        verify(orderStorage, never()).save(any());
    }

    @Test
    void changeDeliveredOrderStatus() throws CloneNotSupportedException {
        OrderStatus testStatus = OrderStatus.DELIVERED;
        Order orderCanceled = orderTestDataForMockReturn;
        orderCanceled.setStatus(testStatus);
        OrderStatus testNewStatus = OrderStatus.AWAITING;


        assertThat(orderCanceled.getStatus(), not(equalTo(testNewStatus)));

        orderIsExist(orderCanceled.getId().toString());

        assertThrows(IllegalStateException.class,
                () -> orderService.changeOrderStatus(orderCanceled, testNewStatus));

        verify(orderStorage, never()).update(any(), any());
    }

    @Test
    void changePaymentMethodTest() throws CloneNotSupportedException {
        PaymentMethod testPayment = PaymentMethod.CASH;
        Order order = orderTestDataForMockReturn;
        Order orderWithNewStatus = order.clone();
        orderWithNewStatus.setPayment(testPayment);

        assertThat(order.getStatus(), not(equalTo(testPayment)));

        orderIsExist(order.getId().toString());
        orderService.changePaymentMethod(order, testPayment);

        verify(orderStorage,
                times(1).description("The UPDATE should be called only once"))
                .update(order, orderWithNewStatus);
    }

    @Test
    void changeOnlinePaymentMethodTest() {
        PaymentMethod testPayment = PaymentMethod.ONLINE;
        Order orderCanceled = orderTestDataForMockReturn;
        orderCanceled.setPayment(testPayment);
        PaymentMethod testNewPayment = PaymentMethod.CREDIT_CARD;

        assertThat(orderCanceled.getStatus(), not(equalTo(testNewPayment)));

        orderIsExist(orderCanceled.getId().toString());

        assertThrows(IllegalStateException.class,
                () -> orderService.changePaymentMethod(orderCanceled, testNewPayment));

        verify(orderStorage, never()).update(any(), any());
    }

    @Test
    void loadAllByUserId() {
        List<Order> orderListTest = orderService.loadAllByUserId(
                orderTestDataForMockReturn.getUser().getId().toString());

        assertThat(orderListTest, notNullValue());
        assertThat(orderListTest, hasItem(orderTestDataForMockReturn));

        verify(orderStorage,
                times(1).description("The UPDATE should be called only once"))
                .findByUserId(any());
    }

    @Test
    void loadAllByStatuses() throws Exception {
        List<Order> orderListTest = orderService.loadAllByStatuses();

        assertThat(orderListTest, notNullValue());
        assertThat(orderListTest, hasItem(orderTestDataForMockReturn));

        verify(orderStorage,
                times(1).description("The UPDATE should be called only once"))
                .findByStatuses(any());
    }

    private void orderIsNotValid(Order order) throws WrongValueException {
        when(orderValidator.validate(order)).thenThrow(new IllegalArgumentException("Order is not valid"));
    }


    private void orderIsValid(Order order) throws WrongValueException {
        when(orderValidator.validate(order)).thenReturn(true);
    }

    private void orderIsExist(String orderId) {
        when(orderStorage.get(orderId)).thenReturn(Optional.of(orderTestDataForMockReturn));
    }

    private void orderIsExist(Order orderCreated) {
        when(orderStorage.find(any())).thenReturn(Collections.singleton(orderCreated));
    }

}