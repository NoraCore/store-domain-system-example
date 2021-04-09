package by.issoft.krivonos.services;

import by.issoft.krivonos.domains.User;
import by.issoft.krivonos.exceptions.WrongValueException;
import by.issoft.krivonos.persistences.UserStorage;
import by.issoft.krivonos.validators.UserValidator;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static by.issoft.krivonos.data.UserTestDataFactory.anyUser;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    private final User userTestData = new User(UUID.randomUUID(),
            "mail@gmail.com",
            "address");

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserStorage userStorage;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(userStorage.save(any()))
                .thenReturn(userTestData)
                .thenThrow(new IllegalArgumentException("It is impossible save the same user twice"));

        when(userStorage.delete(any()))
                .thenReturn(true)
                .thenThrow(new IllegalStateException("It is impossible delete user twice"));

        when(userStorage.update(any(), any()))
                .thenReturn(userTestData);
    }

    @Test
    void createInvalidUserTest() throws WrongValueException {
        //given
        User user = anyUser();
        userIsNotValid(user);

        //expected
        assertThrows(IllegalArgumentException.class,
                () -> userService.createUser(user));

        verify(userStorage, never()).save(any());
    }

    @Test
    void createUserTest() throws WrongValueException {
        //given
        User user = userTestData;
        userIsValid(user);

        //expected
        final User userCreate = userService.createUser(user);
        assertThat(userCreate, is(equalTo(user)));
        ;

        verify(userStorage,
                times(1).description("The method should be called only once"))
                .save(user);
    }

    @Test
    void createUserTwiceTest() throws WrongValueException {
        //given
        User user = userTestData;
        userIsValid(user);

        //expected
        final User userCreated = userService.createUser(user);
        assertThat(userCreated, is(equalTo(user)));

        userIsInStorage(userCreated);

        assertThrows(IllegalStateException.class,
                () -> userService.createUser(user));
        verify(userStorage,
                times(1)
                        .description("The SAVE should be called only once"))
                .save(user);

    }

    @Test
    void updateWithInvalidUser() throws WrongValueException {
        //given
        User user = anyUser();
        User newUserTest = anyUser();
        userIsNotValid(newUserTest);

        //expected
        assertThrows(IllegalArgumentException.class,
                () -> userService.updateUser(user, newUserTest));

        verify(userStorage, never()).update(any(), any());
    }

    @Test
    void updateWithValidUser() throws WrongValueException {
        //given
        User user = anyUser();
        User newUserTest = userTestData;
        userIsValid(newUserTest);

        //expected
        final User userUpdated = userService.updateUser(user, newUserTest);

        assertThat(userUpdated, is(equalTo(newUserTest)));
        verify(userStorage,
                times(1).description("The UPDATE should be called only once"))
                .update(user, newUserTest);
    }

    @Test
    void deleteUser() {
        User user = anyUser();

        userIsInStorage(user);
        final User userDeleted = userService.deleteUser(user);
        assertThat(userDeleted, is(equalTo(user)));

        verify(userStorage,
                times(1).description("The delete should be called only once"))
                .delete(user);

    }

    @Test
    void deleteNotExistUser() {
        //given
        User user = userTestData;

        //expected
        final User userDeleted = userService.deleteUser(user);
        assertThat(userDeleted, is(equalTo(user)));

        userIsNotInStorage(userDeleted);

        assertThrows(IllegalStateException.class,
                () -> userService.deleteUser(userDeleted));
        verify(userStorage,
                times(1)
                        .description("The Delete should be called only once"))
                .delete(userDeleted);
    }

    void userIsInStorage(User user) {
        when(userStorage.findByMail(user.getMail())).thenReturn(Optional.of(user));
    }

    void userIsNotInStorage(User user) {
        when(userStorage.get(user.getId().toString())).thenReturn(Optional.empty());
    }

    void userIsNotValid(User user) throws WrongValueException {
        when(userValidator.validate(user)).thenThrow(new IllegalArgumentException("User is not valid"));
    }

    void userIsValid(User user) throws WrongValueException {
        when(userValidator.validate(user)).thenReturn(true);
    }
}