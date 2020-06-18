package util;

import lombok.extern.slf4j.Slf4j;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DBHelper {

    private static SessionFactory sessionFactory;


    private DBHelper() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    /**
     * Creates new connection for jdbc dao implementation
     *
     * @return connection
     */
    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();
            url.
                    append(PropertyReader.getProperty("connection.url")).
                    append("?user=").
                    append(PropertyReader.getProperty("connection.username")).
                    append("&password=").
                    append(PropertyReader.getProperty("connection.password")).
                    append("&serverTimezone=UTC");


            log.info("URL: " + url + "\n");

            return DriverManager.getConnection(url.toString());
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            log.warn(e.getMessage());
            throw new IllegalStateException();
        }
    }


    @SuppressWarnings("UnusedDeclaration")
    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", PropertyReader.getProperty("hibernate.dialect"));
        configuration.setProperty("hibernate.connection.driver_class", PropertyReader.getProperty("hibernate.connection.driver_class"));
        configuration.setProperty("hibernate.connection.url", PropertyReader.getProperty("connection.url"));
        configuration.setProperty("hibernate.connection.username", PropertyReader.getProperty("connection.username"));
        configuration.setProperty("hibernate.connection.password", PropertyReader.getProperty("connection.password"));
        configuration.setProperty("hibernate.show_sql", PropertyReader.getProperty("hibernate.show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", PropertyReader.getProperty("hibernate.hbm2ddl.auto"));
        return configuration;
    }


    private static SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

}
