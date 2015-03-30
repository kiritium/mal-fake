package de.choong.dao;

public interface DaoInterface<T> {

    public void create(T newObject);
    public T read(long id);
    public void update(T updatedObj);
    public void delete(long id);
    
}
