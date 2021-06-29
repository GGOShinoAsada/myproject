package config;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class HibConf {
    @Autowired
    private DataSource dataSource;
    @Resource
    private Environment environment;



    @Bean
    public SessionFactory sessionFactory() throws IOException {
        LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setPackagesToScan(new String[]{"com.zadanie.nadanieweb.model"});
        bean.setHibernateProperties(getProperties());
        bean.afterPropertiesSet();

        return bean.getObject();
    }
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
   /* @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(new String[]{"com.example.computerstore.model"});
        entityManagerFactoryBean.setJpaProperties(getProperties());

        return entityManagerFactoryBean;
    }*/
    private Properties getProperties(){

        Properties prop = new Properties();
        prop.setProperty("hibernate.hbm2ddl.auto", environment.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
        prop.setProperty("hibernate.dialect",environment.getRequiredProperty("spring.jpa.database-platform"));
        prop.setProperty("hibernate.USE_LEGACY_LIMIT_HANDLERS", environment.getRequiredProperty("spring.jpa.properties.hibernate.USE_LEGACY_LIMIT_HANDLERS"));
        return prop;
    }

}
