package by.issoft.krivonos.exceptions;

public class ValueIsBusyException extends Exception {
    public ValueIsBusyException() {
    }

    public ValueIsBusyException(String message) {
        super(message);
    }

    public ValueIsBusyException(String message, Throwable cause) {
        super(message, cause);
    }
}
