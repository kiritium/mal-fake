package de.choong.dao;

import de.choong.exceptions.DBException;
import de.choong.model.anime.CharacterDO;

public interface ICharacterDao extends ICrudDao<CharacterDO> {

    public CharacterDO readByName(String name) throws DBException;

    public long countAll() throws DBException;
}
