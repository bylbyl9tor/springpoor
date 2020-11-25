package com.examples;

import com.springpoor.annotations.PoorAutowired;
import com.springpoor.annotations.PoorComponent;

@PoorComponent
public class Auto implements Car {
    @PoorAutowired
    private WithEvenMinute withEvenMinute;
    @Override
    public void print() {
        System.out.println(withEvenMinute);
    }
}
