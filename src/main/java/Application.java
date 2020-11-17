import org.apache.logging.log4j.LogManager;
import springpoor.PoorContext;
import tests.MyClass;
import tests.OneClass;
import tests.WithoutAnnotation;

public class Application {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(PoorContext.class);

    public static void main(String[] args) {
        try {
            logger.info("start logging");
            PoorContext poorContext = new PoorContext();
            MyClass myClass = (MyClass) poorContext.getBean("1");
            myClass.print();
            myClass.string="123";

            MyClass myclass1 = (MyClass) poorContext.getBean("1");
            myclass1.print();

            OneClass oneClass = (OneClass) poorContext.getBean("2");
            oneClass.print();
            oneClass.string="321";

            OneClass oneClass1 = (OneClass) poorContext.getBean("2");
            oneClass1.print();
/*            WithoutAnnotation withoutAnnotation = (WithoutAnnotation) poorContext.getBean("3");
            withoutAnnotation.print();*/
            logger.info("end logging");
        } catch (NullPointerException exception) {
            logger.error(exception.getMessage(),exception);
        }
    }
}
