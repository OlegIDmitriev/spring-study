package p4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import p3.helloworld.annotation.StandardOutMessageRenderer;
import p3.helloworld.provider.MessageProvider;
import p3.helloworld.renderer.MessageRenderer;

@Configuration
public class AppConfig {
    @Bean
    public MessageProvider messageProvider() {
        return new ConfigurableMessageProvider();
    }

    @Bean
    public MessageRenderer messageRenderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(messageProvider());

        return renderer;
    }
}
