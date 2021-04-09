package by.issoft.krivonos.validators;

import by.issoft.krivonos.domains.Order;
import by.issoft.krivonos.exceptions.WrongValueException;
import by.issoft.krivonos.utils.RangeNumberOperationsUtils;

public class OrderValidator implements Validator<Order> {

    private final int MAX_BASKET_COST;
    private final int MIN_BASKET_COST;

    public OrderValidator(int min_basket_cost, int max_basket_cost) {
        MAX_BASKET_COST = max_basket_cost;
        MIN_BASKET_COST = min_basket_cost;
    }

    public int getMAX_BASKET_COST() {
        return MAX_BASKET_COST;
    }

    public int getMIN_BASKET_COST() {
        return MIN_BASKET_COST;
    }

    @Override
    public boolean validate(Order order) throws WrongValueException {
        if (order.getUser() == null) {
            throw new WrongValueException("The instance of User is a null");
        }

        if (order.getBasket() == null || !isInRange(order.getBasket().getCost())) {
            throw new WrongValueException("The basket cost should be in range from "
                    + MIN_BASKET_COST + " to " + MAX_BASKET_COST + ":" + order.getBasket());
        }

        if (order.getDate() == null) {
            throw new WrongValueException("The date must be not empty.");
        }

        if (order.getAddress() == null || order.getAddress().isEmpty()) {
            throw new WrongValueException("The address must be not empty.");
        }
        return true;
    }

    private boolean isInRange(int cost) {
        return new RangeNumberOperationsUtils(MIN_BASKET_COST, MAX_BASKET_COST)
                .isInRange(cost);
    }

}
