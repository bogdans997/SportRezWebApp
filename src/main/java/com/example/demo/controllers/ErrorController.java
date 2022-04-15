package com.example.demo.controllers;

import com.example.demo.exceptions.*;
import com.lowagie.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ControllerAdvice
@ResponseBody
public class ErrorController
{
    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleResourceNotFound(final ResourceNotFoundException e)
    {
        logger.error("Failed. Exception is: {}.", e.getMessage());
        ApiError apiError = new ApiError();
        apiError.setCode(ApiError.ErrorCode.RESOURCE_NOT_FOUND);
        apiError.setMessage(e.getMessage());
        return apiError;
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleResourceAlreadyExistsException(final ResourceAlreadyExistsException e)
    {
        logger.error("Failed. Exception is: {}.", e.getMessage());
        ApiError apiError = new ApiError();
        apiError.setCode(ApiError.ErrorCode.RESOURCE_ALREADY_EXISTS);
        apiError.setMessage(e.getMessage());
        return apiError;
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleUsernameNotFoundException(final UsernameNotFoundException e)
    {
        logger.error("Failed. Exception is: {}.", e.getMessage());
        ApiError apiError = new ApiError();
        apiError.setCode(ApiError.ErrorCode.USERNAME_NOT_FOUND);
        apiError.setMessage(e.getMessage());
        return apiError;
    }

    @ExceptionHandler(NullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleNullException(final NullException e)
    {
        logger.error("Failed. Exception is: {}.", e.getMessage());
        ApiError apiError = new ApiError();
        apiError.setCode(ApiError.ErrorCode.NULL);
        apiError.setMessage(e.getMessage());
        return apiError;
    }

    @ExceptionHandler(PageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlePageException(final PageException e)
    {
        logger.error("Failed. Exception is: {}.", e.getMessage());
        ApiError apiError = new ApiError();
        apiError.setCode(ApiError.ErrorCode.WRONG_PAGE_NUMBER_OR_SIZE);
        apiError.setMessage(e.getMessage());
        return apiError;
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleIOException(final IOException e)
    {
        ApiError apiError = new ApiError();
        apiError.setCode(ApiError.ErrorCode.IN_OUT_ERROR);
        apiError.setMessage(e.getMessage());
        return apiError;
    }

    @ExceptionHandler(DocumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleDocumentException(final DocumentException e)
    {
        ApiError apiError = new ApiError();
        apiError.setCode(ApiError.ErrorCode.DOCUMENT_ERROR);
        apiError.setMessage(e.getMessage());
        return apiError;
    }
}
