package com.springpoor.exceptions;
@Deprecated
public class BeanScopeNotFoundException extends Exception {
    public BeanScopeNotFoundException(){
        super();
    }
    public BeanScopeNotFoundException(String message) {
        super(message);
    }
}
