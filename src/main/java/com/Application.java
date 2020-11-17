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

            MyClass myClass = (MyClass) poorContext.getBean("1");//prot
            MyClass myClass1 = (MyClass) poorContext.getBean("1");//prot
            System.out.println(myClass==myClass1);

            OneClass oneClass = (OneClass) poorContext.getBean("2");//sin
            OneClass oneClass1 = (OneClass) poorContext.getBean("2");//sin
            System.out.println(oneClass==oneClass1);

            WithoutAnnotation withoutAnnotation = (WithoutAnnotation) poorContext.getBean("3");//no annotation
            withoutAnnotation.print();

            logger.info("end logging");
        } catch (NullPointerException exception) {
            logger.error(exception.getMessage(),exception);
        }
    }
}
