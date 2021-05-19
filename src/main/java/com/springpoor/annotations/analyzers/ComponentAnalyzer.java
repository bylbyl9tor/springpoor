package com.springpoor.annotations.analyzers;

import com.springpoor.exceptions.PoorException;
import org.apache.logging.log4j.LogManager;


/**
 * @version 1.0
 * @autor Vitaliy Ritus
 */
public class ComponentAnalyzer {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ComponentAnalyzer.class);

    /**
     * Method return new instance of Class object
     *
     * @param clazz
     * @return new instance
     */
    public static Object getNewObject(Class<?> clazz) throws PoorException {
        Object newInstanceOfClass;
        try {
            newInstanceOfClass = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            logger.error(" object have null reference");
            throw new PoorException(exception);
        }
        return newInstanceOfClass;
    }

    /**
     * Method returns the Class object associated with the class, with the given string name.
     *
     * @param path
     * @return Class object
     */
    public static Class<?> getLoadedClass(String path) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(path);
            logger.info("class " + path + " was loaded");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return clazz;
    }
}
