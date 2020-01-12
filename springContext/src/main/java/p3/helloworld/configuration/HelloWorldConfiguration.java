package p3.helloworld.configuration;

import p3.helloworld.provider.MessageProvider;
import p3.helloworld.annotation.HelloMessageProvider;
import p3.helloworld.renderer.MessageRenderer;
import p3.helloworld.annotation.StandardOutMessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "p3.helloworld.annotation")
@Configuration
public class HelloWorldConfiguration {
    @Bean
    public MessageProvider provider() {
        return new HelloMessageProvider();
    }

    @Bean
    public MessageRenderer renderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider());
        return renderer;
    }
}
