package by.issoft.krivonos.services;

import by.issoft.krivonos.domains.Order;
import by.issoft.krivonos.domains.OrderStatus;
import by.issoft.krivonos.domains.PaymentMethod;
import by.issoft.krivonos.exceptions.WrongValueException;
import by.issoft.krivonos.persistences.OrderStorage;
import by.issoft.krivonos.validators.OrderValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkState;

public class OrderServiceImpl implements OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderStorage orderStorage;
    private final OrderValidator orderValidator;

    public OrderServiceImpl(OrderStorage orderStorage, OrderValidator orderValidator) {
        this.orderStorage = orderStorage;
        this.orderValidator = orderValidator;
    }

    /**
     * @param order - order to create
     * @return instance of created Order
     */
    @Override
    public Order createOrder(Order order, PaymentMethod paymentMethod) throws WrongValueException {

        final boolean valid = orderValidator.validate(order);

        Collection<Order> equalsOrders = orderStorage.find(order1 -> compareEquality(order1, order));

        boolean orderIsNotExist = equalsOrders.isEmpty();

        checkState(orderIsNotExist, "Order is created yet: " + order);

        order.setStatus(OrderStatus.AWAITING);
        order.setPayment(paymentMethod);
        final Order orderSaved = orderStorage.save(order);
        return orderSaved;
    }

    @Override
    public Order changeOrderStatus(Order order, OrderStatus orderStatus) throws CloneNotSupportedException {
        final Optional<Order> orderOptional = orderStorage.get(order.getId().toString());
        boolean orderIsPresent =  orderOptional.isPresent();
        checkState(orderIsPresent, "Order is not exist");

        boolean isNotCanceled = !order.getStatus().equals(OrderStatus.CANCELED);
        checkState(isNotCanceled, "Order is canceled.");

        boolean isNotDelivered = !order.getStatus().equals(OrderStatus.DELIVERED);
        checkState(isNotDelivered, "Order is delivered.");

        final Order orderWithNewStatus = orderOptional.get().clone();

        final Order orderChanged = orderStorage.update(orderOptional.get(), orderWithNewStatus);

        return orderChanged;
    }

    @Override
    public Order changePaymentMethod(Order order, PaymentMethod paymentMethod) throws CloneNotSupportedException {
        boolean o =  orderStorage.get(order.getId().toString()).isPresent();
        checkState(o, "Order is not exist");

        boolean isNotPaided = !order.getPayment().equals(PaymentMethod.ONLINE);
        checkState(isNotPaided, "It's impossible to change method of payment: " + order.getPayment());

        order.setPayment(paymentMethod);

        final Order orderWithNewPayment = order.clone();
        final Order orderChanged = orderStorage.update(order, orderWithNewPayment);
        return orderChanged;
    }

    @Override
    public List<Order> loadAllByUserId(String userId) {
        return orderStorage.findByUserId(userId)
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> loadAllByStatuses(OrderStatus... statuses) {
        return orderStorage.findByStatuses(statuses)
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private boolean compareEquality(Order order1, Order order2) {
        return order1.equals(order2) &&
                order1.getStatus().equals(order2.getStatus());
    }

}
