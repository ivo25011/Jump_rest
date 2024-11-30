package com.example.Jump_rest.exceptions;

/**
 * Exception class that contains used exceptions within project.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
