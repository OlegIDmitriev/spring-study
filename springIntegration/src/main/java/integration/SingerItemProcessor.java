package integration;

import batch.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component("itemProcessor")
public class SingerItemProcessor implements ItemProcessor<Singer, Singer> {
    private static Logger logger = LoggerFactory.getLogger(SingerItemProcessor.class);

    @Override
    public Singer process(Singer singer) throws Exception {
        String firstName = singer.getFirstName().toUpperCase();
        String lastName = singer.getLastName().toUpperCase();
        String song = singer.getSong().toUpperCase();

        Singer transformSinger = new Singer();
        transformSinger.setFirstName(firstName);
        transformSinger.setLastName(lastName);
        transformSinger.setSong(song);

        logger.info("Transformed singer: " + singer + " Into: " + transformSinger);

        return transformSinger;
    }
}
