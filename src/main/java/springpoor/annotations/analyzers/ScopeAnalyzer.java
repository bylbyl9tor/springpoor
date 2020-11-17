package springpoor.annotations.analyzers;

import org.apache.logging.log4j.LogManager;
import springpoor.annotations.Scope;

public class ScopeAnalyzer {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ComponentAnalyzer.class);

    public static Object returnScopedObject(Object object) {
        String scope = object.getClass().getAnnotation(Scope.class).type();
        switch (scope) {
            case "":
            case "singleton": {
                return object;
            }
            case "prototype": {
                try {
                    return object.getClass().newInstance();
                } catch (InstantiationException e) {
                    logger.error(e.getMessage());
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage());
                }
            }
            default:
                return null;
        }
    }
}
