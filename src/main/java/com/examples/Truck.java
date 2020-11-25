package com.examples;


public class Truck implements Car {
    private WithoutAutowired withoutAutowired;

    @Override
    public void print() {
        System.out.println(withoutAutowired);
    }
}
