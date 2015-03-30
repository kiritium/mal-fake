package de.choong.dao;

import java.util.ArrayList;

public interface IAnimeDao<T> extends ICrudDao<T>{

	public ArrayList<T> readAll();
	
}
