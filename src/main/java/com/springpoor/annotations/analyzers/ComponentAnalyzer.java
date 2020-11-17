package com.springpoor.annotations.analyzers;

import org.apache.logging.log4j.LogManager;

public class ComponentAnalyzer {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ComponentAnalyzer.class);

    public static Object getNewObject(String path) {
        Object testClass = null;
        try {
            Class<?> c = Class.forName(path);
            testClass = c.newInstance();
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(" object have null reference");
        }
        return testClass;
    }

    public static Object returnSingleton(Object object) {
        if (object.getClass() == String.class) {
            return getNewObject(object.toString());
        } else {
            return object;
        }
    }
}