package com.gb.spring;

import com.gb.spring.components.BooksComponent;
import com.gb.spring.components.SQLComponent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

//    public static void main(String[] args) {
//
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//
//        CodeCreator javaCC = context.getBean("javaCodeCreator", CodeCreator.class);
//        System.out.println(javaCC.getClassExample());
//
//        CodeCreator vbNetCC = context.getBean("vbNetCodeCreator", CodeCreator.class);
//        System.out.println(vbNetCC.getClassExample());
//
//        SimpleBeanScopeTest sbst1 = context.getBean("simpleBeanScopeTest", SimpleBeanScopeTest.class);
//        System.out.println(sbst1 + ", data: " + sbst1.getData());
//        sbst1.setData("B");
//        System.out.println(sbst1 + ", data: " + sbst1.getData());
//
//        SimpleBeanScopeTest sbst2 = context.getBean("simpleBeanScopeTest", SimpleBeanScopeTest.class);
//        System.out.println(sbst2 + ", data: " + sbst2.getData());
//
//        ((ClassPathXmlApplicationContext) context).close();
//
//    }

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContextAnnotationExample.xml");

        BooksComponent bc = context.getBean("booksComponent", BooksComponent.class);
        bc.test();

        SQLComponent sqlComponent = context.getBean("SQLComponent", SQLComponent.class);

        ((ClassPathXmlApplicationContext) context).close();
    }

//    public static void main(String[] args) {
//
//        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
//        BooksComponent bc = context.getBean("booksComponent", BooksComponent.class);
//        bc.test();
//        ((AnnotationConfigApplicationContext) context).close();
//    }
}
