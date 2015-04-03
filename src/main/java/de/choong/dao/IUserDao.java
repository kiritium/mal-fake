package de.choong.dao;

import de.choong.exceptions.DBException;
import de.choong.model.user.UserDO;

public interface IUserDao extends ICrudDao<UserDO> {

    public UserDO readByName(String name) throws DBException;

}
