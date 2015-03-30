package de.choong.dao;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.choong.model.AnimeDO;

public class DaoTest {

    private ArrayList<AnimeDO> testDB;
    private AnimeStaticDBDao dao;
    private AnimeDO dbAnime1 = new AnimeDO(1, "Kiseijuu", 2014, "Tom");
    private AnimeDO dbAnime2 = new AnimeDO(2, "Death Parade", 2015, "Decim");

    @Before
    public void setup() {
        testDB = new ArrayList<AnimeDO>();
        testDB.add(dbAnime1);
        testDB.add(dbAnime2);
        dao = new AnimeStaticDBDao(testDB);
    }

    @Test
    public void testCreate() {
        AnimeDO newAnime = new AnimeDO(1, "Anime", 2000, "Author");
        dao.create(newAnime);
        for (AnimeDO anime : testDB) {
            if (anime.getTitle().equals(newAnime.getTitle())) {
                assertAnimeEqualsWithoutId(newAnime, anime);
                Assert.assertEquals(3, anime.getId());
            }
        }
    }

    @Test
    public void testRead() {
        AnimeDO anime1 = dao.read(1);
        assertAnimeEquals(dbAnime1, anime1);
        AnimeDO anime2 = dao.read(2);
        assertAnimeEquals(dbAnime2, anime2);
        AnimeDO anime3 = dao.read(3);
        Assert.assertNull(anime3);
    }

    @Test
    public void testDelete() {
        int initialSize = testDB.size();
        dao.delete(1);
        for (AnimeDO anime : testDB) {
            if (anime.getId() == 1) {
                Assert.assertTrue(false);
            }
        }
        Assert.assertTrue(testDB.size() == initialSize - 1);
        dao.delete(2);
        for (AnimeDO anime : testDB) {
            if (anime.getId() == 2) {
                Assert.assertTrue(false);
            }
        }
        Assert.assertTrue(testDB.size() == initialSize - 2);
        dao.delete(3); // Element does not exist, this operation should be
                       // successful (no exception)
    }

    @Test
    public void testUpdate() {
        int initialSize = testDB.size();
        AnimeDO newAnime = new AnimeDO(2, "DP", 2015, "Decim");
        dao.update(newAnime);
        for (AnimeDO anime : testDB) {
            if (newAnime.getId() == anime.getId()) {
                assertAnimeEqualsWithoutId(newAnime, anime);
            }
            if (anime.getId() == 1) {
                assertAnimeEqualsWithoutId(anime, new AnimeDO(1, "Kiseijuu", 2014, "Tom"));
            }
        }
        Assert.assertTrue(initialSize == testDB.size());
    }

    public void assertAnimeEqualsWithoutId(AnimeDO newAnime, AnimeDO anime) {
        Assert.assertEquals(newAnime.getTitle(), anime.getTitle());
        Assert.assertEquals(newAnime.getYear(), anime.getYear());
        Assert.assertEquals(newAnime.getAuthor(), anime.getAuthor());
    }

    public void assertAnimeEquals(AnimeDO newAnime, AnimeDO anime) {
        Assert.assertEquals(newAnime.getId(), anime.getId());
        assertAnimeEqualsWithoutId(newAnime, anime);
    }

}
