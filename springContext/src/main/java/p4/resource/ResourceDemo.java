package p4.resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.File;

public class ResourceDemo {
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext();
        File file = File.createTempFile("test", "txt");
        file.deleteOnExit();

        Resource res1 = ctx.getResource("file:" + file.getPath());
        displayInfo(res1);

        Resource res2 = ctx.getResource("classpath:test.txt");
        displayInfo(res2);

        Resource res3 = ctx.getResource("https://bash.im/");
        displayInfo(res3);
    }

    private static void displayInfo(Resource resource) throws Exception {
        try {
            System.out.println(resource.getClass());
            System.out.println(resource.getURL().getContent());
            System.out.println("");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
