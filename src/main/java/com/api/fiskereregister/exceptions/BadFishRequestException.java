package com.api.fiskereregister.exceptions;

public class BadFishRequestException extends RuntimeException {
    private static final long serialVersionUID = 1;
    public BadFishRequestException(String message) {
        super(message);
    }
}
