package de.choong.dao;

import java.util.List;

import org.hibernate.NullPrecedence;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import de.choong.dao.lambda.ExecuteInTransaction;
import de.choong.exceptions.DBException;
import de.choong.model.user.UserDO;

public class UserDao implements IUserDao {

    private static final long serialVersionUID = -2539560151301942214L;

    @Override
    public void create(UserDO newObject) throws DBException {
        ExecuteInTransaction.run(session -> session.save(newObject));
    }

    @Override
    public UserDO read(int id) throws DBException {
        return (UserDO) ExecuteInTransaction.get(session -> session.get(UserDO.class, id));
    }

    @Override
    public void update(UserDO updatedObj) throws DBException {
        ExecuteInTransaction.run(session -> session.update(updatedObj));
    }

    @Override
    public void delete(int id) throws DBException {
        UserDO user = read(id);
        ExecuteInTransaction.run(session -> session.delete(user));
    }

    @Override
    public UserDO readByName(String name) throws DBException {
        @SuppressWarnings("unchecked")
        List<UserDO> result = ExecuteInTransaction
                .get(session -> session.createCriteria(UserDO.class)
                        .add(Restrictions.eqOrIsNull("username", name)).list());

        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    @Override
    public List<UserDO> readWithLimit(int first, int max, Order order) {
        @SuppressWarnings("unchecked")
        List<UserDO> users = ExecuteInTransaction.get(session -> session
                .createCriteria(UserDO.class).setFirstResult(first).setMaxResults(max)
                .addOrder(order.ignoreCase().nulls(NullPrecedence.LAST)).list());
        return users;
    }

    @Override
    public long countAll() {
        return (long) ExecuteInTransaction.get(session -> session.createCriteria(UserDO.class)
                .setProjection(Projections.rowCount()).uniqueResult());
    }
}
