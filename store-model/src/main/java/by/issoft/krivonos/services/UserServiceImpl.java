package by.issoft.krivonos.services;

import by.issoft.krivonos.domains.User;
import by.issoft.krivonos.exceptions.WrongValueException;
import by.issoft.krivonos.persistences.UserStorage;
import by.issoft.krivonos.validators.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkState;

public class UserServiceImpl implements UserService {

    public static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserStorage userStorage;
    private final UserValidator userValidator;

    public UserServiceImpl(UserStorage userStorage, UserValidator userValidator) {
        this.userStorage = userStorage;
        this.userValidator = userValidator;
    }

    /**
     * @param user - user to create
     *@return instance of created  created user
     */
    @Override
    public User createUser(User user) throws WrongValueException {

        final boolean valid = userValidator.validate(user);

        Optional<User> userByMail = userStorage.findByMail(user.getMail());
        checkState(userByMail.isEmpty(), "mail is busy");
        final User userSaved = userStorage.save(user);
        return userSaved;
    }

    @Override
    public User updateUser(User userForUpdate, User newUser) throws WrongValueException {
        final boolean valid = userValidator.validate(newUser);

        Optional<User> userById = userStorage.get(userForUpdate.getId().toString());
        checkState(userById.isEmpty(), "User is not exist");

        final User userUpdated = userStorage.update(userForUpdate, newUser);

        return userUpdated;
    }

    @Override
    public User deleteUser(User userForDelete) {
        Optional<User> userById = userStorage.get(userForDelete.getId().toString());
        checkState(userById.isEmpty(), "User is not exist");

        boolean isDeleted = userStorage.delete(userForDelete);
        checkState(isDeleted, "User is not deleted");

        return userForDelete;
    }
}
