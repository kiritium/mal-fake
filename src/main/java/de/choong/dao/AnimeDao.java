package de.choong.dao;

import java.util.List;

import org.hibernate.NullPrecedence;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import de.choong.dao.lambda.ExecuteInTransaction;
import de.choong.exceptions.DBException;
import de.choong.model.anime.AnimeDO;

public class AnimeDao implements IAnimeDao {

    private static final long serialVersionUID = 6398704770406750368L;

    @Override
    public int create(AnimeDO newObject) throws DBException {
        return (int) ExecuteInTransaction.get(session -> session.save(newObject));
    }

    @Override
    public AnimeDO read(int id) throws DBException {
        return (AnimeDO) ExecuteInTransaction.get(session -> session.get(AnimeDO.class, id));
    }

    @Override
    public void update(AnimeDO updatedObj) throws DBException {
        ExecuteInTransaction.run(session -> session.update(updatedObj));
    }

    @Override
    public void delete(int id) throws DBException {
        AnimeDO anime = read(id);
        ExecuteInTransaction.run(session -> session.delete(anime));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AnimeDO> readAll() throws DBException {
        return ExecuteInTransaction.get(session -> session.createCriteria(AnimeDO.class).list());
    }

    @Override
    public long countAll() throws DBException {
        return (long) ExecuteInTransaction.get(session -> session.createCriteria(AnimeDO.class)
                .setProjection(Projections.rowCount()).uniqueResult());
    }

    @Override
    public List<AnimeDO> readWithLimit(int first, int max, Order order) throws DBException {
        @SuppressWarnings("unchecked")
        List<AnimeDO> animes = ExecuteInTransaction.get(session -> session
                .createCriteria(AnimeDO.class).setFirstResult(first).setMaxResults(max)
                .addOrder(order.ignoreCase().nulls(NullPrecedence.LAST)).list());
        return animes;
    }
}
