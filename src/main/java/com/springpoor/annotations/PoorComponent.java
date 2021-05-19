package com.springpoor.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.springpoor.annotations.ScopeType.SINGLETON;

/**
 * @version 1.0
 * @autor Vitaliy Ritus
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PoorComponent {
    /**
     * return ScopeType; default SINGLETON.
     *
     * @return ScopeType
     */
    ScopeType scope() default SINGLETON;

    /**
     * Returns the value of lazy (); the default value is false.
     * If lazy() == true then the bean will not be created immediately, but only when accessing it.
     *
     * @return boolean
     */
    boolean lazy() default false;
}
