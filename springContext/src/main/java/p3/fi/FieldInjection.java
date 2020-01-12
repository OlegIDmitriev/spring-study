package p3.fi;

import org.springframework.context.support.GenericXmlApplicationContext;

public class FieldInjection {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:p3/p3.fi-context.xml");
        ctx.refresh();

        Singer singer = ctx.getBean(Singer.class);
        singer.sing();
        ctx.close();
    }
}
