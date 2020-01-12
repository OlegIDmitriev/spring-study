package p4.env.annotated;

import p3.helloworld.provider.MessageProvider;

import javax.inject.Inject;
import javax.inject.Named;

@Named("messageProvider")
public class ConfigurableMessageProv implements MessageProvider {
    private String message = "Default message";

    public ConfigurableMessageProv() {
    }

    @Inject
    @Named("message")
    public ConfigurableMessageProv(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
