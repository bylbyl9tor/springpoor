package com.examples;

import com.springpoor.annotations.PoorAutowired;
import com.springpoor.annotations.PoorComponent;
import com.springpoor.annotations.ScopeType;

@PoorComponent(scope = ScopeType.EVENMINUTE, lazy = true)
public class WithEvenMinute {
    @PoorAutowired
    WithoutAutowired withoutAutowired;
    public WithEvenMinute() {
    }

    public void print() {
        System.out.println();
    }
}
