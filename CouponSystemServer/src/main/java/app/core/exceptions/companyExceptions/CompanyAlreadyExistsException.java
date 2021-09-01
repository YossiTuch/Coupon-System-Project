package app.core.exceptions.companyExceptions;

public class CompanyAlreadyExistsException extends CompanyException{
    public CompanyAlreadyExistsException() {
    }

    public CompanyAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CompanyAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompanyAlreadyExistsException(String message) {
        super(message);
    }

    public CompanyAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
