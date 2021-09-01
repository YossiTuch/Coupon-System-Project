package app.core.exceptions.companyExceptions;

import app.core.exceptions.AuthenticationException;

public class CompanyAuthenticationException extends AuthenticationException {
    public CompanyAuthenticationException() {
    }

    public CompanyAuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CompanyAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompanyAuthenticationException(String message) {
        super(message);
    }

    public CompanyAuthenticationException(Throwable cause) {
        super(cause);
    }
}

