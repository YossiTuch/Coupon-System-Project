package app.core.exceptions.companyExceptions;

public class CompanyNotFoundException extends CompanyException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompanyNotFoundException() {
		super();
	}

	public CompanyNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CompanyNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompanyNotFoundException(String message) {
		super(message);
	}

	public CompanyNotFoundException(Throwable cause) {
		super(cause);
	}

}
