package com.tests;

import com.springpoor.annotations.PoorComponent;
import com.springpoor.annotations.Scope;
import com.springpoor.annotations.ScopeType;

@Scope(type = ScopeType.PROTOTYPE)
@PoorComponent
public class MyClass {
    public String string;

    public MyClass() {
    }

    public void print() {
        System.out.println(string);
    }
}
