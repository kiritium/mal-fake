package de.choong.dao;

import java.io.Serializable;

/**
 * Basic dao functionality.
 * 
 * CRUD:
 * C - Create
 * R - Read (or "find")
 * U - Update
 * D - Delete (or "remove")
 * 
 * @param <T> Datatype
 */
public interface ICrudDao<T> extends Serializable{

    public void create(T newObject);
    public T read(long id);
    public void update(T updatedObj);
    public void delete(long id);
    
}
