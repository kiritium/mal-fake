package de.choong.dao;

import java.util.List;

import de.choong.dao.lambda.ExecuteInTransaction;
import de.choong.exceptions.DBException;
import de.choong.model.AnimeDO;

public class AnimeDao implements IAnimeDao {

	private static final long serialVersionUID = 6398704770406750368L;

	@Override
	public void create(AnimeDO newObject) throws DBException {
		ExecuteInTransaction.run(session -> session.save(newObject));
	}

	@Override
	public AnimeDO read(int id) throws DBException {
		return ExecuteInTransaction.get(session -> (AnimeDO) session.get(AnimeDO.class, id));
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
}
