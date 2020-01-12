package p4.config.annotated;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import p3.helloworld.provider.MessageProvider;

@Service("provider")
public class ConfigurableMessageProvider implements MessageProvider {
    private String message;

    public ConfigurableMessageProvider() {
    }

    public ConfigurableMessageProvider(@Value("Love on the weekend!") String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
