package io.tp.accountservice.services.exceptions;

public class BusinessException extends ServiceException {
    public BusinessException(ErrorCode errorCode) {
        super(errorCode);
    }
}
