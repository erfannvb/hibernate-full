package nvb.dev.util;

import nvb.dev.model.Athlete;
import nvb.dev.model.Person;
import nvb.dev.model.Programmer;
import nvb.dev.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

import static nvb.dev.constant.DbConstant.*;

public class HibernateUtil {

    private HibernateUtil() {
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                Configuration configuration = getConfiguration();

                configuration.addAnnotatedClass(Person.class);
                configuration.addAnnotatedClass(Athlete.class);
                configuration.addAnnotatedClass(Programmer.class);
                configuration.addAnnotatedClass(Student.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (HibernateException e) {
                throw new HibernateException("Something went wrong!");
            }
        }
        return sessionFactory;
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();

        Properties properties = new Properties();
        properties.put(AvailableSettings.DRIVER, DB_DRIVER);
        properties.put(AvailableSettings.URL, DB_URL);
        properties.put(AvailableSettings.USER, DB_USER);
        properties.put(AvailableSettings.PASS, DB_PASS);
        properties.put(AvailableSettings.POOL_SIZE, DB_POOL_SIZE);
        properties.put(AvailableSettings.HBM2DDL_AUTO, DB_HBM2DDL_AUTO);
        properties.put(AvailableSettings.SHOW_SQL, DB_SHOW_SQL);
        properties.put(AvailableSettings.DIALECT, DB_DIALECT);

        configuration.setProperties(properties);
        return configuration;
    }

}
