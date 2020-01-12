package ru.raiffeisen.jdbcBoot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.raiffeisen.jdbcBoot.dao.SingerDao;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.raiffeisen.jdbcBoot.dao"})
public class JdbcBootApplication {
	private static Logger logger = LoggerFactory.getLogger(JdbcBootApplication.class);

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(JdbcBootApplication.class, args);
		assert (ctx != null);

		SingerDao singerDao = ctx.getBean(SingerDao.class);
		String singerName = singerDao.findNameById(1L);
		logger.info("Retrieved singer: " + singerName);

		System.in.read();
		ctx.close();
	}

}
