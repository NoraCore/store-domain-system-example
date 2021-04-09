package by.issoft.krivonos.persistences;

import by.issoft.krivonos.domains.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ItemInMemoryStorageImpl implements ItemStorage {

    private final Map<String, Item> itemMap = new HashMap<>();

    @Override
    public Optional<Item> get(String id) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public List<Item> getAll() {
        return itemMap.values().parallelStream().toList();
    }

    @Override
    public Item save(Item item) {
        itemMap.put(item.getId().toString(), item);
        return item;
    }

    @Override
    public Item update(Item item, Item newItem) {
        throw new UnsupportedOperationException("not implemented yet");

    }

    @Override
    public boolean delete(Item item) {
        throw new UnsupportedOperationException("not implemented yet");

    }

    @Override
    public List<Optional<Item>> findByNames(String... name) {
        throw new UnsupportedOperationException("not implemented yet");

    }

    @Override
    public List<Optional<Item>> findByCosts(Integer... cost) {
        throw new UnsupportedOperationException("not implemented yet");

    }

    @Override
    public List<Item> find(Predicate<? super Item> predicate) {
        return getAll().stream()
                .filter( predicate )
                .collect(Collectors.<Item>toList());
    }
}
