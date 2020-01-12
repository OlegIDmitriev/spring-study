package p3.name;

import org.springframework.context.support.GenericXmlApplicationContext;
import sun.management.AgentConfigurationError;

import java.util.Map;

public class BeanNamingTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:p3/p3.name-context-x.xml");
        ctx.refresh();

        Map<String, String> beans = ctx.getBeansOfType(String.class);
        beans.entrySet().stream().forEach(b -> System.out.println(b.getKey()));
        ctx.close();
    }
}
