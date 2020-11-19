package com.springpoor;


import java.util.Set;

public interface ApplicationContext {
    Set<String> getAllBeansNames();

    Object getBean(String beanName);
}
