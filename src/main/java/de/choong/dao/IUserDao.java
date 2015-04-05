package de.choong.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import de.choong.exceptions.DBException;
import de.choong.model.user.UserDO;

public interface IUserDao extends ICrudDao<UserDO> {

    public UserDO readByName(String name) throws DBException;

    public List<UserDO> readWithLimit(int first, int max, Order order) throws DBException;

    public long countAll() throws DBException;
}
