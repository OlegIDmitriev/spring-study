package sources.impl;

import exception.MySQLErrorCodesTranslator;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import sources.SingerDao;

import javax.sql.DataSource;

public class JdbcSingerDao implements SingerDao, InitializingBean {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        MySQLErrorCodesTranslator errorTranslator = new MySQLErrorCodesTranslator();
        errorTranslator.setDataSource(dataSource);
        jdbcTemplate.setExceptionTranslator(errorTranslator);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new BeanCreationException("Must set dataSource on SingerDao");
        }
    }

    @Override
    public String findNameById(Long id) {
        return null;
    }
}
