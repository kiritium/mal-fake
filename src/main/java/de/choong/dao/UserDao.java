package de.choong.dao;

import de.choong.exceptions.DBException;
import de.choong.model.UserDO;

public class UserDao implements IUserDao {

	private static final long serialVersionUID = -2539560151301942214L;

	@Override
	public void create(UserDO newObject) throws DBException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDO read(int id) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(UserDO updatedObj) throws DBException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DBException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean login(UserDO user) throws DBException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void logout() throws DBException {
		// TODO Auto-generated method stub
		
	}

}
