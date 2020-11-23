package com.springpoor.context;


import com.springpoor.context.PoorContext;
import com.springpoor.exceptions.BeanNotFoundException;
import com.springpoor.exceptions.PoorException;

import java.util.Set;

/**
 * @version 1.0
 * @autor Vitaliy Ritus
 * @see PoorContext
 */
public interface ApplicationContext {
    /**
     * The method returns a set of bean names.
     *
     * @return a set of bean names.
     */
    Set<String> getAllBeansNames();

    /**
     * The method returns a bean.
     *
     * @param beanName bean name
     * @return
     */
    Object getBean(String beanName) throws PoorException, BeanNotFoundException;

    /**
     * The method returns bean information.
     *
     * @param beanName bean name
     * @return
     */
    String getBeanInfo(String beanName) throws BeanNotFoundException;

}
