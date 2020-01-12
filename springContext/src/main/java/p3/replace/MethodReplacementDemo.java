package p3.replace;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

public class MethodReplacementDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:p3/rep-context-x.xml");
        ctx.refresh();

        ReplacementTarget replacementTarget = (ReplacementTarget) ctx.getBean("replacementTarget");
        ReplacementTarget standardTarget = (ReplacementTarget) ctx.getBean("standardTarget");

        disaplyInfo(replacementTarget);
        disaplyInfo(standardTarget);

        ctx.close();
    }

    private static void disaplyInfo(ReplacementTarget target) {
        System.out.println(target.formatMessage("Thanks for playing, try again!"));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("perfTest");
        for(int i=0; i<100000; i++) {
            String out = target.formatMessage("No filter in my head!");
        }
        stopWatch.stop();
        System.out.println("100000 invocations took: " + stopWatch.getTotalTimeMillis() + " ms");
    }
}
