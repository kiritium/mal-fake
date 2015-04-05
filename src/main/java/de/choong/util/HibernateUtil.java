package de.choong.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Util to access DB operations.
 *
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    /**
     * Creates SessionFactory from hibernate.cfg.xml.
     * 
     * @return
     * @throws HibernateException
     */
    public static SessionFactory getSessionFactory() throws HibernateException {
        if (sessionFactory == null) {
            Configuration conf = new Configuration().configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    conf.getProperties()).build();
            sessionFactory = conf.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

    /**
     * Simple way to access a DB session.
     * 
     * @return
     */
    public static Session getSession() {
        return getSessionFactory().openSession();
    }
}
