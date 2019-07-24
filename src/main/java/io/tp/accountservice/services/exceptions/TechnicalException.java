package io.tp.accountservice.services.exceptions;


public class TechnicalException extends ServiceException {

    public TechnicalException(ErrorCode errorCode) {
        super(errorCode);
    }
}
