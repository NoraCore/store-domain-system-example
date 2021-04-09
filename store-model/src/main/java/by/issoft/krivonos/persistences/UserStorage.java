package by.issoft.krivonos.persistences;

import by.issoft.krivonos.domains.Item;
import by.issoft.krivonos.domains.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface UserStorage extends DataStorage<User> {

    Optional<User> findByMail(String mail);

    Optional<User> findByPhoneNumber(String phoneNumber);
}
