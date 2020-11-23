package com;

import com.springpoor.context.ApplicationContext;
import com.examples.WithEvenMinute;
import com.springpoor.exceptions.BeanNotFoundException;
import com.springpoor.exceptions.PoorException;
import org.apache.logging.log4j.LogManager;
import com.springpoor.context.PoorContext;
import com.examples.MyClass;
import com.examples.OneClass;
import com.examples.WithoutAnnotation;

import java.io.IOException;


public class Application {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        try {
            logger.info("Application started");
            ApplicationContext poorContext = PoorContext.getInstance();
            for (String string : poorContext.getAllBeansNames()) {
                System.out.println(poorContext.getBeanInfo(string));
            }
            //System.out.println(poorContext.getBeanInfo("string"));

            MyClass myClass = (MyClass) poorContext.getBean("0");//prot
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

        } catch (NullPointerException | IOException | PoorException | BeanNotFoundException exception) {
            logger.error(exception);
        }
        logger.info("Application finished work");
    }
}
