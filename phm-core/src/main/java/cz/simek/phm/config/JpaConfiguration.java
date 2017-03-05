package cz.simek.phm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by jenik on 31.1.17.
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:application.properties"})
//@ComponentScan(basePackages = "cz.simek.phm")
public class JpaConfiguration extends CommonConfig {

    private static final String CLOSE = "close";
    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan(new String[]{"cz.simek.phm.model"});
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties());
        return factoryBean;
    }


    /*
     * Provider specific adapter.
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        return hibernateJpaVendorAdapter;
    }

    /*
     * Here you can specify any provider specific properties.
     */
    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }

/*    private BoneCPDataSource datasourceCP() {
        BoneCPDataSource ds = new BoneCPDataSource();

        ds.setIdleMaxAge(getLong("ds.idle.max.age.mins"), TimeUnit.MINUTES);
        ds.setIdleConnectionTestPeriod(getLong("ds.idle.connection.test.period.mins"), TimeUnit.MINUTES);

        ds.setMaxConnectionsPerPartition(getInteger("ds.max.connections.per.partition"));
        ds.setMinConnectionsPerPartition(getInteger("ds.min.connections.per.partition"));

        ds.setPartitionCount(getInteger("ds.partition.count"));
        ds.setAcquireIncrement(getInteger("ds.acquire.increment"));
        ds.setStatementsCacheSize(getInteger("ds.statements.cache.size"));
        ds.setReleaseHelperThreads(getInteger("ds.release.helper.threads"));

        ds.setConnectionTestStatement(get("ds.connection.test.statement"));
        ds.setConnectionTimeout(getLong("ds.connection.timeout.millis"), TimeUnit.MILLISECONDS);

        ds.setDisableJMX(true);
        ds.setLazyInit(true);

        return ds;
    }*/

}
