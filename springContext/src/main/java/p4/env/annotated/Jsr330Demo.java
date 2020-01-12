package p4.env.annotated;

import org.springframework.context.support.GenericXmlApplicationContext;
import p3.helloworld.renderer.MessageRenderer;

public class Jsr330Demo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:p4/jsr330-x.xml");
        ctx.refresh();

        MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        renderer.render();

        ctx.close();
    }
}
