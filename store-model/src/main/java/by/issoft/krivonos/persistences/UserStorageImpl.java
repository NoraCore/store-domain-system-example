package by.issoft.krivonos.persistences;

import by.issoft.krivonos.domains.Item;
import by.issoft.krivonos.domains.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class UserStorageImpl implements UserStorage {

    Logger logger = LoggerFactory.getLogger(UserStorageImpl.class);

    @Override
    public Optional<User> findByMail(String mail) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public Optional<User> get(String id) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public User save(User user) {
        logger.debug("The save method");

        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public User update(User user, User newUser) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public List<User> find(Predicate<? super User> predicate) {
        return null;
    }

    @Override
    public boolean delete(User user) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
