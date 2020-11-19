package com.tests;

import com.springpoor.annotations.PoorComponent;
import com.springpoor.annotations.ScopeType;

@PoorComponent(scope = ScopeType.EVENMINUTE)
public class WithEvenMinute {
    public String string;

    public WithEvenMinute() {
    }

    public void print() {
        System.out.println(string);
    }
}