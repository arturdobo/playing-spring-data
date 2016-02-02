package com.github.arturdobo.playing.springdata.config;

import com.github.arturdobo.playing.springdata.persistence.repos.AbstractEntityJpaRepositoryImpl;
import com.github.arturdobo.playing.springdata.persistence.repos._ReposRoot;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@ComponentScan
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = _ReposRoot.class, repositoryBaseClass = AbstractEntityJpaRepositoryImpl.class)
public class JpaConfiguration {
	@Bean
	public EmbeddedDatabaseFactoryBean embeddedDatabaseFactoryBean() {
		EmbeddedDatabaseFactoryBean factoryBean = new EmbeddedDatabaseFactoryBean();
		factoryBean.setDatabaseType(EmbeddedDatabaseType.H2);

		return factoryBean;
	}

	@Bean(name = "entityManagerFactory")
	public AbstractEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setPackagesToScan("com.github.arturdobo.playing.springdata.persistence.model");
		factoryBean.setPersistenceProvider(new HibernatePersistenceProvider());
		factoryBean.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "create-drop");
		factoryBean.getJpaPropertyMap().put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

		return factoryBean;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
