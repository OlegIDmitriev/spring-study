package p4.config.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import p3.helloworld.provider.MessageProvider;
import p3.helloworld.renderer.MessageRenderer;


@Configuration
@ComponentScan(basePackages = {"p4.config.annotated"})
public class AppConfigTwo {
    @Autowired
    MessageProvider provider;

    @Bean(name = "messageRenderer")
    public MessageRenderer messageRenderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider);

        return renderer;
    }
}
