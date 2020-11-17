package tests;

import springpoor.annotations.PoorComponent;

@PoorComponent
public class OneClass {
    public String string;

    public OneClass() {
    }

    public void print() {
        System.out.println(string);
    }
}
