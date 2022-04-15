package com.example.demo.exceptions;

import lombok.Getter;

@Getter
public class ResourceException extends Exception
{
    private static final long serialVersionUID = 1L;

    private final ErrorInfo.ResourceType resourceType;

    public ResourceException(final ErrorInfo.ResourceType resourceType, final String message) {
        super(message);

        this.resourceType = resourceType;
    }
}
