package com.sfbest.www.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HessianTest {

    private static  volatile boolean running = true;

    public static void main(String[] args) {
        ApplicationContext contex = new ClassPathXmlApplicationContext("classpath:hessian/hessian-client.xml");
        ((ClassPathXmlApplicationContext) contex).start();
        synchronized (HessianTest.class) {
            while (running) {
                try {
                    HessianTest.class.wait();
                } catch (Throwable e) {

                }
            }
        }
    }
}
