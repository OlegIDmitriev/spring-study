package jdbctemplate;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import sources.SingerDao;

import javax.sql.DataSource;

public class JdbcSingerDao implements SingerDao, InitializingBean {
    private JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (jdbcTemplate == null) {
            throw new BeanCreationException("Must set jdbcTemplate on SingerDao");
        }
    }

    @Override
    public String findNameById(Long id) {
        return jdbcTemplate.queryForObject("select first_name||' '||last_name from Singer " +
                "where id=?", new Object[]{id}, String.class);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
