package p3.helloworld.annotation;

import p3.helloworld.provider.MessageProvider;

//@Component("provider")
public class HelloMessageProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello world!";
    }
}
