package com.anwar.service.exception;

/**
 * @author Anwar
 */

public class ProductNotFoundException extends BaseServiceException {

    public ProductNotFoundException(final ApiError.Errors errorCode, final String message) {
        super(errorCode, message);
    }
}
