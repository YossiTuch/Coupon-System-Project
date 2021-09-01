package app.core.exceptions.customerExceptions;

public class CustomerAlreadyExistsException extends CustomerException{
    public CustomerAlreadyExistsException() {
    }

    public CustomerAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CustomerAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerAlreadyExistsException(String message) {
        super(message);
    }

    public CustomerAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
