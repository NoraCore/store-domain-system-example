package by.issoft.krivonos.services;

import by.issoft.krivonos.domains.Order;
import by.issoft.krivonos.domains.OrderStatus;
import by.issoft.krivonos.domains.PaymentMethod;
import by.issoft.krivonos.exceptions.WrongValueException;

import java.util.List;

public interface OrderService {


    Order createOrder(Order order, PaymentMethod paymentMethod) throws WrongValueException;

    Order changeOrderStatus(Order order, OrderStatus orderStatus) throws CloneNotSupportedException;

    Order changePaymentMethod(Order order, PaymentMethod paymentMethod) throws CloneNotSupportedException;

    List<Order> loadAllByUserId(String userId);

    List<Order> loadAllByStatuses(OrderStatus... statuses);
}
