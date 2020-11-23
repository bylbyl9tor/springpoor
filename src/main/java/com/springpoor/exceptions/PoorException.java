package com.springpoor.exceptions;

public class PoorException extends Exception {

    public PoorException(Exception exception) {
        super(exception);
    }
}
