package ch9;

import ch9.config.DataJpaConfig;
import ch9.config.TxTemplateConfig;
import ch9.service.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class TxProgrammaticDemo {
    private static Logger logger = LoggerFactory.getLogger(TxProgrammaticDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(TxTemplateConfig.class, DataJpaConfig.class);
        SingerService singerService = ctx.getBean(SingerService.class);

        logger.info("Singer count: " + singerService.countAll());
        ctx.close();
    }
}
