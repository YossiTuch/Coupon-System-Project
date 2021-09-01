package app.core.exceptions.couponExceptions;

public class CouponDateExpiredException extends CouponException {

	public CouponDateExpiredException() {
		super();
	}

	public CouponDateExpiredException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CouponDateExpiredException(String message, Throwable cause) {
		super(message, cause);
	}

	public CouponDateExpiredException(String message) {
		super(message);
	}

	public CouponDateExpiredException(Throwable cause) {
		super(cause);
	}
	
	

}
