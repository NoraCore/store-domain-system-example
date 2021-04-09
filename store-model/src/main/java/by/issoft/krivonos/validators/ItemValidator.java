package by.issoft.krivonos.validators;

import by.issoft.krivonos.domains.Item;
import by.issoft.krivonos.exceptions.WrongNamingException;
import by.issoft.krivonos.exceptions.WrongValueException;
import by.issoft.krivonos.utils.RangeNumberOperationsUtils;

public class ItemValidator implements Validator<Item> {

    private final int MAX_ITEM_COST;
    private final int MIN_ITEM_COST;
    private final int MAX_LENGTH_OF_NAME;


    public ItemValidator(int min_item_cost, int max_item_cost, int max_length_of_name) {
        MAX_ITEM_COST = max_item_cost;
        MIN_ITEM_COST = min_item_cost;
        MAX_LENGTH_OF_NAME = max_length_of_name;
    }

    @Override
    public boolean validate(Item item) throws WrongValueException {
        if (item.getNameItem() == null ||
                item.getNameItem().isEmpty() ||
                item.getNameItem().length() >= MAX_LENGTH_OF_NAME) {
            throw new WrongNamingException("Name of item should be not empty and less than " + MAX_LENGTH_OF_NAME + ": " + item);
        }

        if (!isInRange(item.getCost())) {
            throw new WrongValueException("The basket cost should be in range from "
                    + MIN_ITEM_COST + " to " + MAX_ITEM_COST + ":" + item);
        }
        return true;
    }

    private boolean isInRange(int cost) {
        return new RangeNumberOperationsUtils(MIN_ITEM_COST, MAX_ITEM_COST)
                .isInRange(cost);
    }
}