package de.choong.dao;

import de.choong.exceptions.DBException;
import de.choong.model.UserDO;

public interface IUserDao extends ICrudDao<UserDO> {

    public boolean login(UserDO user) throws DBException;

    public void logout() throws DBException;

    public UserDO readByName(String name) throws DBException;

}
