package by.issoft.krivonos;

import by.issoft.krivonos.domains.*;
import by.issoft.krivonos.exceptions.WrongValueException;
import by.issoft.krivonos.persistences.ItemInMemoryStorageImpl;
import by.issoft.krivonos.persistences.UserStorage;
import by.issoft.krivonos.persistences.UserStorageImpl;
import by.issoft.krivonos.services.UserService;
import by.issoft.krivonos.services.UserServiceImpl;
import by.issoft.krivonos.validators.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkState;

public class Runner {

    public static Logger logger = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) throws CloneNotSupportedException {

        UUID idd = UUID.randomUUID();
        Set<OrderItem> orderSet = new HashSet<>(Arrays.asList(

                new OrderItem(new Item(idd, "2", 770), 1),
                new OrderItem(new Item(idd, "3", 2323), 1),
                new OrderItem(new Item(UUID.randomUUID(), "4", 90), 1),
                new OrderItem(new Item(UUID.randomUUID(), "5", 70), 2)));
        Basket basket = new Basket(orderSet);

        logger.debug("amount {}", basket.amountOfItem());
        logger.debug("getCost {}", basket.getCost());

        ItemInMemoryStorageImpl itemStorage = new ItemInMemoryStorageImpl();

        orderSet.forEach(orderItem -> {
            logger.debug("Save {} _ {} ",orderItem.getItem().getNameItem(),
                    itemStorage.save(orderItem.getItem()));
        });

        itemStorage.getAll().forEach(System.out::println);
        List<Item> list = itemStorage.find(p -> p.getCost() > 200);
        list.forEach(System.out::println);

        UserStorage userStorage= new UserStorageImpl();
        UserValidator userValidator = new UserValidator();

        UserService userService = new UserServiceImpl(userStorage,userValidator);

        User user = new User(UUID.randomUUID(),"345","dfg");
        try {
            userService.createUser(user);
        } catch (WrongValueException e) {
            logger.error(e.getMessage());
        }

        Order order = new Order(UUID.randomUUID(), user, basket,new Date(),"address", OrderStatus.DELIVERED, PaymentMethod.CREDIT_CARD );
        Order cloneOrder = order.clone();

        logger.debug(order.toString());
        logger.debug("{}",order.hashCode());

        cloneOrder.setStatus(OrderStatus.AWAITING);
        logger.debug("{}",cloneOrder.hashCode());
        logger.debug(cloneOrder.toString());
    }

}
