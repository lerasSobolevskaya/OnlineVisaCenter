package visa.center.config;

import static org.hibernate.cfg.AvailableSettings.*;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@ComponentScan(basePackages = { "visa.center.dao", "visa.center.service" })
public class AppConfig {

	private Environment environment;

	public AppConfig(Environment environment) {
		this.environment = environment;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		Properties properties = new Properties();

		properties.put(SHOW_SQL, environment.getProperty("hibernate.show_sql"));
		properties.put(FORMAT_SQL, environment.getProperty("hibernate.format_sql"));
		properties.put(USE_SQL_COMMENTS, environment.getProperty("hibernate.use_sql_comments"));

		properties.put(C3P0_MIN_SIZE, environment.getProperty("hibernate.c3p0.min_size"));
		properties.put(C3P0_MAX_SIZE, environment.getProperty("hibernate.c3p0.max_size"));
		properties.put(C3P0_ACQUIRE_INCREMENT, environment.getProperty("hibernate.c3p0.acquire_increment"));
		properties.put(C3P0_TIMEOUT, environment.getProperty("hibernate.c3p0.timeout"));
		properties.put(C3P0_MAX_STATEMENTS, environment.getProperty("hibernate.c3p0.max_statements"));

		factoryBean.setDataSource(dataSource());
		factoryBean.setHibernateProperties(properties);
		factoryBean.setPackagesToScan("visa.center.model");
		return factoryBean;
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getProperty("com.mysql.cj.jdbc.Driver"));
		dataSource.setUrl(environment.getProperty("mysql.url"));
		dataSource.setUsername(environment.getProperty("mysql.username"));
		dataSource.setPassword(environment.getProperty("mysql.password"));
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

}
