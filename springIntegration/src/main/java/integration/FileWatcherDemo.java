package integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class FileWatcherDemo {
    private static Logger logger = LoggerFactory.getLogger(FileWatcherDemo.class);

    public static void main(String[] args) throws Exception {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:spring/integration-context.xml");
        assert (ctx != null);
        logger.info("Application started...");
        System.in.read();
        ctx.close();
    }
}
