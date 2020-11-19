package com.springpoor.context;

import com.springpoor.ApplicationContext;
import org.apache.logging.log4j.LogManager;
import com.springpoor.annotations.PoorComponent;
import com.springpoor.annotations.analyzers.ComponentAnalyzer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.*;

public class PoorContext implements ApplicationContext {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(PoorContext.class);

    private final String FILE_PATH;

    private final Map<String, Object> singletons = new HashMap<>();

    private final Map<String, Object> prototypes = new HashMap<>();

    private final Map<String, Object> evenMinutes = new HashMap<>();

    public PoorContext() {
        FILE_PATH = "src/main/resources/beans.properties";
        convertToMap(readPropertyFile(FILE_PATH));
        logger.info("constructor with no arguments called");
    }

    public PoorContext(String filePath) {
        FILE_PATH = filePath;
        convertToMap(readPropertyFile(FILE_PATH));
        logger.info("constructor with custom filepath " + filePath + " called");
    }

    private Properties readPropertyFile(String str) {
        File file = new File(str);
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return properties;
    }

    private void convertToMap(Properties properties) {
        for (Map.Entry<?, ?> key : properties.entrySet()) {
            String beanName = key.getKey().toString();
            Class<?> clazz = ComponentAnalyzer.getLoadedClass(key.getValue().toString());
            if (clazz.isAnnotationPresent(PoorComponent.class)) {
                PoorComponent annotation = clazz.getAnnotation(PoorComponent.class);
                switch (annotation.scope()) {
                    case SINGLETON: {
                        if (annotation.lazy()) {
                            singletons.put(beanName, clazz);
                        } else {
                            singletons.put(beanName, ComponentAnalyzer.getNewObject(clazz));
                        }
                        break;
                    }
                    case PROTOTYPE: {
                        prototypes.put(beanName, clazz);
                        break;
                    }
                    case EVENMINUTE: {
                        evenMinutes.put(beanName, clazz);
                        break;
                    }
                }
            }
        }
    }

    public Set<String> getAllBeansNames() {
        Set<String> allBeanNames = new HashSet<>();
        allBeanNames.addAll(singletons.keySet());
        allBeanNames.addAll(prototypes.keySet());
        allBeanNames.addAll(evenMinutes.keySet());
        return allBeanNames;
    }

    public Object getBean(String beanName) {
        Object mapValue;
        if (singletons.containsKey(beanName)) { //if object is Class->create new and put; else get;
            mapValue = singletons.get(beanName);
            if (mapValue.getClass() == Class.class) {
                Object object = ComponentAnalyzer.getNewObject((Class<?>) mapValue);
                singletons.put(beanName, object);
                return object;
            } else {
                return mapValue;
            }
        } else if (prototypes.containsKey(beanName)) { //return new object
            return ComponentAnalyzer.getNewObject((Class<?>) prototypes.get(beanName));
        } else if (evenMinutes.containsKey(beanName)) {
            int minutes = LocalTime.now().getMinute();//get minutes
            mapValue = evenMinutes.get(beanName);
            Object object;
            if (mapValue.getClass() == Class.class) { //if object is Class-> return new and put;
                object = ComponentAnalyzer.getNewObject((Class<?>) mapValue);
                evenMinutes.put(beanName, object);
            } else { //minutes even->return new object; else return old;
                if (minutes % 2 == 0) {
                    object = ComponentAnalyzer.getNewObject(mapValue.getClass());
                    evenMinutes.put(beanName, object);
                } else {
                    object = evenMinutes.get(beanName);
                }
            }
            return object;
        } else {
            logger.warn("bin named " + beanName + " doesnt exist, check you properties file " + FILE_PATH + " or annotation");
            return null;
        }
    }
}