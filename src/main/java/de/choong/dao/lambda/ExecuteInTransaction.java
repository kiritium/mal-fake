package de.choong.dao.lambda;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.choong.exceptions.DBException;
import de.choong.util.HibernateUtil;

public class ExecuteInTransaction {

    public static void run(ExecutableWithoutReturn action) throws DBException {
        final Session session = HibernateUtil.getSession();
        final Transaction tx = session.beginTransaction();

        try {
            action.execute(session);
            session.flush();
            tx.commit();
        } catch (HibernateException ex) {
            tx.rollback();
            session.close();
            throw new DBException("", ex);
        }
    }

    public static <T> T get(ExecutableWithReturn<T> action) throws DBException {
        final Session session = HibernateUtil.getSession();
        final Transaction tx = session.beginTransaction();

        try {
            T result = action.execute(session);

            session.flush();
            tx.commit();

            return result;
        } catch (HibernateException ex) {
            tx.rollback();
            session.close();
            throw new DBException("", ex);
        }
    }
}
