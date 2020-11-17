package springpoor.annotations.analyzers;

import org.apache.logging.log4j.LogManager;

public class ComponentAnalyzer {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ComponentAnalyzer.class);

    public static Object createValue(String path) {
        Object testClass = null;
        try {
            Class<?> c = Class.forName(path);
            testClass = c.newInstance();
        } catch (Exception e) {
            logger.error(e.getMessage()+ " object created with null reference");
        }
        return testClass;
    }
}
