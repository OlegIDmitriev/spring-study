package ru.raiffesien.jtaboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.raiffesien.jtaboot.entities.Singer;
import ru.raiffesien.jtaboot.services.SingerService;

import java.util.Date;
import java.util.GregorianCalendar;

@SpringBootApplication(scanBasePackages = "ru.raiffesien.jtaboot.services")
public class Application implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx  = SpringApplication.run(Application.class, args);
        System.in.read();
        ctx.close();
    }

    @Autowired
    private SingerService singerService;

    @Override
    public void run(String... args) throws Exception {
        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(new Date(
                (new GregorianCalendar(1977, 9, 16)).getTime().getTime()));

        singerService.save(singer);

        long count = singerService.count();
        if (count == 1) {
            logger.info("--> Singer saved successfully");
        } else {
            logger.error("--> Singer was not saved, check the config!!1");
        }

        try {
            singerService.save(null);
        } catch (Exception ex) {
            logger.error(ex.getMessage() + "Final count: " + singerService.count());
        }
    }
}
