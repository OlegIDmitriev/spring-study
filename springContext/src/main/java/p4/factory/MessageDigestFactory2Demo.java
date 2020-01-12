package p4.factory;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageDigestFactory2Demo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:p4/digest2-x.xml");
        ctx.refresh();

        MessageDigester digester = ctx.getBean("digester", MessageDigester.class);
        digester.digest("Hello world!");
        ctx.close();
    }
}
