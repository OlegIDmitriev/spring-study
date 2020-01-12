package ch10.config;

import ch10.entities.Singer;
import ch10.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.util.Locale;

@Configuration
@ComponentScan(basePackages = "ch10")
public class FormatterConfig {

    @Autowired
    ApplicationConversionServiceFactoryBean conversionService;

    @Bean
    public Singer john() throws Exception {
        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(conversionService.getDateTimeFormatter().parse("1977-10-16", Locale.ENGLISH));
        singer.setPersonalSite(new URL("http://johnmayer.com/"));

        return singer;
    }
}
