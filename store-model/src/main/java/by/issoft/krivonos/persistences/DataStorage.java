package by.issoft.krivonos.persistences;

import by.issoft.krivonos.domains.Item;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface DataStorage<T> {

    Optional<T> get(String id);

    List<T> getAll();

    T save(T t);

    T update(T t, T t_update);

    Collection<T> find(Predicate<? super T> predicate);

    boolean delete(T t);
}
