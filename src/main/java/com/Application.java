package com;

import com.springpoor.ApplicationContext;
import org.apache.logging.log4j.LogManager;
import com.springpoor.PoorContext;
import com.tests.MyClass;
import com.tests.OneClass;
import com.tests.WithoutAnnotation;

public class Application {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        try {
            logger.info("start logging");
            ApplicationContext poorContext = new PoorContext();
            MyClass myClass = (MyClass) poorContext.getBean("1");
            myClass.print();
            myClass.string="123";
            myClass.print();
            MyClass myclass1 = (MyClass) poorContext.getBean("1");
            myclass1.print();

            OneClass oneClass = (OneClass) poorContext.getBean("2");
            oneClass.print();
            oneClass.string="321";

            OneClass oneClass1 = (OneClass) poorContext.getBean("2");
            oneClass1.print();

            WithoutAnnotation withoutAnnotation = (WithoutAnnotation) poorContext.getBean("3");
            withoutAnnotation.print();
            logger.info("end logging");
        } catch (NullPointerException exception) {
            logger.error(exception.getMessage(),exception);
        }
    }
}
