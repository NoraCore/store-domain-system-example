package by.issoft.krivonos.validators;

import by.issoft.krivonos.exceptions.WrongValueException;

public interface Validator<T> {
    boolean validate(T t) throws WrongValueException;
}
