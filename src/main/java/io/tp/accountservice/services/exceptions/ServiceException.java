package io.tp.accountservice.services.exceptions;


import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class ServiceException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String identifier;

    protected ServiceException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        identifier = generateIdentifier();
    }

    private String generateIdentifier() {
        return UUID.randomUUID().toString();
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getIdentifier() {
        return identifier;
    }
}