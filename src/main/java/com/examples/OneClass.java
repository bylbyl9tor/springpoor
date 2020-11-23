package com.examples;

import com.springpoor.annotations.PoorComponent;
import com.springpoor.annotations.ScopeType;

@PoorComponent(scope = ScopeType.SINGLETON ,lazy = false)
public class OneClass {
    public String string;

    public OneClass() {
    }

    public void print() {
        System.out.println(string);
    }
}
