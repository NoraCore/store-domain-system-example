package by.issoft.krivonos.services;

import by.issoft.krivonos.domains.Item;
import by.issoft.krivonos.exceptions.WrongValueException;
import by.issoft.krivonos.persistences.ItemStorage;
import by.issoft.krivonos.validators.ItemValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class ItemServiceImpl implements ItemService {

    public static Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    private final ItemStorage itemStorage;
    private final ItemValidator itemValidator;

    public ItemServiceImpl(ItemStorage itemStorage, ItemValidator itemValidator) {
        this.itemStorage = itemStorage;
        this.itemValidator = itemValidator;
    }

    @Override
    public Item create(Item item) throws WrongValueException {
        final boolean valid = itemValidator.validate(item);
        return itemStorage.save(item);
    }

    @Override
    public void delete(Item item) {

    }

    @Override
    public Item update(Item itemForUpdate, Item newItem) {
        return null;
    }

    @Override
    public List<Item> getAll() {
        return null;
    }

    @Override
    public List<Item> getByNames(String... names) {
        return null;
    }

    @Override
    public List<Item> getByCosts(int... costs) {
        return null;
    }

    @Override
    public List<Item> getPageOfItem(int numberOfPage) {
        return null;
    }
}
