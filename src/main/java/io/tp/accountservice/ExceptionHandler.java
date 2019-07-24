package io.tp.accountservice;

import io.tp.accountservice.rest.model.ErrorResource;
import io.tp.accountservice.services.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;
import java.util.UUID;

@ControllerAdvice
public class ExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(UnknownResourceException.class)
    @ResponseBody
    public ErrorResource handleUnknownResource(UnknownResourceException e) {
        LOGGER.info(e.getIdentifier() + " " + e.getErrorCode().name() + " " + e.getMessage(), e);
        return createErrorResource(e);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ErrorResource handleBusinessException(BusinessException e) {
        LOGGER.info(e.getIdentifier() + " " + e.getErrorCode().name() + " " + e.getMessage(), e);
        return createErrorResource(e);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(TechnicalException.class)
    @ResponseBody
    public ErrorResource handleTechnicalException(TechnicalException e) {
        LOGGER.error(e.getIdentifier() + " " + e.getErrorCode().name() + " " + e.getMessage(), e);
        return createErrorResource(e);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ErrorResource handleUnknownException(Exception e) {
        final TechnicalException technicalException = new TechnicalException(ErrorCode.SERVICE_UNAVAILABLE);
        LOGGER.error(technicalException.getIdentifier() + " " + technicalException.getErrorCode().name() + " " + e.getMessage(), e);

        ErrorResource errorResource = new ErrorResource();
        errorResource.setCode(UUID.randomUUID().toString());
        errorResource.setMessage(ErrorCode.SERVICE_UNAVAILABLE.name());
        return errorResource;
    }

    private ErrorResource createErrorResource(ServiceException e) {
        ErrorResource errorResource = new ErrorResource();
        errorResource.setCode(e.getIdentifier());
        errorResource.setMessage(localizeMessage(e));
        return errorResource;
    }

    private String localizeMessage(ServiceException e) {
        return messageSource.getMessage(e.getErrorCode().name(), new Object[]{}, Locale.getDefault());
    }
}
