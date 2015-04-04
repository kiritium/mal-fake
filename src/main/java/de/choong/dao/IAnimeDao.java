package de.choong.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import de.choong.exceptions.DBException;
import de.choong.model.anime.AnimeDO;

public interface IAnimeDao extends ICrudDao<AnimeDO> {

    public List<AnimeDO> readAll() throws DBException;

    public long countAll();

    public List<AnimeDO> readWithLimit(int first, int max, Order order);
}
