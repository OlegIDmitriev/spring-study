package p3.name.nonsingle;

import org.springframework.context.support.GenericXmlApplicationContext;

public class NonSingletonDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:p3/non-single-x.xml");
        ctx.refresh();

        Singer singer1 = ctx.getBean("nonSingleton", Singer.class);
        Singer singer2 = ctx.getBean("nonSingleton", Singer.class);

        System.out.println("Identity equal?: " + (singer1 == singer2));
        System.out.println("Value equal?: " + (singer1.equals(singer2)));

        System.out.println(singer1);
        System.out.println(singer2);

        ctx.close();
    }
}
