package com.apress.prospring5.ch11;

import com.apress.prospring5.ch11.config.AsyncConfig;
import com.apress.prospring5.ch11.services.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.concurrent.Future;

public class AsyncTaskDemo {
    private static final Logger logger = LoggerFactory.getLogger(AsyncTaskDemo.class);

    public static void main(String[] args) throws Exception {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AsyncConfig.class);
        AsyncService asyncService = ctx.getBean("asyncService", AsyncService.class);

        for (int i=0; i<5; i++) {
            asyncService.asyncTask();
        }

        Future<String> result1 = asyncService.asyncWithReturn("John Mayer");
        Future<String> result2 = asyncService.asyncWithReturn("Eric Clapton");
        Future<String> result3 = asyncService.asyncWithReturn("BB King");
        Thread.sleep(6000);

        logger.info("Result1: " + result1.get());
        logger.info("Result2: " + result2.get());
        logger.info("Result3: " + result3.get());
        System.in.read();
        ctx.close();
    }
}
