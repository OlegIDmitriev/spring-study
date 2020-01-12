package p3.helloworld.annotation;

import org.springframework.beans.factory.annotation.Value;
import p3.helloworld.provider.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("provider")
public class ConfigurableMessageProvider implements MessageProvider {
    private String message;

    public ConfigurableMessageProvider(@Value("I am your father!") String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
