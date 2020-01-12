package ch7.dao;

import ch7.config.AdvancedConfig;
import ch7.config.AppConfig;
import ch7.entities.Album;
import ch7.entities.Instrument;
import ch7.entities.Singer;
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

public class SingerDaoImplTest {
    private static Logger logger = LoggerFactory.getLogger(SingerDaoImplTest.class);

    private GenericApplicationContext ctx;
    private SingerDao singerDao;

    @Before
    public void setUp() throws Exception {
        ctx = new AnnotationConfigApplicationContext(AdvancedConfig.class);
        singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
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

        singerDao.save(singer);
        assertNotNull(singer.getId());

        List<Singer> singers = singerDao.findAllWithAlbum();
        assertEquals(4, singers.size());
        listSingersWithAlbum(singers);
    }

    @Test
    public void testFindAll() {
        List<Singer> singers = singerDao.findAll();
        assertEquals(3, singers.size());
        listSingers(singers);
    }

    @Test
    public void testFindAllWithAlbum() {
        List<Singer> singers = singerDao.findAllWithAlbum();
        assertEquals(3, singers.size());
        listSingersWithAlbum(singers);
    }

    @Test
    public void testFindById() {
        Singer singer = singerDao.findById(1L);
        assertNotNull(singer);
        logger.info(singer.toString());
    }

    @Test
    public void testUpdate() {
        Singer singer = singerDao.findById(1L);
        assertNotNull(singer);

        assertEquals("Mayer", singer.getLastName());
        Album album = singer.getAlbums().stream().filter(
                a -> a.getTitle().equals("Battle Studies")
        ).findFirst().get();

        singer.setFirstName("John Clayton");
        singer.removeAlbum(album);
        singerDao.save(singer);

        listSingersWithAlbum(singerDao.findAllWithAlbum());
    }

    @Test
    public void testDelete() {
        Singer singer = singerDao.findById(2L);
        assertNotNull(singer);
        singerDao.delete(singer);

        listSingersWithAlbum(singerDao.findAllWithAlbum());
    }

    private void listSingers(List<Singer> singers) {
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