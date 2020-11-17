package com.springpoor.exceptions;

public class BeanScopeNotFoundException extends Exception {
    public BeanScopeNotFoundException(){
        super();
    }
    public BeanScopeNotFoundException(String message) {
        super(message);
    }
}
