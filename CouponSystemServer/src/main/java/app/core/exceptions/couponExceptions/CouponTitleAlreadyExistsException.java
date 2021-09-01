package app.core.exceptions.couponExceptions;

public class CouponTitleAlreadyExistsException extends CouponException {

	public CouponTitleAlreadyExistsException() {
		super();
	}

	public CouponTitleAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CouponTitleAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public CouponTitleAlreadyExistsException(String message) {
		super(message);
	}

	public CouponTitleAlreadyExistsException(Throwable cause) {
		super(cause);
	}

}
