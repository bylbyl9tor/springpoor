package com;

import com.springpoor.ApplicationContext;
import com.tests.WithEvenMinute;
import org.apache.logging.log4j.LogManager;
import com.springpoor.context.PoorContext;
import com.tests.MyClass;
import com.tests.OneClass;
import com.tests.WithoutAnnotation;


public class Application {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {

        try {
            logger.info("start logging");
            ApplicationContext poorContext = new PoorContext();
            System.out.println(poorContext.getAllBeansNames());

            MyClass myClass = (MyClass) poorContext.getBean("1");//prot
            MyClass myClass1 = (MyClass) poorContext.getBean("1");//prot
            System.out.println("prototype ==prototype1\n" + (myClass == myClass1));

            OneClass oneClass = (OneClass) poorContext.getBean("2");//sin
            OneClass oneClass1 = (OneClass) poorContext.getBean("2");//sin
            System.out.println("singletone==singletone1\n" + (oneClass == oneClass1));

            WithoutAnnotation withoutAnnotation = (WithoutAnnotation) poorContext.getBean("3");//sin lazy true
            WithoutAnnotation withoutAnnotation1 = (WithoutAnnotation) poorContext.getBean("3");//sin lazy true
            System.out.println("singletone lazy==singletone1 lazy\n" + (withoutAnnotation == withoutAnnotation1));

            WithEvenMinute withEvenMinute = (WithEvenMinute) poorContext.getBean("4");//evenminute
            WithEvenMinute withEvenMinute1 = (WithEvenMinute) poorContext.getBean("4");//evenminute
            WithEvenMinute withEvenMinute2 = (WithEvenMinute) poorContext.getBean("4");//evenminute
            System.out.println("evenminute==evenminute\n" + (withEvenMinute == withEvenMinute1));
            System.out.println("evenminute1==evenminute2\n" + (withEvenMinute1 == withEvenMinute2));

            logger.info("end logging");
        } catch (NullPointerException exception) {
            logger.error(exception.getMessage(), exception);
        }
    }
}
