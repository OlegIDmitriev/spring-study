package p4.config.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p3.helloworld.provider.MessageProvider;
import p3.helloworld.renderer.MessageRenderer;


public class StandardOutMessageRenderer implements MessageRenderer {
    private MessageProvider messageProvider;

    @Override
    public void render() {
        if (messageProvider == null) {
            throw new RuntimeException("You must set the property messageProvider");
        }

        System.out.println(messageProvider.getMessage());
    }

    @Override
    public void setMessageProvider(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }
}
