package by.issoft.krivonos.services;

import by.issoft.krivonos.domains.User;
import by.issoft.krivonos.exceptions.WrongValueException;

public interface UserService {
    User createUser(User user) throws WrongValueException;

    User updateUser(User userForUpdate, User newUser) throws WrongValueException;

    User deleteUser(User userForDelete);
}
