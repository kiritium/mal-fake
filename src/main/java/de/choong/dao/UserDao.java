package de.choong.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import de.choong.exceptions.DBException;
import de.choong.model.UserDO;
import de.choong.util.HibernateUtil;

public class UserDao implements IUserDao {

    private static final long serialVersionUID = -2539560151301942214L;

    @Override
    public void create(UserDO newObject) throws DBException {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        session.save(newObject);

        session.flush();
        tx.commit();
    }

    @Override
    public UserDO read(int id) throws DBException {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        UserDO user = (UserDO) session.get(UserDO.class, id);

        session.flush();
        tx.commit();

        return user;
    }

    @Override
    public void update(UserDO updatedObj) throws DBException {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        session.update(updatedObj);

        session.flush();
        tx.commit();
    }

    @Override
    public void delete(int id) throws DBException {
        UserDO user = read(id);
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        session.delete(user);

        session.flush();
        tx.commit();
    }

    @Override
    public boolean login(UserDO user) throws DBException {

        return false;
    }

    @Override
    public void logout() throws DBException {
        // TODO Auto-generated method stub
    
    }

    @Override
    public UserDO readByName(String name) throws DBException {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = session.beginTransaction();

        @SuppressWarnings("unchecked")
		List<UserDO> result = session.createCriteria(UserDO.class)
			.add(Restrictions.eqOrIsNull("username", name))
			.list();

        session.flush();
        tx.commit();

        if(result.isEmpty()) {
        	return null;
        }
    	return result.get(0);
    }

}
