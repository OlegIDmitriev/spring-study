package ch8.service;

import ch8.config.JpaConfig;
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

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class SingerServiceImplTest {
    private static Logger logger = LoggerFactory.getLogger(SingerServiceImplTest.class);
    private GenericApplicationContext ctx;
    private SingerService singerService;

    @Before
    public void setUp() throws Exception {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        singerService = ctx.getBean(SingerService.class);
        assertNotNull(singerService);
    }

    @Test
    public void findAll() {
        List<Singer> singers = singerService.findAll();
        assertEquals(3, singers.size());
        listSingers(singers);
    }

    @Test
    public void findAllWithNative() {
        List<Singer> singers = singerService.findAllByNativeQuery();
        assertEquals(3, singers.size());
        listSingers(singers);
    }

    @Test
    public void findAllWithNative2() {
        List<Singer> singers = singerService.findAllByNativeQuery2();
        assertEquals(3, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFindAllWithAlbum() {
        List<Singer> singers = singerService.findAllWithAlbum();
        assertEquals(3, singers.size());
        listSingersWithAlbum(singers);
    }

    @Test
    public void testInsert() {
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date(new GregorianCalendar(1940, 8, 16).getTime().getTime()));

        Album album = new Album();
        album.setTitle("My Kind of Blues");
        album.setReleaseDate(new Date(new GregorianCalendar(1961, 7, 18).getTime().getTime()));
        singer.addAbum(album);

        album = new Album();
        album.setTitle("A Heart full of Blues");
        album.setReleaseDate(new Date(new GregorianCalendar(1962, 3, 20).getTime().getTime()));
        singer.addAbum(album);

        singerService.save(singer);
        assertNotNull(singer.getId());

        List<Singer> singers = singerService.findAllWithAlbum();
        assertEquals(4, singers.size());
        listSingersWithAlbum(singers);
    }

    @Test
    public void testUpdate() {
        Singer singer = singerService.findById(1L);
        assertNotNull(singer);
        assertEquals("Mayer", singer.getLastName());

        Album album = singer.getAlbums().stream().filter(
                a -> a.getTitle().equals("Battle Studies")
        ).findFirst().get();

        singer.setFirstName("John Clayton");
        singer.removeAlbum(album);
        singerService.save(singer);
        listSingersWithAlbum(singerService.findAllWithAlbum());
    }

    @Test
    public void testDelete() {
        Singer singer = singerService.findById(2L);
        assertNotNull(singer);
        singerService.delete(singer);
        listSingersWithAlbum(singerService.findAllWithAlbum());
    }

    @Test
    public void findByCriteriaQuery() {
        List<Singer> singers = singerService.findByCriteriaQuery("John", "Mayer");
        assertEquals(1, singers.size());
        listSingersWithAlbum(singers);
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