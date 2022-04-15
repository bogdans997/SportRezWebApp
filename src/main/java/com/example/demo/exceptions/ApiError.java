package com.example.demo.exceptions;

public class ApiError
{
    String message;
    ErrorCode code;

    public enum ErrorCode
    {
        RESOURCE_NOT_FOUND,
        RESOURCE_ALREADY_EXISTS,
        USERNAME_NOT_FOUND,
        NULL,
        NEGATIVE_NUMBER,
        WRONG_PAGE_NUMBER_OR_SIZE,
        IN_OUT_ERROR,
        DOCUMENT_ERROR
    }

    public ApiError() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }
}
