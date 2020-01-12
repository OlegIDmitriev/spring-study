package ch8;

import ch8.config.AppConfig;
import ch8.config.DataJpaConfig;
import ch8.entities.Singer;
import ch8.entities.SingerAudit;
import ch8.service.SingerAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SpringAuditJPADemo {
    private static final Logger logger = LoggerFactory.getLogger(SpringAuditJPADemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        SingerAuditService singerAuditService = ctx.getBean(SingerAuditService.class);

        List<SingerAudit> singerAudits = singerAuditService.findAll();
        listAudits(singerAudits);

        logger.info("Add a new singer");
        SingerAudit singerAudit = new SingerAudit();
        singerAudit.setFirstName("BB");
        singerAudit.setLastName("King");
        singerAudit.setBirthDate(new Date(new GregorianCalendar(1940, 8, 16).getTime().getTime()));
        singerAuditService.save(singerAudit);

        singerAudits = singerAuditService.findAll();
        listAudits(singerAudits);

        singerAudit = singerAuditService.findById(4L);
        logger.info("\nSinger with id 4: " + singerAudit + "\n");

        logger.info("Update singer");
        singerAudit.setFirstName("John Clayton");
        singerAuditService.save(singerAudit);

        listAudits(singerAuditService.findAll());
        ctx.close();
    }

    public static void listAudits(List<SingerAudit> singerAudits) {
        logger.info("\nListing singers without details: ");
        for (SingerAudit singerAudit : singerAudits) {
            logger.info(singerAudit + "\n");
        }
    }
}
