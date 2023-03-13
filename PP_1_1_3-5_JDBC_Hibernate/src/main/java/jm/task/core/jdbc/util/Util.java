package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/testdb?useSSL=false&allowMultiQueries=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

   private static SessionFactory sessionFactory = null;

   public static SessionFactory getConnection(){
       try{
           Properties properties = new Properties();
           properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
           properties.setProperty("hibernate.connection.url", URL);
           properties.setProperty("hibernate.connection.username", USERNAME);
           properties.setProperty("hibernate.connection.password", PASSWORD);
           properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

           Configuration cfg = new Configuration();
           cfg.setProperties(properties).addAnnotatedClass(User.class);

           ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                   .applySettings(cfg.getProperties()).build();

           sessionFactory = cfg.buildSessionFactory(serviceRegistry);

       } catch (Throwable e) {
           throw new ExceptionInInitializerError(e);
       }
       return sessionFactory;
   }
}
