package de.choong.dao;

import java.io.Serializable;

import de.choong.exceptions.DBException;

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

    public void create(T newObject) throws DBException;
    public T read(int id) throws DBException;
    public void update(T updatedObj) throws DBException;
    public void delete(int id) throws DBException;
    
}
