package com.anwar.service.exception;

/**
 * @author Anwar
 */

public class NoProductsFoundException extends BaseServiceException {

    public NoProductsFoundException(final ApiError.Errors errorCode, final String message) {
        super(errorCode, message);
    }
}
