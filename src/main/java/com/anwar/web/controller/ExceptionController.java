package com.anwar.web.controller;

import com.anwar.service.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Anwar
 */

@ControllerAdvice
public class ExceptionController {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotFoundException.class)
    public ApiError mapProductNotFoundException(ProductNotFoundException exception) {
        LOG.error("Api Error:", exception);
        return exception.getApiError();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoProductsFoundException.class)
    public ApiError mapNoProductsFoundException(NoProductsFoundException exception) {
        LOG.error("Api Error:", exception);
        return exception.getApiError();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ApiError mapInternalServerError(RuntimeException exception) {
        LOG.error("Internal server error:", exception);
        return ApiError.build(ApiError.Errors._INTERNAL_SYSTEM_ERROR, "An unexpected system error occurred. Please try your request again or contact Product API support.");
    }
}