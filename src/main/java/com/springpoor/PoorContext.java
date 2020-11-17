package com.springpoor;

import org.apache.logging.log4j.LogManager;
import com.springpoor.annotations.PoorComponent;
import com.springpoor.annotations.Scope;
import com.springpoor.annotations.analyzers.ComponentAnalyzer;
import com.springpoor.annotations.analyzers.ScopeAnalyzer;

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
            Object obj = createValue(key.getValue().toString());
            if (obj.getClass().isAnnotationPresent(PoorComponent.class)) {
                context.put(key.getKey().toString(), obj);
            }
        }
    }

    public Object getBean(String beanName) throws NullPointerException {
        logger.info("request bean with name " + beanName);
        if (context.get(beanName) == null) {

            logger.warn("bin named " + beanName + " doesnt exist, check you properties file " + FILE_PATH + " or annotation");
            throw new NullPointerException("object have reference null");

        } else {
            if (context.get(beanName).getClass().isAnnotationPresent(Scope.class)) {
                return ScopeAnalyzer.returnScopedObject(context.get(beanName));
            } else {
                return context.get(beanName);
            }
        }
    }

    private Object createValue(String path) {
        return ComponentAnalyzer.createValue(path);
    }
}
