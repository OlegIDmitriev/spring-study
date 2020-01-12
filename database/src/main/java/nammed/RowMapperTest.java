package nammed;

import entity.Album;
import entity.Singer;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import plainJdbc.dao.SingerDAO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RowMapperTest {
    @Test
    public void testRowMapper() {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(NamedJdbcCfg.class);
        SingerDAO singerDAO = ctx.getBean(SingerDAO.class);
        assertNotNull(singerDAO);

        List<Singer> singers = singerDAO.findAll();
        assertTrue(singers.size() == 3);

        singers.forEach(singer -> {
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    System.out.println("---" + album);
                }
            }
        });

        ctx.close();
    }
}
