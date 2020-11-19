package com.springpoor.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.springpoor.annotations.ScopeType.SINGLETON;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PoorComponent {
    ScopeType scope() default SINGLETON;

    boolean lazy() default false;

}
