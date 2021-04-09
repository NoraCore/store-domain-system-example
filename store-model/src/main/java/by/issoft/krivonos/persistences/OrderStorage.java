package by.issoft.krivonos.persistences;

import by.issoft.krivonos.domains.Order;
import by.issoft.krivonos.domains.OrderStatus;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderStorage extends DataStorage<Order>{

    List<Optional<Order>> findByUserId(String userId);

    List<Optional<Order>> findByDates(Date... date);

    List<Optional<Order>> findByAddress(String... address);

    List<Optional<Order>> findByStatuses(OrderStatus... orderStatuses);
}
