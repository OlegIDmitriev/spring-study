package nammed;

import entity.Singer;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import plainJdbc.dao.SingerDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NamedJdbcSingerDao implements SingerDAO, InitializingBean {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (jdbcTemplate == null) {
            throw new BeanCreationException("Must set jdbcTemplate on SingerDao");
        }
    }

    @Override
    public String findNameById(Long id) {
        String sql = "select first_name||' '||last_name from Singer where id = :singerId";
        Map<String, Object> namedParamaters = new HashMap<>();
        namedParamaters.put("singerId", id);

        return jdbcTemplate.queryForObject(sql, namedParamaters, String.class);
    }

    @Override
    public List<Singer> findAllWithAlbums() {
        String sql = "select s.id, s.first_name, s.last_name, s.birth_date, " +
                "a.id as \"album_id\", a.title, a.release_date from Singer s " +
                "left join album a on s.id = a.singer_id ";
        return jdbcTemplate.query(sql, new SingerWithDetailExtractor());
    }

    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Singer> findAll() {
        String sql = "select id, first_name, last_name, birth_date from Singer";
        return jdbcTemplate.query(sql, new SingerMapper());
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public String findLastNameById(Long id) {
        return null;
    }

    @Override
    public String findFirstNameById(Long id) {
        return null;
    }

    @Override
    public void insert(Singer singer) {

    }

    @Override
    public void update(Singer singer) {

    }

    @Override
    public void delete(Long singerId) {

    }


    @Override
    public void insertWithAlbum(Singer singer) {

    }

    private static final class SingerMapper implements RowMapper<Singer> {

        @Override
        public Singer mapRow(ResultSet rs, int i) throws SQLException {
            Singer singer = new Singer();
            singer.setId(rs.getLong("id"));
            singer.setFirstName(rs.getString("first_name"));
            singer.setLastName(rs.getString("last_name"));
            singer.setBirthDate(rs.getDate("birth_date"));

            return singer;
        }
    }

}
