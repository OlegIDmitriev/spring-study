package ch10;

import ch10.config.AnotherSingerAppConfig;
import ch10.entities.AnotherSinger;
import ch10.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MultipleConversionDemo {
    private static Logger logger = LoggerFactory.getLogger(MultipleConversionDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AnotherSingerAppConfig.class);

        Singer john = ctx.getBean("john", Singer.class);
        logger.info("Singer info: " + john);

        ConversionService conversionService = ctx.getBean(ConversionService.class);
        AnotherSinger anotherSinger = conversionService.convert(john, AnotherSinger.class);
        logger.info("Another singer info: " + anotherSinger);

        String[] strings = conversionService.convert("a,b,c", String[].class);
        logger.info("String array: " + strings[0] + " " + strings[1] + " " + strings[2]);

        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");

        Set<String> stringSet = conversionService.convert(stringList, HashSet.class);
        for (String string : stringSet) {
            logger.info("Set: " + string);
        }

    }
}
