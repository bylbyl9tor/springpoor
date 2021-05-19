package com.examples;

import com.springpoor.annotations.PoorAutowired;
import com.springpoor.annotations.PoorComponent;
import com.springpoor.annotations.ScopeType;

@PoorComponent(scope = ScopeType.PROTOTYPE)
public class MyClass {
    @PoorAutowired
    private WithEvenMinute withEvenMinute;

    public MyClass() {
    }

    public void print() {
        System.out.println(withEvenMinute);
    }
}
