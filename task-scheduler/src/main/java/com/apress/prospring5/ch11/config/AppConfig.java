package com.apress.prospring5.ch11.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import({DataServiceConfig.class})
@ImportResource("classpath:ctx/task-context.xml")
public class AppConfig {
}
