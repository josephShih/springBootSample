package com.springBoot.sample.demo.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "writeEntityManagerFactory",
transactionManagerRef = "writeTransactionManager",
basePackages = {"com.springBoot.sample.demo.repository.sampleDB.write"} )
@Configuration
public class SimpleWriteDataSourceConfig {
	
	
	
	@Primary
	@Bean(name="writeDataSource")
	public DataSource getDataSource(@Qualifier("spring.datasource-org.springframework.boot.autoconfigure.jdbc.DataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder()
				.driverClassName("com.mysql.cj.jdbc.Driver")
				.url("jdbc:mysql://localhost:3306/passport")
				.username("test").password("test").build();
	}
	
	@Primary
	@Bean(name="writeEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean writeEntityManagerFactory(EntityManagerFactoryBuilder builder,@Qualifier("writeDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.springBoot.sample.demo.repository.sampleDB.model").persistenceUnit("simpleWrite").build();
	}
	
	@Primary
	@Bean(name="writeTransactionManager")
	public JpaTransactionManager writeTransactionManager(@Qualifier("writeEntityManagerFactory") EntityManagerFactory entyEntityManagerFactory) {
		return new JpaTransactionManager(entyEntityManagerFactory);
	}
	
	@Bean(name="writeJdbcTemplate")
	public JdbcTemplate writeJdbcTemplate(@Qualifier("writeDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
		
	}
}
