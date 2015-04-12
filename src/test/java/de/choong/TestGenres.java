package de.choong;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.choong.dao.IAnimeDao;
import de.choong.exceptions.DBException;
import de.choong.model.anime.AnimeDO;
import de.choong.model.anime.Genre;
import de.choong.util.HibernateUtil;
import de.choong.util.SpringUtil;

public class TestGenres {

    private IAnimeDao dao = (IAnimeDao) SpringUtil.getBean("animeDao");

    @Test
    public void testAddGenres() {
        HibernateUtil.getSessionFactory();

        AnimeDO anime = new AnimeDO();
        anime.setTitle("TestGenre2");
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.ACTION);
        genres.add(Genre.ADVENTURE);
        anime.setGenres(genres);

        int id = -1;
        try {
            id = dao.create(anime);
        } catch (DBException e) {
            e.printStackTrace();
        }
        try {
            AnimeDO anime2 = dao.read(id);
            System.out.println(id);
        } catch (DBException e) {
            e.printStackTrace();
        }

    }
}
