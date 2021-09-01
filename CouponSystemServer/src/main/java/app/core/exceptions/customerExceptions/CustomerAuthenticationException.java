package app.core.exceptions.customerExceptions;

import app.core.exceptions.AuthenticationException;

public class CustomerAuthenticationException extends AuthenticationException {
    public CustomerAuthenticationException() {
    }

    public CustomerAuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CustomerAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerAuthenticationException(String message) {
        super(message);
    }

    public CustomerAuthenticationException(Throwable cause) {
        super(cause);
    }
}

