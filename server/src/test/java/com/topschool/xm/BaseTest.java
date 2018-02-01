package com.topschool.xm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTest {
    private ApplicationContext context;

    public BaseTest(){
        context = new ClassPathXmlApplicationContext("classpath:application.xml");
    }

    public <T extends Object>T getBean(String clazz){
        return (T)this.context.getBean(clazz);
    }

    public <T extends Object>T getBean(Class<T> clazz){
        return (T)this.context.getBean(clazz);
    }
}
