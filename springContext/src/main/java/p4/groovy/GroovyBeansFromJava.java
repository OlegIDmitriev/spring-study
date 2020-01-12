package p4.groovy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class GroovyBeansFromJava {
    public static void main(String[] args) {
        ApplicationContext ctx = new GenericXmlApplicationContext("classpath:beans.groovy");

        Singer singer = ctx.getBean("singer", Singer.class);
        System.out.println(singer);
    }
}
