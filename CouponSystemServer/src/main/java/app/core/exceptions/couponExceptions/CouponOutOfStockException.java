package app.core.exceptions.couponExceptions;

public class CouponOutOfStockException extends CouponPurchaseException{
    public CouponOutOfStockException() {
    }

    public CouponOutOfStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CouponOutOfStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public CouponOutOfStockException(String message) {
        super(message);
    }

    public CouponOutOfStockException(Throwable cause) {
        super(cause);
    }
}
