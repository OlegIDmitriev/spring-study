package p3.child;

import org.springframework.context.support.GenericXmlApplicationContext;

public class InheritanceDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:p3/p3.child-x.xml");
        ctx.refresh();

        Singer parent = (Singer) ctx.getBean("parent");
        Singer child = (Singer) ctx.getBean("p3/child");

        System.out.println("parent : " + parent);
        System.out.println("p3.child : " + child);
    }
}
