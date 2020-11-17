package com.tests;

import com.springpoor.annotations.PoorComponent;
import com.springpoor.annotations.Scope;
import com.springpoor.annotations.ScopeType;

@Scope(type = ScopeType.SINGLETON)
@PoorComponent
public class OneClass {
    public String string;

    public OneClass() {
    }

    public void print() {
        System.out.println(string);
    }
}
