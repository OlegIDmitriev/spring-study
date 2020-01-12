package ch9;

import ch9.config.DataJpaConfig;
import ch9.config.ServicesConfig;
import ch9.entities.Singer;
import ch9.service.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class TxAnnotationDemo {
    private static Logger logger = LoggerFactory.getLogger(TxAnnotationDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ServicesConfig.class, DataJpaConfig.class);
        SingerService singerService = ctx.getBean(SingerService.class);

        List<Singer> singers = singerService.findAll();
        singers.forEach(s -> logger.info(s.toString()));

        Singer singer = singerService.findById(1L);
        singer.setFirstName("John Clayton");
        singer.setLastName("Mayer");
        singerService.save(singer);
        logger.info("Singer saved successfully:" + singer);
        logger.info("Singer count: " + singerService.countAll());

        ctx.close();
    }
}
