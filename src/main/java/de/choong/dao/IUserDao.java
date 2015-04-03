package de.choong.dao;

import de.choong.exceptions.DBException;
import de.choong.model.UserDO;

public interface IUserDao extends ICrudDao<UserDO> {

    public UserDO readByName(String name) throws DBException;

}
