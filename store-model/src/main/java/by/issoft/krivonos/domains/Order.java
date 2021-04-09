package by.issoft.krivonos.domains;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Order implements Cloneable{
    private final UUID id;
    private final User user;
    private OrderStatus status;
    private final Basket basket;
    private PaymentMethod payment;
    private final Date date;
    private final String address;


    public Order(UUID id, User user, Basket basket, Date date, String address) {
        this(id, user, basket, date, address, OrderStatus.AWAITING, PaymentMethod.CREDIT_CARD);
    }

    public Order(UUID id, User user, Basket basket, Date date, String address, OrderStatus orderStatus) {
        this(id, user, basket, date, address, orderStatus, PaymentMethod.CREDIT_CARD);
    }

    public Order(UUID id, User user, Basket basket, Date date, String address, OrderStatus orderStatus, PaymentMethod payment) {
        this.status = orderStatus;
        this.payment = payment;
        this.id = id;
        this.user = user;
        this.basket = basket;
        this.date = date;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public PaymentMethod getPayment() {
        return payment;
    }

    public void setPayment(PaymentMethod paymentMethod) {
        this.payment = paymentMethod;
    }

    public User getUser() {
        return user;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Basket getBasket() {
        return basket;
    }

    public Date getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user.toString() +
                ", status=" + status +
                ", basket=" + basket.toString() +
                ", date=" + date +
                ", payment=" + payment +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(user, order.user)
                && Objects.equals(basket, order.basket)
                && Objects.equals(date, order.date)
                && Objects.equals(address, order.address);
//        Objects.equals(id, order.id)
//                &&
    }

    @Override
    public Order clone() throws CloneNotSupportedException {
        return (Order) super.clone();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, basket, date, address);
    }
}
