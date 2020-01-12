package ch10.config;

import ch10.convert.StringToDateTimeConverter;
import ch10.entities.Singer;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.converter.Converter;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ch10")
public class AppConfig {
    @Value("${date.format.pattern}")
    private String dateFormatPattern;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Singer john(@Value("${countrySinger.firstName}") String firstName,
                       @Value("${countrySinger.lastName}") String lastName,
                       @Value("${countrySinger.birthDate}") DateTime birthDate,
                       @Value("${countrySinger.personalSite}") URL personalSite) throws Exception {
        Singer singer = new Singer();
        singer.setFirstName(firstName);
        singer.setLastName(lastName);
        singer.setBirthDate(birthDate);
        singer.setPersonalSite(personalSite);

        return singer;
    }

    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(converter());
        conversionServiceFactoryBean.setConverters(converters);
        conversionServiceFactoryBean.afterPropertiesSet();

        return conversionServiceFactoryBean;
    }

    @Bean
    StringToDateTimeConverter converter() {
        StringToDateTimeConverter converter = new StringToDateTimeConverter();
        converter.setDatePattern(dateFormatPattern);
        converter.init();

        return converter;
    }
}
