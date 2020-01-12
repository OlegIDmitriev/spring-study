package implement;

import entity.Album;
import entity.Singer;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;
import plainJdbc.dao.SingerDAO;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnnotationJdbcTest {
    private GenericApplicationContext ctx;
    private SingerDAO singerDAO;

    @BeforeEach
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        singerDAO = ctx.getBean(SingerDAO.class);
        assertNotNull(singerDAO);
    }

    @Test
    public void testFindAll() {
        List<Singer> singers = singerDAO.findAll();
        assertTrue(singers.size() == 3);
        singers.forEach(singer -> {
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    System.out.println("\t--> " + album);
                }
            }
        });

        ctx.close();
    }

    @Test
    public void findByFirstName() {
        List<Singer> singers = singerDAO.findByFirstName("John");
        assertTrue(singers.size() == 2);
        listSingers(singers);
        ctx.close();
    }

    @Test
    public void testSingerUpdate() {
        Singer singer = new Singer();
        singer.setId(1L);
        singer.setFirstName("John Clayton");
        singer.setLastName("Mayer");
        singer.setBirthDate(new Date(new GregorianCalendar(1977, 9, 16).getTime().getTime()));

        singerDAO.update(singer);
        List<Singer> singers = singerDAO.findAll();
        listSingers(singers);
    }

    @Test
    public void testSingerInsert() {
        Singer singer = new Singer();
        singer.setFirstName("Ed");
        singer.setLastName("Sheeran");
        singer.setBirthDate(new Date(new GregorianCalendar(1991, 1, 17).getTime().getTime()));

        singerDAO.insert(singer);
        List<Singer> singers = singerDAO.findAll();
        listSingers(singers);
    }

    private void listSingers(List<Singer> singers) {
        singers.forEach(singer -> {
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    System.out.println("\t--> " + album);
                }
            }
        });
    }

    @Test
    public void testSingerInsertWithAlbum() {
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date(new GregorianCalendar(1940, 8, 16).getTime().getTime()));

        Album album = new Album();
        album.setTitle("My Kind of Blues");
        album.setReleaseDate(new Date(new GregorianCalendar(1961, 3, 20).getTime().getTime()));
        singer.addAlbum(album);

        singerDAO.insertWithAlbum(singer);

        List<Singer> singers = singerDAO.findAllWithAlbums();
        listSingers(singers);
    }

    @Test
    public void testFindFirstNameById() {
        String firstName = singerDAO.findFirstNameById(2L);
        assertEquals("Eric", firstName);
        System.out.println("Retrieved value: " + firstName);
    }

    @AfterEach
    public void tearDown() {
        ctx.close();
    }

}
