package com.springpoor.exceptions;


public class BeanNotFoundException extends Exception {
    public BeanNotFoundException(Exception exception) {
        super(exception);
    }

    public BeanNotFoundException(String exception) {
        super(exception);
    }
}
