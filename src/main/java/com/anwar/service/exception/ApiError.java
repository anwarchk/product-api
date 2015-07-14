package com.anwar.service.exception;

/**
 * @author Anwar
 */

public class ApiError {

    private String errorCode;
    private String errorMessage;

    public ApiError() {
    }

    public ApiError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static ApiError build(Errors errorCode, String errorMessage) {
        return new ApiError(errorCode.name(), errorMessage);
    }

    public enum Errors {
        _INTERNAL_SYSTEM_ERROR,
        _PRODUCT_NOT_FOUND,
        _PRODUCTS_NOT_FOUND
    }
}
