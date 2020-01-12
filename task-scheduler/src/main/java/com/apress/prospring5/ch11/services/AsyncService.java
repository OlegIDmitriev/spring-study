package com.apress.prospring5.ch11.services;

import java.util.concurrent.Future;

public interface AsyncService {
    void asyncTask();
    Future<String> asyncWithReturn(String name);
}
