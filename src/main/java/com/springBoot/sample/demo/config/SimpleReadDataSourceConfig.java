package com.springBoot.sample.demo.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "readEntityManagerFactory",
	transactionManagerRef = "readTransactionManager",
	basePackages = {"com.springBoot.sample.demo.repository.sampleDB.read"})
@Configuration
public class SimpleReadDataSourceConfig {

	@Bean(name="readDataSource")
	public DataSource getDataSource(@Qualifier("spring.datasource-org.springframework.boot.autoconfigure.jdbc.DataSourceProperties")DataSourceProperties readDataSourceProperties) {
		return readDataSourceProperties.initializeDataSourceBuilder()
				.driverClassName("com.mysql.cj.jdbc.Driver")
				.url("jdbc:mysql://localhost:3306/passport")
				.username("test").password("test").build();
	}
	
	@Bean(name="readEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean readEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("readDataSource")DataSource dataSource) {
		return builder.dataSource(dataSource)
				.packages("com.springBoot.sample.demo.repository.sampleDB.model")
				.persistenceUnit("simpleRead").build();
	}
	
	@Bean(name="readTransactionManager")
	public PlatformTransactionManager readTransactionManager(@Qualifier("readEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
	@Bean(name="readJdbcTemplate")
	public JdbcTemplate readJdbcTemplate(@Qualifier("readDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
