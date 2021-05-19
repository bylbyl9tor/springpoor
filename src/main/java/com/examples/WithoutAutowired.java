package com.examples;

import com.springpoor.annotations.PoorComponent;
import com.springpoor.annotations.ScopeType;

@PoorComponent(scope = ScopeType.SINGLETON, lazy = false)
public class WithoutAutowired {
    public WithoutAutowired() {
    }

    public void print() {
        System.out.println();
    }
}
