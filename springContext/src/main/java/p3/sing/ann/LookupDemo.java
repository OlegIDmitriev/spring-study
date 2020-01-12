package p3.sing.ann;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;


public class LookupDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:p3/lookup-context-a.xml");
        ctx.refresh();

        DemoBean abstractBean = ctx.getBean("abstractLookupBean", DemoBean.class);
        DemoBean standardBean = ctx.getBean("standardLookupBean", DemoBean.class);

        displayInfo("abstractLookupBean" ,abstractBean);
        displayInfo("standardLookupBean" ,standardBean);

        ctx.close();
    }

    public static void displayInfo(String beanName ,DemoBean bean){
        Singer singer1 = bean.getMySinger();
        Singer singer2 = bean.getMySinger();

        System.out.println(beanName + ": " + "Singer Instance the same? " + (singer1 == singer2));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("lookupDemo");

        for (int i=0; i<100000; i++) {
            Singer singer = bean.getMySinger();
            singer.sing();
        }

        stopWatch.stop();
        System.out.println("100000 gets took " + stopWatch.getTotalTimeMillis() + " ms");
    }
}
