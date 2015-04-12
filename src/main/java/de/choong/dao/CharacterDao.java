package de.choong.dao;

import java.util.List;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import de.choong.dao.lambda.ExecuteInTransaction;
import de.choong.exceptions.DBException;
import de.choong.model.anime.CharacterDO;

public class CharacterDao implements ICharacterDao {

    private static final long serialVersionUID = -760130963063783989L;

    @Override
    public int create(CharacterDO newObject) throws DBException {
        return (int) ExecuteInTransaction.get(session -> session.save(newObject));
    }

    @Override
    public CharacterDO read(int id) throws DBException {
        return (CharacterDO) ExecuteInTransaction
                .get(session -> session.get(CharacterDO.class, id));
    }

    @Override
    public void update(CharacterDO updatedObj) throws DBException {
        ExecuteInTransaction.run(session -> session.update(updatedObj));
    }

    @Override
    public void delete(int id) throws DBException {
        CharacterDO character = read(id);
        ExecuteInTransaction.run(session -> session.delete(character));
    }

    @Override
    public CharacterDO readByName(String name) throws DBException {
        @SuppressWarnings("unchecked")
        List<CharacterDO> result = ExecuteInTransaction
                .get(session -> session.createCriteria(CharacterDO.class)
                        .add(Restrictions.eqOrIsNull("name", name)).list());

        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    @Override
    public long countAll() throws DBException {
        return (long) ExecuteInTransaction.get(session -> session.createCriteria(CharacterDO.class)
                .setProjection(Projections.rowCount()).uniqueResult());
    }

}
