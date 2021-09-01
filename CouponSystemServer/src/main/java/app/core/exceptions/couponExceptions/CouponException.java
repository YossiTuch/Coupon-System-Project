package app.core.exceptions.couponExceptions;

import app.core.exceptions.CouponSystemException;

public class CouponException extends CouponSystemException {
    public CouponException() {
    }

    public CouponException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CouponException(String message, Throwable cause) {
        super(message, cause);
    }

    public CouponException(String message) {
        super(message);
    }

    public CouponException(Throwable cause) {
        super(cause);
    }
}
