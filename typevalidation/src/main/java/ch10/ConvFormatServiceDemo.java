package ch10;

import ch10.config.FormatterConfig;
import ch10.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.convert.ConversionService;

public class ConvFormatServiceDemo {
    private static Logger logger = LoggerFactory.getLogger(ConvFormatServiceDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(FormatterConfig.class);
        Singer john = ctx.getBean("john", Singer.class);
        logger.info("Singer info: " + john);

        ConversionService conversionService = ctx.getBean("conversionService", ConversionService.class);
        logger.info("Birthdate of singer is: " + conversionService.convert(john.getBirthDate(), Singer.class));

        ctx.close();
    }
}
