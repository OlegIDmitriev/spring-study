package ch8;

import ch8.config.EnversConfig;
import ch8.entities.SingerAudit;
import ch8.service.SingerAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SpringEnversJPADemo {
    private static final Logger logger = LoggerFactory.getLogger(SpringEnversJPADemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(EnversConfig.class);
        SingerAuditService singerAuditService = ctx.getBean(SingerAuditService.class);

        logger.info("Add a new singer");
        SingerAudit singerAudit = new SingerAudit();
        singerAudit.setFirstName("BB");
        singerAudit.setLastName("King");
        singerAudit.setBirthDate(new Date(new GregorianCalendar(1940, 8, 16).getTime().getTime()));
        singerAuditService.save(singerAudit);

        listAudits(singerAuditService.findAll());

        logger.info("Update singer");
        singerAudit.setFirstName("Riley B.");
        singerAuditService.save(singerAudit);
        listAudits(singerAuditService.findAll());
        SingerAudit old = singerAuditService.findAuditByRevision(4L, 1);
        logger.info("\nOld singer with id 4 and rev 1: " + old + "\n");
        old = singerAuditService.findAuditByRevision(4L, 2);
        logger.info("\nOld singer with id 4 and rev 2: " + old + "\n");


        ctx.close();
    }

    public static void listAudits(List<SingerAudit> singerAudits) {
        logger.info("\nListing singers without details: ");
        for (SingerAudit singerAudit : singerAudits) {
            logger.info(singerAudit + "\n");
        }
    }
}
