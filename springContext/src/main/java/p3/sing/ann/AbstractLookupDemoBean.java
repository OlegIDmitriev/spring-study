package p3.sing.ann;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component("abstractLookupBean")
public class AbstractLookupDemoBean implements DemoBean {
    @Lookup("singer")
    public Singer getMySinger() {
        return null;
    };

    @Override
    public void doSomething() {
        getMySinger().sing();
    }
}
