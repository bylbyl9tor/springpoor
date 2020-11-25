package com.springpoor.context;

import com.springpoor.annotations.PoorAutowired;
import com.springpoor.annotations.ScopeType;
import com.springpoor.exceptions.AutowiredFieldNotComponentException;
import com.springpoor.exceptions.BeanNotFoundException;
import com.springpoor.exceptions.PoorException;
import org.apache.logging.log4j.LogManager;
import com.springpoor.annotations.PoorComponent;
import com.springpoor.annotations.analyzers.ComponentAnalyzer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The class is used to initialize the application context from the .properties file. <br/>
 * <br/>Has three public methods:<br/> 1){@link PoorContext#getAllBeansNames()}  returns all bean names
 * <br/>2) {@link PoorContext#getBean(String)} returns a bean object
 * <br/>3) {@link PoorContext#getBeanInfo(String)} meta information about bean
 *
 * @version 1.0
 * @autor Vitaliy Ritus
 */
public class PoorContext implements ApplicationContext {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(PoorContext.class);

    private static PoorContext instance;

    private final String FILE_PATH;

    private final Map<String, PoorBeanDefinition> metaInfo = new HashMap<>();

    private final Map<String, Object> instanceObjects = new ConcurrentHashMap<>();

    private final Map<String, String> pathAndBeanName = new HashMap<>();

    private PoorContext() throws Exception {
        FILE_PATH = "src/main/resources/beans.properties";
        convertToMap(readPropertyFile(FILE_PATH));
        autowiredFilter();
        logger.info("constructor with no arguments called");
    }

    public static PoorContext getInstance() throws Exception {
        if (instance == null) {
            instance = new PoorContext();
        }
        return instance;
    }

    /**
     * Method for getting the Properties object with data from the configuration file {@link PoorContext#FILE_PATH}
     *
     * @param filePath configuration file path
     * @return returns a set of Properties from the config file or null on error
     * @throws IOException if there was an error reading from the input stream.
     */
    private Properties readPropertyFile(final String filePath) throws IOException {
        File file = new File(filePath);
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        return properties;
    }

    /**
     * The method fills the context with data.
     * The method takes an element from Properties, the method {@link ComponentAnalyzer#getLoadedClass(String)}
     * creates its Class, checks if annotation is present {@link PoorComponent},
     * if present, the bean goes to the field {@link PoorContext#metaInfo}, then if it has a
     * {@link com.springpoor.annotations.ScopeType#SINGLETON} or {@link com.springpoor.annotations.ScopeType#EVENMINUTE}
     * and {@link PoorComponent#lazy()} == false, then a new instance of the object is created and goes to the field
     * {@link PoorContext#instanceObjects}
     *
     * @param properties properties, {@link PoorContext#readPropertyFile(String)} method result
     * @see PoorContext#convertToMap(Properties)
     * @see ComponentAnalyzer
     */
    private void convertToMap(Properties properties) throws PoorException {
        for (Map.Entry<?, ?> key : properties.entrySet()) {
            String beanName = key.getKey().toString();
            Class<?> clazz = ComponentAnalyzer.getLoadedClass(key.getValue().toString());
            if (clazz.isAnnotationPresent(PoorComponent.class)) {
                PoorBeanDefinition beanCandidate = new PoorBeanDefinition(clazz);
                metaInfo.put(beanName, beanCandidate);
                pathAndBeanName.put(key.getValue().toString(), beanName);
                switch (beanCandidate.getScopeType()) {
                    case SINGLETON:
                    case EVENMINUTE: {
                        if (!beanCandidate.isLazy()) {
                            instanceObjects.put(beanName, ComponentAnalyzer.getNewObject(clazz));
                        }
                        break;
                    }
                }
            }
        }
    }

    private void autowiredFilter() throws Exception {
        for (Map.Entry<?, ?> object : instanceObjects.entrySet()) {
            instanceObjects.replace(object.getKey().toString(), injectAutowiredFields(object.getValue()));
        }
    }

    private Object injectAutowiredFields(Object object) throws Exception {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(PoorAutowired.class)) {
                String beanName = pathAndBeanName.get(field.getType().getCanonicalName());
                if (beanName == null) {
                    throw new AutowiredFieldNotComponentException("In class " + object.getClass().getCanonicalName() +
                            " field " + field.getName() + " " + field.getName() +
                            " tagged with annotation @PoorAutowired, but class " + field.getType().getCanonicalName() +
                            " not a bean (not tagged with annotation @Component)");
                }
                field.setAccessible(true);
                field.set(object, getBean(beanName));
                field.setAccessible(false);
            }
        }
        return object;
    }

    /**
     * The method returns a set of bean names that are stored in the PoorContext.
     *
     * @return returns the set of names of all beans.
     * @see PoorContext#metaInfo
     */
    public Set<String> getAllBeansNames() {
        return metaInfo.keySet();
    }

    /**
     * The method returns String with information about bean(class, scope(), lazy()).
     *
     * @return returns the set of names of all beans.
     * @see PoorBeanDefinition#toString()
     * @see PoorContext#metaInfo
     */
    public String getBeanInfo(String beanName) throws BeanNotFoundException {
        try {
            return metaInfo.get(beanName).toString();
        } catch (Exception exception) {
            logger.error("bean named " + beanName + " doesnt exist, check you properties file " + FILE_PATH + " or annotation");
            throw new BeanNotFoundException("bean named " + beanName + " doesnt exist, check you properties file " + FILE_PATH + " or annotation");
        }
    }

    /**
     * The method returns an bean from context.
     * If {@link PoorContext#metaInfo} contains beanName, method get bean {@link PoorContext#metaInfo}
     * we call the {@link com.springpoor.annotations.ScopeType#needNewObject(boolean)} call the method that
     * returns true if need, create a new instance of the bean and false if we return the existing one.
     * If bean name not found - null will be returned
     *
     * @param beanName bean name
     * @return if the bean name is not found null will be returned, if found, then the bean instance
     * @see com.springpoor.annotations.ScopeType#needNewObject(boolean)
     */
    public Object getBean(String beanName) throws Exception {
        if (metaInfo.containsKey(beanName)) {
            PoorBeanDefinition poorBeanDefinition = metaInfo.get(beanName);
            if (poorBeanDefinition.getScopeType().needNewObject(instanceObjects.containsKey(beanName))) {
                Object object = injectAutowiredFields(ComponentAnalyzer.getNewObject(poorBeanDefinition.getClazz()));
                if (poorBeanDefinition.getScopeType() != ScopeType.PROTOTYPE) {
                    instanceObjects.put(beanName, object);
                }
                return object;
            } else {
                return instanceObjects.get(beanName);
            }
        } else {
            logger.error("bean named " + beanName + " doesnt exist, check you properties file " + FILE_PATH + " or annotation");
            throw new BeanNotFoundException("bean with name " + beanName + " not found in context");
        }
    }
}