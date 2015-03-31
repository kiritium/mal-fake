package de.choong.mockup;

import java.util.ArrayList;

import de.choong.model.AnimeDO;

public class AnimeDB {
	
	private static ArrayList<AnimeDO> db = new ArrayList<AnimeDO>();
	private static int idCounter = 2;
	
	static {
		db.add(new AnimeDO(1, "Kiseijuu", 2014, "Tom"));
		db.add(new AnimeDO(2, "Death Parade", 2015, "Decim"));
	}
	
	public AnimeDB() {
	    
	}
	
	public ArrayList<AnimeDO> get() {
	    return db;
	}

	public int getNewId() {
	    idCounter++;
	    return idCounter;
	}
}
