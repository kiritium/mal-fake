package de.choong.dao.lambda;

import org.hibernate.Session;
import org.hibernate.Transaction;

import de.choong.util.HibernateUtil;

public class ExecuteInTransaction {

	public static void run(ExecutableWithoutReturn action) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();

		// TODO if hibernate fails to insert the transaction is never closed, as
		// a consequence all following request are blocked
		action.execute(session);

		session.flush();
		tx.commit();
	}

	public static <T> T get(ExecutableWithReturn<T> action) {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tx = session.beginTransaction();

		// TODO if hibernate fails to insert the transaction is never closed, as
		// a consequence all following request are blocked
		T result = action.execute(session);

		session.flush();
		tx.commit();

		return result;
	}
}
