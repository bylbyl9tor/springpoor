package com.examples;

import com.springpoor.annotations.PoorAutowired;
import com.springpoor.annotations.PoorComponent;

@PoorComponent
public class Truck implements Car {
    @PoorAutowired
    private WithoutAnnotation withoutAnnotation;
    @Override
    public void print() {
        System.out.println(withoutAnnotation);
    }
}
