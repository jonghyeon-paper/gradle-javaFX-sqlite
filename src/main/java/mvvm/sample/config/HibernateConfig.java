package mvvm.sample.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class HibernateConfig {
	
	// old config
//	@Bean
//	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		sessionFactory.setDataSource(dataSource);
//		sessionFactory.setPackagesToScan(new String[] {"mvvm.sample"});
//		sessionFactory.setHibernateProperties(hibernateProperties());
//		return sessionFactory;
//	}
//	
//	@Bean
//	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//		transactionManager.setSessionFactory(sessionFactory);
//		return transactionManager;
//	}
//	
//	@Bean
//	public HibernateTemplate geHibernateTemplate(SessionFactory sessionFactory) {
//		HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
//		return hibernateTemplate;
//	}
	
	// new config
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setPackagesToScan(new String[] {"mvvm.sample"});
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactoryBean.setJpaProperties(hibernateProperties());
		return entityManagerFactoryBean;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "com.enigmabridge.hibernate.dialect.SQLiteDialect");
		properties.put("hibernate.hbm2ddl.auto", "none");
		properties.put("hibernate.format_sql", true);
		properties.put("hibernate.show_sql", true);
		return properties;
	}
}