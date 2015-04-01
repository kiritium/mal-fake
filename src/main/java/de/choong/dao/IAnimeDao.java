package de.choong.dao;

import java.util.List;

import de.choong.exceptions.DBException;

public interface IAnimeDao<T> extends ICrudDao<T>{

	public List<T> readAll() throws DBException;
	
}
