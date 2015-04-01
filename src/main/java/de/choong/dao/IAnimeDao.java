package de.choong.dao;

import java.util.ArrayList;

import de.choong.exceptions.DBException;

public interface IAnimeDao<T> extends ICrudDao<T>{

	public ArrayList<T> readAll() throws DBException;
	
}
