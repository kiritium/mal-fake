package de.choong.dao;

import java.util.List;

import de.choong.exceptions.DBException;
import de.choong.model.AnimeDO;

public interface IAnimeDao extends ICrudDao<AnimeDO> {

    public List<AnimeDO> readAll() throws DBException;

}
