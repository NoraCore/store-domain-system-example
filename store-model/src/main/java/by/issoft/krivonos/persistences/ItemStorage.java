package by.issoft.krivonos.persistences;

import by.issoft.krivonos.domains.Item;

import java.util.List;
import java.util.Optional;

public interface ItemStorage extends DataStorage<Item> {

    List<Optional<Item>> findByNames(String... name);

    List<Optional<Item>> findByCosts(Integer... cost);
}
