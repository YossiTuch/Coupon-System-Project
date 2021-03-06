package app.core.exceptions.couponExceptions;

public class CouponAlreadyPurchasedException extends CouponPurchaseException{
    public CouponAlreadyPurchasedException() {
    }

    public CouponAlreadyPurchasedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CouponAlreadyPurchasedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CouponAlreadyPurchasedException(String message) {
        super(message);
    }

    public CouponAlreadyPurchasedException(Throwable cause) {
        super(cause);
    }
}
