package ch8.service;

import ch8.SingerService;
import ch8.config.DataJpaConfig;
import ch8.entities.Album;
import ch8.entities.Instrument;
import ch8.entities.Singer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class SingerServiceImplTest {
    private static Logger logger = LoggerFactory.getLogger(SingerServiceImpl.class);

    private GenericApplicationContext ctx;
    private SingerService singerService;

    @Before
    public void setUp() throws Exception {
        ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class);
        singerService = ctx.getBean(SingerService.class);
        assertNotNull(singerService);
    }

    @Test
    public void testFindAll() {
        List<Singer> singers = singerService.findAll();
        assertEquals(3, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFindByFirstName() {
        List<Singer> singers = singerService.findByFirstName("John");
        assertEquals(2, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFIndByFirstNameAndLastName() {
        List<Singer> singers = singerService.findByFirstNameAndLastName("John", "Mayer");
        assertEquals(1, singers.size());
        listSingers(singers);
    }

    private static void listSingers(List<Singer> singers) {
        logger.info(" --- Listing singers: ");
        for (Singer singer : singers) {
            logger.info(singer.toString());
        }
    }

    private void listSingersWithAlbum(List<Singer> singers) {
        logger.info(" --- Listing singers with instruments: ");
        for (Singer singer : singers) {
            logger.info(singer.toString());
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    logger.info("\t" + album.toString());
                }
            }
            if (singer.getInstruments() != null) {
                for (Instrument instrument : singer.getInstruments()) {
                    logger.info("\tInstrument: " + instrument.getInstrumentId());
                }
            }
        }
    }

    @After
    public void tearDown() throws Exception {
        ctx.close();
    }
}