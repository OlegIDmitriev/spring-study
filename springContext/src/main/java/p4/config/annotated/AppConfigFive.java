package p4.config.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import p3.helloworld.provider.MessageProvider;
import p3.helloworld.renderer.MessageRenderer;

@Configuration
@ImportResource(value = "classpath:p4/mix-x.xml")
public class AppConfigFive {
    @Autowired
    MessageProvider provider;

    @Bean(name = "messageRenderer")
    public MessageRenderer messageRenderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider);

        return renderer;
    }
}
