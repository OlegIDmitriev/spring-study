package com.apress.prospring5.ch11;

import com.apress.prospring5.ch11.config.AnnotationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class ScheduledTaskAnnotatedDemo {
    private final static Logger logger = LoggerFactory.getLogger(ScheduledTaskAnnotatedDemo.class);

    public static void main(String[] args) throws Exception {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        System.in.read();
        ctx.close();
    }
}
