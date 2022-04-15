package com.example.demo.exceptions;

public class DuplicateResourceException extends ResourceException
{
    private static final long serialVersionUID = 1L;

    public DuplicateResourceException(ErrorInfo.ResourceType resourceType, String message) {
        super(resourceType, message);
        // TODO Auto-generated constructor stub
    }
}
