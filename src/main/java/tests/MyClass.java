package tests;

import springpoor.annotations.PoorComponent;
import springpoor.annotations.Scope;

@Scope(type = "prototype")
@PoorComponent
public class MyClass {
    public String string;

    public MyClass() {
    }

    public void print() {
        System.out.println(string);
    }
}
