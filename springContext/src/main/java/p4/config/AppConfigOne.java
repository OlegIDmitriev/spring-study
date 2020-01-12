package p4.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import p3.helloworld.annotation.StandardOutMessageRenderer;
import p3.helloworld.provider.MessageProvider;
import p3.helloworld.renderer.MessageRenderer;

@Configuration
@PropertySource(value = "classpath:message.properties")
public class AppConfigOne {
    @Autowired
    private Environment env;

    @Bean
    @Lazy
    public MessageProvider messageProvider() {
        return new ConfigurableMessageProvider(env.getProperty("message"));
    }

    @Bean(name = "messageRenderer")
    @Scope(value = "prototype")
    @DependsOn(value = "messageProvider")
    public MessageRenderer messageRenderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(messageProvider());

        return renderer;
    }
}
