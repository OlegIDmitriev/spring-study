package ch9.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.postgresql.xa.PGXADataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@EnableJpaRepositories
@SuppressWarnings(value = "unchecked")
public class XAJpaConfig {
    private static Logger logger = LoggerFactory.getLogger(XAJpaConfig.class);

    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSourceA() {
        try {
            PGXADataSource pgDataSource = new PGXADataSource();
            pgDataSource.setUrl("jdbc:postgresql://localhost:5432/musicdb_a");
            pgDataSource.setUser("prospring5_a");
            pgDataSource.setPassword("prospring5_a");
            //.setCurrentSchema("musicdb_a");

            AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
            dataSource.setUniqueResourceName("XADBMSA");
            dataSource.setXaDataSource(pgDataSource);
            dataSource.setLocalTransactionMode(true);
            dataSource.setPoolSize(1);

            return dataSource;
        } catch (Exception ex) {
            logger.error("Populator DataSource bean cannot be created!", ex);
            return null;
        }
    }

    @Bean
    public Properties xaAProperties() {
        Properties xaProp = new Properties();

        xaProp.put("databaseName", "musicdb_a");

        return xaProp;
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSourceB() {
        try {
            PGXADataSource pgDataSource = new PGXADataSource();
            pgDataSource.setUrl("jdbc:postgresql://localhost:5432/musicdb_b");
            pgDataSource.setUser("prospring5_b");
            pgDataSource.setPassword("prospring5_b");
            //pgDataSource.setCurrentSchema("musicdb_b");

            AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
            dataSource.setUniqueResourceName("XADBMSB");
            dataSource.setXaDataSource(pgDataSource);
            dataSource.setLocalTransactionMode(true);
            dataSource.setPoolSize(1);

            return dataSource;
        } catch (Exception ex) {
            logger.error("Populator DataSource bean cannot be created!", ex);
            return null;
        }
    }

    @Bean
    public Properties xaBProperties() {
        Properties xaProp = new Properties();
        //xaProp.put("url", "jdbc:postgresql://localhost:5432/postgres");
        xaProp.put("databaseName", "musicdb_b");
        //xaProp.put("user", "prospring5_b");
        //xaProp.put("password", "prospring5_b");

        return xaProp;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.transaction.factory_class", "org.hibernate.transaction.JTATransactionFactory");
        hibernateProp.put(JTA_PLATFORM, "com.atomikos.icatch.jta.hibernate4.AtomikosPlatform");
        // required by Hibernate 5
        hibernateProp.put(TRANSACTION_COORDINATOR_STRATEGY, "jta");
        hibernateProp.put(CURRENT_SESSION_CONTEXT_CLASS, "jta");

        hibernateProp.put(AUTOCOMMIT, false);
        hibernateProp.put(FLUSH_BEFORE_COMPLETION, false);
        hibernateProp.put(DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");
        // this will work only if users/schemas are created first, use ddl.sql script for this
        hibernateProp.put(HBM2DDL_AUTO, "create-drop");
        hibernateProp.put(SHOW_SQL, true);
        hibernateProp.put(MAX_FETCH_DEPTH, 3);
        hibernateProp.put(STATEMENT_BATCH_SIZE, 10);
        hibernateProp.put(STATEMENT_FETCH_SIZE, 50);

        return hibernateProp;
    }

    @Bean
    public EntityManagerFactory emfA() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("ch9.entities");
        factoryBean.setDataSource(dataSourceA());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setPersistenceUnitName("emfA");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

    @Bean
    public EntityManagerFactory emfB() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("ch9.entities");
        factoryBean.setDataSource(dataSourceB());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setPersistenceUnitName("emfB");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.afterPropertiesSet();

        return factoryBean.getObject();
    }

}
