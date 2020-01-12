package ch9;

import ch9.entities.Singer;
import ch9.service.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class TxDeclarativeDemo {
    private static Logger logger = LoggerFactory.getLogger(TxDeclarativeDemo.class);

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:ctx/aop-tx.xml");;
        ctx.refresh();

        SingerService singerService = ctx.getBean(SingerService.class);

        List<Singer> singers = singerService.findAll();
        singers.forEach(s -> logger.info(s.toString()));

        Singer singer = singerService.findById(1L);
        singer.setFirstName("John Clayton");
        singerService.save(singer);
        logger.info("singer saved successfully: " + singer);

        logger.info("Singer count: " + singerService.countAll());
        ctx.close();
    }
}
