package de.choong.util;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.EmptyInterceptor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import de.choong.model.BaseDO;

/**
 * Util to access DB operations.
 *
 */
public class HibernateUtil {
    private static ThreadLocal<SessionFactory> sessionFactory = new ThreadLocal<SessionFactory>();
    private static EntityManagerFactory entityManagerFactory;

    /**
     * Creates SessionFactory from hibernate.cfg.xml.
     * 
     * @return
     * @throws HibernateException
     */
    public static SessionFactory getSessionFactory() throws HibernateException {
        if (sessionFactory.get() == null) {
            Configuration conf = new Configuration().configure().setInterceptor(
                    new AuditInterceptor());
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    conf.getProperties()).build();
            sessionFactory.set(conf.buildSessionFactory(serviceRegistry));
        }
        return sessionFactory.get();
    }

    public static EntityManager getEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("myManager");
        return entityManagerFactory.createEntityManager();
    }

    /**
     * Simple way to access a DB session.
     * 
     * @return
     */
    public static Session getSession() {
        return getSessionFactory().getCurrentSession();
    }

    /**
     * TODO bduong: this code does need some revision
     */
    private static class AuditInterceptor extends EmptyInterceptor {
        private static final long serialVersionUID = 1094556730902967262L;

        public AuditInterceptor() {
            super();
        }

        @Override
        public boolean onSave(Object entity, Serializable id, Object[] state,
                String[] propertyNames, Type[] types) {
            if (entity instanceof BaseDO) {
                BaseDO e = (BaseDO) entity;
                e.onCreate();
                System.out.println("created");
            }
            return super.onSave(entity, id, state, propertyNames, types);
        }

        @Override
        public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState,
                Object[] previousState, String[] propertyNames, Type[] types) {
            if (entity instanceof BaseDO) {
                BaseDO e = (BaseDO) entity;
                e.onUpdate();
                System.out.println("update");
            }
            return super
                    .onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
        }
    }
}
