package ch10.config;

import ch10.convert.SingerToAnotherSingerConverter;
import ch10.convert.StringToDateTimeConverter;
import ch10.entities.Singer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Configuration
@ComponentScan(basePackages = "ch10")
public class AnotherSingerAppConfig {

    @Bean
    public Singer john() throws Exception {
        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(converter().convert("1977-10-16"));
        singer.setPersonalSite(new URL("http://johnmayer.com/"));

        return singer;
    }

    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(converter());
        converters.add(singerConverter());
        conversionServiceFactoryBean.setConverters(converters);
        conversionServiceFactoryBean.afterPropertiesSet();

        return conversionServiceFactoryBean;
    }

    @Bean
    StringToDateTimeConverter converter() {
        return new StringToDateTimeConverter();
    }

    @Bean
    SingerToAnotherSingerConverter singerConverter() {
        return new SingerToAnotherSingerConverter();
    }
}
