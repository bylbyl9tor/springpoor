package com.tests;

import com.springpoor.annotations.PoorComponent;
import com.springpoor.annotations.ScopeType;

@PoorComponent(scope = ScopeType.PROTOTYPE)
public class MyClass {
    public String string;

    public MyClass() {
    }

    public void print() {
        System.out.println(string);
    }
}
