package ch8.service;

import ch8.SingerService;
import ch8.config.DataJpaConfig;
import ch8.entities.Album;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class AlbumServiceImplTest {
    private static Logger logger = LoggerFactory.getLogger(AlbumServiceImplTest.class);

    private GenericApplicationContext ctx;
    private AlbumService albumService;

    @Before
    public void setUp() throws Exception {
        ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class);
        albumService = ctx.getBean(AlbumService.class);
        assertNotNull(albumService);
    }

    @Test
    public void testFindBySinger() {

    }

    @Test
    public void testFindByTitle() {
        List<Album> albums = albumService.findByTitle("The");
        assertEquals(2, albums.size());
        albums.forEach(a -> logger.info(a.toString() + ", Singer: " + a.getSinger().getFirstName() +
                " " + a.getSinger().getLastName()));
    }

    @After
    public void tearDown() throws Exception {
        ctx.close();
    }
}