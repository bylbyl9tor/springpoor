package com.springpoor.annotations.analyzers;

import com.springpoor.annotations.PoorComponent;
import org.apache.logging.log4j.LogManager;

@Deprecated
public class ScopeAnalyzer {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ScopeAnalyzer.class);

    public static Object returnScopedObject(Object object) /*throws BeanScopeNotFoundException*/ {

        switch (object.getClass().getAnnotation(PoorComponent.class).scope()) {
            case SINGLETON: {
                return object;
            }
            case PROTOTYPE: {
                try {
                    return object.getClass().newInstance();
                } catch (InstantiationException e) {
                    logger.error(e.getMessage());
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage());
                }
            }
                /*throw new BeanScopeNotFoundException("class "+object.getClass()+" dont have a wrong scope");*/
        }
        return object;
    }
}
