package com.examples;

import com.springpoor.annotations.PoorAutowired;
import com.springpoor.annotations.PoorComponent;
import com.springpoor.annotations.ScopeType;

@PoorComponent(scope = ScopeType.SINGLETON, lazy = false)
public class OneClass {
    @PoorAutowired
    private MyClass myClass;
    public OneClass() {
    }

    public void print() {
        System.out.println(myClass);
    }
}
