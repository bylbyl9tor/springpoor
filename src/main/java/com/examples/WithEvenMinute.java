package com.examples;

import com.springpoor.annotations.PoorComponent;
import com.springpoor.annotations.ScopeType;

@PoorComponent(scope = ScopeType.EVENMINUTE, lazy = true)
public class WithEvenMinute {
    public String string;

    public WithEvenMinute() {
    }

    public void print() {
        System.out.println(string);
    }
}
