package by.issoft.krivonos.services;

import by.issoft.krivonos.domains.Item;
import by.issoft.krivonos.exceptions.WrongValueException;

import java.util.List;

public interface ItemService {
    Item create(Item item) throws WrongValueException;

    void delete(Item item);

    Item update(Item itemForUpdate, Item newItem);

    List<Item> getAll();

    List<Item> getByNames(String... names);

    List<Item> getByCosts(int... costs);

    List<Item> getPageOfItem(int numberOfPage);
}
