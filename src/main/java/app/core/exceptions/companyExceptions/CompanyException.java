package app.core.exceptions.companyExceptions;

import app.core.exceptions.CouponSystemException;

public class CompanyException extends CouponSystemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompanyException() {
		super();
	}

	public CompanyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CompanyException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompanyException(String message) {
		super(message);
	}

	public CompanyException(Throwable cause) {
		super(cause);
	}

}
