package com.anwar.service.exception;

/**
 * @author Anwar
 */

public class BaseServiceException extends RuntimeException {

    private final ApiError apiError;

    public BaseServiceException(ApiError.Errors errorCode, String errorMessage) {
        super(errorMessage);
        this.apiError = ApiError.build(errorCode, errorMessage);
    }

    public ApiError getApiError() {
        return apiError;
    }
}
