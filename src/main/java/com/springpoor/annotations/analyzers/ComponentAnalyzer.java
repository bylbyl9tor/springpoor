package com.springpoor.annotations.analyzers;

import org.apache.logging.log4j.LogManager;

public class ComponentAnalyzer {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ComponentAnalyzer.class);

    public static Object getNewObject(Class<?> clazz) {
        Object testClass = null;
        try {
            testClass = clazz.newInstance();
        } catch (Exception e) {
            logger.error(e.getMessage(),e.getStackTrace());
            logger.error(" object have null reference");
        }
        return testClass;
    }

    public static Class<?> getLoadedClass(String path) {
        Class<?> c = null;
        try {
            c = Class.forName(path);
            logger.info("class " + path + " was loaded");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return c;
    }

/*    public static Object returnScopedBean(Object object) {
        if (object.getClass() == Class.class) {
            return getNewObject((Class<?>) object);
        } else {
            return object;
        }
    }*/
}
