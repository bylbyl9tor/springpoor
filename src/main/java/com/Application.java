package com;

import com.examples.*;
import com.springpoor.context.ApplicationContext;
import org.apache.logging.log4j.LogManager;
import com.springpoor.context.PoorContext;

import java.util.ConcurrentModificationException;

public class Application {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        try {
            logger.info("Application started");
            ApplicationContext poorContext = PoorContext.getInstance();

            for (String string : poorContext.getAllBeansNames()) {
                System.out.println(poorContext.getBeanInfo(string));
            }
            System.out.println("---------------------------------------");

            MyClass myClass = (MyClass) poorContext.getBean("1");//prot
            MyClass myClass1 = (MyClass) poorContext.getBean("1");//prot
            System.out.println("prototype ==prototype1\n" + (myClass == myClass1));
            myClass.print();
            myClass1.print();
            System.out.println();

            OneClass oneClass = (OneClass) poorContext.getBean("2");//sin
            OneClass oneClass1 = (OneClass) poorContext.getBean("2");//sin
            System.out.println("singletone==singletone1\n" + (oneClass == oneClass1));
            oneClass.print();
            oneClass1.print();
            System.out.println();

            WithoutAnnotation withoutAnnotation = (WithoutAnnotation) poorContext.getBean("3");//sin lazy true
            WithoutAnnotation withoutAnnotation1 = (WithoutAnnotation) poorContext.getBean("3");//sin lazy true
            System.out.println("singletone lazy==singletone1 lazy\n" + (withoutAnnotation == withoutAnnotation1));
            withoutAnnotation.print();
            withoutAnnotation1.print();
            System.out.println();

            WithEvenMinute withEvenMinute = (WithEvenMinute) poorContext.getBean("4");//evenminute
            WithEvenMinute withEvenMinute1 = (WithEvenMinute) poorContext.getBean("4");//evenminute
            WithEvenMinute withEvenMinute2 = (WithEvenMinute) poorContext.getBean("4");//evenminute
            System.out.println("evenminute==evenminute\n" + (withEvenMinute == withEvenMinute1));
            System.out.println("evenminute1==evenminute2\n" + (withEvenMinute1 == withEvenMinute2));
            withEvenMinute.print();
            withEvenMinute1.print();
            withEvenMinute2.print();
            System.out.println();

            Car car = (Car) poorContext.getBean("5");
            car.print();
        } catch (Exception exception) {
            // exception.getStackTrace();
            logger.error("Exception happened", exception);
        }
        logger.info("Application finished work");
    }
}
