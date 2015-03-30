package de.choong.dao;

import java.util.ArrayList;

import de.choong.model.AnimeDO;

/**
 * Anime Dao that saves the data in a SQLite DB.
 * 
 */
public class AnimeSqliteDBDao implements IAnimeDao<AnimeDO> {

	@Override
	public void create(AnimeDO newObject) {
		// TODO
	}

	@Override
	public AnimeDO read(long id) {
		AnimeDO anime = new AnimeDO();
		
		// TODO
		
		return anime;
	}

	@Override
	public void update(AnimeDO updatedObj) {
		// TODO
	}

	@Override
	public void delete(long id) {
		// TODO
	}

	@Override
	public ArrayList<AnimeDO> readAll() {
		ArrayList<AnimeDO> animes = new ArrayList<>();
		
		// TODO
		
		return animes;
	}

	
	
}
