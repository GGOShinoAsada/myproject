package com.company.project1.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.company.project1")
@PropertySource("classpath: db.properties")
public class DbConfig {
    @Resource
    Environment env;
    @Autowired
    private void setInveroment(Environment env){
        this.env = env;
    }
    @Resource
    private Environment environment;
    @Bean
    public DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));

        return dataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean factory(){
        Properties properties = new Properties();
        try{
            InputStream fis = getClass().getClassLoader().getResourceAsStream("classpath:db.properties");
            properties.load(fis);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        LocalContainerEntityManagerFactoryBean f = new LocalContainerEntityManagerFactoryBean();
        f.setDataSource(getDataSource());
        f.setPackagesToScan(environment.getActiveProfiles());
        f.setPackagesToScan("com.company.project1");
        f.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        f.setJpaProperties(properties);

        return f;
    }
    @Bean
    public PlatformTransactionManager manager(){
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setDataSource(getDataSource());
        manager.setJpaDialect(new HibernateJpaDialect());
        return manager;
    }

}
