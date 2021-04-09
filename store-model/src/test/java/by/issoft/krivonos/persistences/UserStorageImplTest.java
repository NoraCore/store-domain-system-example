package by.issoft.krivonos.persistences;

import by.issoft.krivonos.domains.User;
import org.junit.jupiter.api.Test;

import static by.issoft.krivonos.data.UserTestDataFactory.anyValidUser;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserStorageImplTest {
    private UserStorage userStorage = new UserStorageImpl();

    @Test
    public void savePositiveTest() {
        //given
        User validUserTest  = anyValidUser();

        //when
        final User user = userStorage.save(validUserTest);

        //then
        assertThat(user, is(not(null)));
        final User loaded = userStorage.get(user.getId().toString()).get();
        assertThat(loaded, is(equalTo(user)));
    }

    @Test
    public void saveNullUserTest() {
        //when
        assertThrows(NullPointerException.class, () -> userStorage.save(null));
    }

}