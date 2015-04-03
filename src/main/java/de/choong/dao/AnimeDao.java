package de.choong.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.choong.exceptions.DBException;
import de.choong.model.AnimeDO;
import de.choong.util.HibernateUtil;

public class AnimeDao implements IAnimeDao {

    private static final long serialVersionUID = 6398704770406750368L;

    @Override
    public void create(AnimeDO newObject) throws DBException {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        session.save(newObject);

        session.flush();
        tx.commit();
    }

    @Override
    public AnimeDO read(int id) throws DBException {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        AnimeDO anime = (AnimeDO) session.get(AnimeDO.class, id);

        session.flush();
        tx.commit();

        return anime;
    }

    @Override
    public void update(AnimeDO updatedObj) throws DBException {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        session.update(updatedObj);

        session.flush();
        tx.commit();
    }

    @Override
    public void delete(int id) throws DBException {
        AnimeDO anime = read(id);
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        session.delete(anime);

        session.flush();
        tx.commit();
    }

    @Override
    public List<AnimeDO> readAll() throws DBException {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<AnimeDO> animes = session.createCriteria(AnimeDO.class).list();

        session.flush();
        tx.commit();
        return animes;
    }
}
