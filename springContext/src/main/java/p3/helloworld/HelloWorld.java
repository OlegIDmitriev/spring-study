package p3.helloworld;

import p3.helloworld.factory.MessageSupportFactory;
import p3.helloworld.provider.MessageProvider;
import p3.helloworld.renderer.MessageRenderer;

public class HelloWorld {
    public static void main(String[] args) {
        MessageRenderer mr = MessageSupportFactory.getInstance().getRenderer();
        MessageProvider mp = MessageSupportFactory.getInstance().getProvider();
        mr.setMessageProvider(mp);
        mr.render();
    }
}
