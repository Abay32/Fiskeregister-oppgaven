package com.api.fiskereregister.exceptions;

public class FishNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public FishNotFoundException(String message) {
        super(message);
    }
}
