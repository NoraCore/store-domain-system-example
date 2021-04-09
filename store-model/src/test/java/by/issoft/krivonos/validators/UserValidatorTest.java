package by.issoft.krivonos.validators;

import by.issoft.krivonos.domains.User;
import by.issoft.krivonos.exceptions.WrongValueException;
import org.junit.jupiter.api.Test;

import static by.issoft.krivonos.data.UserTestDataFactory.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;

class UserValidatorTest {

    private final UserValidator userValidator = new UserValidator();

    @Test
    public void validateWithValidUserTest() throws WrongValueException {
        User validUser = anyValidUser();
        boolean result = userValidator.validate(validUser);
        assertThat(result, is(equalTo(true)));
    }

    @Test
    public void validateWithNullUserTest(){
        User nullUser = null;
        assertThrows(WrongValueException.class, () ->userValidator.validate(nullUser));
    }

    @Test
    public void validateWithInvalidUserMailTest(){
        String invalidMail = "invalid@invalid..com";
        User user = anyUserByMail(invalidMail);
        assertThrows(WrongValueException.class, () ->userValidator.validate(user));
    }

    @Test
    public void validateWithInvalidUsersPhoneNumberTest(){
        String invalidMail = "valid@gmail.com";
        String invalidPhone = "invalidphone";

        User nullUser = createTestUser(invalidMail,invalidPhone,"", "");
        assertThrows(WrongValueException.class, () ->userValidator.validate(nullUser));
    }
}