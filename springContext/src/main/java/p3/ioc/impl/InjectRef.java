package p3.ioc.impl;

import p3.ioc.Oracle;
import org.springframework.context.support.GenericXmlApplicationContext;

public class InjectRef {
    private Oracle oracle;

    public void setOracle(Oracle oracle) {
        this.oracle = oracle;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:p3/ref-inject-x.xml");
        ctx.refresh();

        InjectRef injectRef = (InjectRef) ctx.getBean("injectRef");
        System.out.println(injectRef);
        ctx.close();
    }

    @Override
    public String toString() {
        return oracle.defineMeaningOfLife();
    }
}
