package de.choong.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import de.choong.exceptions.DBException;
import de.choong.model.AnimeDO;

public class AnimeHibernateDBDao implements IAnimeDao {

    private static final long serialVersionUID = 6398704770406750368L;

    private static SessionFactory sessionFactory;

    /**
     * Creates SessionFactory from hibernate.cfg.xml
     * 
     * @return
     * @throws HibernateException
     */
    private static SessionFactory getSessionFactory() throws HibernateException {
        if (sessionFactory == null) {
            Configuration conf = new Configuration().configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    conf.getProperties()).build();
            sessionFactory = conf.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

    @Override
    public void create(AnimeDO newObject) throws DBException {
        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        session.save(newObject);

        session.flush();
        tx.commit();
    }

    @Override
    public AnimeDO read(int id) throws DBException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(AnimeDO updatedObj) throws DBException {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(int id) throws DBException {
        // TODO Auto-generated method stub

    }

    @Override
    public List<AnimeDO> readAll() throws DBException {
        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<AnimeDO> animes = session.createCriteria(AnimeDO.class).list();

        session.flush();
        tx.commit();
        return animes;
    }

}
