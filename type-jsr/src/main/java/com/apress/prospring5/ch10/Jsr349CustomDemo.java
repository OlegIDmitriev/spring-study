package com.apress.prospring5.ch10;

import com.apress.prospring5.ch10.config.AppConfig;
import com.apress.prospring5.ch10.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class Jsr349CustomDemo {
    private static Logger logger = LoggerFactory.getLogger(Jsr349CustomDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        SingerValidationService service = ctx.getBean(SingerValidationService.class);

        Singer singer = new Singer();
        singer.setFirstName("J");
        singer.setLastName("Mayer");
        singer.setGender(null);
        singer.setGenre(null);

        validateSinger(singer, service);

        ctx.close();
    }

    private static void validateSinger(Singer singer, SingerValidationService service) {
        Set<ConstraintViolation<Singer>> violations = service.validateSinger(singer);
        listViolations(violations);
    }

    private static void listViolations(Set<ConstraintViolation<Singer>> violations) {
        logger.info("No. of violations: " + violations.size());
        for (ConstraintViolation<Singer> violation : violations) {
            logger.info("Validation error for property: "
                    + violation.getPropertyPath()
                    + " with value: "
                    + violation.getInvalidValue()
                    + " with error message: " + violation.getMessage());
        }
    }
}
