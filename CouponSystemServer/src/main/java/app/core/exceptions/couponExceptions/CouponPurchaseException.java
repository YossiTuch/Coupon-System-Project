package app.core.exceptions.couponExceptions;

public class CouponPurchaseException extends CouponException{
    public CouponPurchaseException() {
    }

    public CouponPurchaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CouponPurchaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CouponPurchaseException(String message) {
        super(message);
    }

    public CouponPurchaseException(Throwable cause) {
        super(cause);
    }
}
