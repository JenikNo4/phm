package cz.simek.phm.config.util;

import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by jenik on 28.1.17.
 */
public class RepositoryConfigUtil {

    public static LocalSessionFactoryBean getSessionFactory(Properties properties, DataSource ds,
                                                            String... packagesToScan) {
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(ds);
        lsfb.setHibernateProperties(properties);
        lsfb.setPackagesToScan(packagesToScan);

        return lsfb;
    }

    public static Properties getHibernateProperties(Environment env, String importSql) {
        Properties properties = getHibernateProperties(env);
        properties.put("hibernate.hbm2ddl.import_files", importSql);

        return properties;
    }

    public static Properties getHibernateProperties(Environment env) {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show.sql.enabled"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

        return properties;
    }
}