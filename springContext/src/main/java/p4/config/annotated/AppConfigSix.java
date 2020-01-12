package p4.config.annotated;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import p3.helloworld.provider.MessageProvider;

@Configuration
public class AppConfigSix {

    @Bean
    public MessageProvider provider() {
        return new ConfigurableMessageProvider("You still alive");
    }
}
