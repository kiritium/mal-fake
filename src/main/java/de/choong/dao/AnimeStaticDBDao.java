package de.choong.dao;

import java.util.ArrayList;

import de.choong.mockup.AnimeDB;
import de.choong.model.AnimeDO;

/**
 * Anime Dao that saves the Data in RAM (List).
 *
 */
public class AnimeStaticDBDao implements IAnimeDao<AnimeDO> {

    private ArrayList<AnimeDO> db;
    
    public AnimeStaticDBDao() {
        
    }
    
    public AnimeStaticDBDao(ArrayList<AnimeDO> db) {
        this.db = db;
    }
    
    private ArrayList<AnimeDO> getDB() {
        if (db == null) {
            return new AnimeDB().get();
        }
        return db;
    }
    
    @Override
    public void create(AnimeDO newObject) {
        ArrayList<AnimeDO> db = getDB();
        newObject.setId(new AnimeDB().getNewId());
        db.add(newObject);
    }

    @Override
    public AnimeDO read(long id) {
        ArrayList<AnimeDO> db = getDB();
        for (AnimeDO obj : db) {
            if (obj.getId() == id) {
                return obj;
            }
        }
        return null;
    }
    
    public ArrayList<AnimeDO> readAll() {
        return getDB();
    }

    @Override
    public void update(AnimeDO updatedObj) {
        if (read(updatedObj.getId()) != null) {
            delete(updatedObj.getId());
            create(updatedObj);
        }
    }

    @Override
    public void delete(long id) {
        ArrayList<AnimeDO> db = getDB();
        db.remove(read(id));
    }
    
}
