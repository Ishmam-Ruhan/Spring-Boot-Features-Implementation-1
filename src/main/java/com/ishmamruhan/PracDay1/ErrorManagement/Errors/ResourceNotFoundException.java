package com.ishmamruhan.PracDay1.ErrorManagement.Errors;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
