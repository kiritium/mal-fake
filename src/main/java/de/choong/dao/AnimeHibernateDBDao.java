package de.choong.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import de.choong.exceptions.DBException;
import de.choong.model.AnimeDO;
import de.choong.util.HibernateUtil;

public class AnimeHibernateDBDao implements IAnimeDao {

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
    
    public long countAll() {
    	Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        long count = (Long) session.createCriteria(AnimeDO.class).setProjection(Projections.rowCount()).uniqueResult();

        session.flush();
        tx.commit();
        return count;
    }
    
    public List<AnimeDO> readWithLimit(int first, int max) {
    	Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        @SuppressWarnings("unchecked")
		List<AnimeDO> animes = session.createCriteria(AnimeDO.class)
			.setFirstResult(first)
			.setMaxResults(max)
			.list();

        session.flush();
        tx.commit();
    	return animes;
    }
    
    public List<AnimeDO> readWithLimit(int first, int max, Order order) {
    	Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        @SuppressWarnings("unchecked")
		List<AnimeDO> animes = session.createCriteria(AnimeDO.class)
			.setFirstResult(first)
			.setMaxResults(max)
			.addOrder(order)
			.list();

        session.flush();
        tx.commit();
    	return animes;
    }
}
