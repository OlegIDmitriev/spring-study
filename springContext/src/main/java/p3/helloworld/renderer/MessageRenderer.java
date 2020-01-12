package p3.helloworld.renderer;

import p3.helloworld.provider.MessageProvider;

public interface MessageRenderer {
    void render();
    void setMessageProvider(MessageProvider messageProvider);
    MessageProvider getMessageProvider();
}
