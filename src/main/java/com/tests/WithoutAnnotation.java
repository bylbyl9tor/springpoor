package com.tests;

import com.springpoor.annotations.PoorComponent;
import com.springpoor.annotations.ScopeType;

@PoorComponent(scope = ScopeType.SINGLETON, lazy = false)
public class WithoutAnnotation {
    public String string;

    public WithoutAnnotation() {
    }

    public void print() {
        System.out.println(string);
    }
}
