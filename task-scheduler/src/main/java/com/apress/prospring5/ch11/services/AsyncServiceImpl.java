package com.apress.prospring5.ch11.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service("asyncService")
public class AsyncServiceImpl implements AsyncService {
    private final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Async
    @Override
    public void asyncTask() {
        logger.info("Start execution of async. task");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ie) {
            logger.error("Task Interruption", ie);
        }

        logger.info("Complete executon of async. task");
    }

    @Async
    @Override
    public Future<String> asyncWithReturn(String name) {
        logger.info("Start execution of async. task with return for " + name);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
            logger.error("Task Interruption", ie);
        }

        logger.info("Complete executon of async. task with return for " + name);
        return new AsyncResult<>("Hello: " + name);
    }
}
