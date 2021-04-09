package by.issoft.krivonos.persistences;

import by.issoft.krivonos.domains.Item;
import by.issoft.krivonos.domains.Order;
import by.issoft.krivonos.domains.OrderStatus;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OrderStorageImpl implements OrderStorage{
    @Override
    public Optional<Order> get(String id) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public List<Order> getAll() {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Order save(Order order) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Order update(Order order, Order newOrder) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public List<Order> find(Predicate<? super Order> predicate) {
        return getAll().stream()
                .filter(predicate)
                .collect(Collectors.<Order>toList());
    }

    @Override
    public boolean delete(Order order) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public List<Optional<Order>> findByUserId(String userId) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public List<Optional<Order>> findByDates(Date... date) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public List<Optional<Order>> findByAddress(String... address) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public List<Optional<Order>> findByStatuses(OrderStatus... orderStatuses) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
