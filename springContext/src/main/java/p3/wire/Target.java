package p3.wire;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Target {
    private Foo fooOne;
    private Foo fooTwo;
    private Bar bar;

    public Target() {
    }

    public Target(Foo foo) {
        System.out.println("Target(Foo) called");
    }

    public Target(Foo foo, Bar bar) {
        System.out.println("Target(Foo, Bar) called");
    }

    public void setFooOne(Foo fooOne) {
        this.fooOne = fooOne;
        System.out.println("Property fooOne set");
    }

    public void setFooTwo(Foo fooTwo) {
        this.fooTwo = fooTwo;
        System.out.println("Property fooTwo set");
    }

    public void setBar(Bar bar) {
        this.bar = bar;
        System.out.println("Property bar set");
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:p3/wire-x.xml");
        ctx.refresh();

        Target t = null;
        System.out.println("Using byName:\n");
        t = (Target) ctx.getBean("targetByName");

        System.out.println("Using byType:\n");
        t = (Target) ctx.getBean("targetByType");

        System.out.println("Using constructor:\n");
        t = (Target) ctx.getBean("targetConstructor");

        ctx.close();
    }
}
