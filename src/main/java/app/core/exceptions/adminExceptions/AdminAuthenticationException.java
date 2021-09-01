package app.core.exceptions.adminExceptions;

import app.core.exceptions.AuthenticationException;
import app.core.exceptions.CouponSystemException;

public class AdminAuthenticationException extends AuthenticationException {
    public AdminAuthenticationException() {
    }

    public AdminAuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AdminAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminAuthenticationException(String message) {
        super(message);
    }

    public AdminAuthenticationException(Throwable cause) {
        super(cause);
    }
}

