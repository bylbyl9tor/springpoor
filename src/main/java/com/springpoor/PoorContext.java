package com.springpoor;

import com.springpoor.annotations.ScopeType;
import org.apache.logging.log4j.LogManager;
import com.springpoor.annotations.PoorComponent;
import com.springpoor.annotations.analyzers.ComponentAnalyzer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PoorContext implements ApplicationContext {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(PoorContext.class);

    private String FILE_PATH;

    private Map<String, Object> context = new HashMap<>();

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
            Object object = ComponentAnalyzer.getNewObject(key.getValue().toString());
            if (object.getClass().isAnnotationPresent(PoorComponent.class)) {
                if (object.getClass().getAnnotation(PoorComponent.class).scope().equals(ScopeType.SINGLETON)) {
                    context.put(key.getKey().toString(), object);
                } else {
                    context.put(key.getKey().toString(), key.getValue().toString());
                }
            }
        }
    }

    public Object getBean(String beanName) {
        if (context.containsKey(beanName)) {
            return ComponentAnalyzer.returnSingleton(context.get(beanName));
        } else {
            logger.warn("bin named " + beanName + " doesnt exist, check you properties file " + FILE_PATH + " or annotation");
            return  null;
        }
    }
}
