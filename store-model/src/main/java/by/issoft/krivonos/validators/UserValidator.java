package by.issoft.krivonos.validators;

import by.issoft.krivonos.domains.User;
import by.issoft.krivonos.exceptions.NullValueException;
import by.issoft.krivonos.exceptions.WrongValueException;

import java.util.regex.Pattern;

public class UserValidator implements Validator<User> {

    private final String VALID_EMAIL_REGEX="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";;
    private final String VALID_PHONE_REGEX="^(375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$";

    @Override
    public boolean validate(User user) throws WrongValueException {
        if (user == null) {
            throw new NullValueException("The instance of User is a null");
        }
        if (!validateEmail(user.getMail())) {
            throw new WrongValueException("The mail of user is incorrect:" + user);
        }
        if (!validatePhoneNumber(user.getPhoneNumber())) {
            throw new WrongValueException("The phone number must be digits only:" + user);
        }
        return true;
    }

    private boolean validateEmail(String email) {
        return Pattern
                .compile(VALID_EMAIL_REGEX, Pattern.CASE_INSENSITIVE)
                .matcher(email)
                .matches();
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        return Pattern
                .compile(VALID_PHONE_REGEX)
                .matcher(phoneNumber)
                .matches();
    }

}
