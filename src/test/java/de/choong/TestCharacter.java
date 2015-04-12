package de.choong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import de.choong.dao.IAnimeDao;
import de.choong.exceptions.DBException;
import de.choong.model.anime.AnimeDO;
import de.choong.model.anime.CharacterDO;
import de.choong.util.HibernateUtil;
import de.choong.util.SpringUtil;

public class TestCharacter {

    private IAnimeDao dao = (IAnimeDao) SpringUtil.getBean("animeDao");

    @Test
    public void testAddCharacter() {
        HibernateUtil.getSessionFactory();

        AnimeDO anime = new AnimeDO();
        anime.setTitle("TestGenre2");
        Set<CharacterDO> chars = new HashSet<>();
        CharacterDO char1 = new CharacterDO();
        char1.setName("character1");
        CharacterDO char2 = new CharacterDO();
        char2.setName("character2");
        chars.add(char1);
        chars.add(char2);
        anime.setCharacters(chars);

        int id = -1;
        try {
            id = dao.create(anime);
        } catch (DBException e) {
            e.printStackTrace();
        }
        try {
            AnimeDO anime2 = dao.read(id);
            Set<CharacterDO> chars2 = anime2.getCharacters();
            for (CharacterDO character : chars2) {
                System.out.printf("Name: %s\n", character.getName());
            }

            List<CharacterDO> x = new ArrayList<CharacterDO>(chars2);
            Collections.sort(x, new Comparator<CharacterDO>() {

                @Override
                public int compare(CharacterDO o1, CharacterDO o2) {
                    return o1.getName().compareTo(o2.getName());
                }

            });

            for (CharacterDO c : x) {
                System.out.printf("Name: %s\n", c.getName());
            }

            System.out.println(id);
        } catch (DBException e) {
            e.printStackTrace();
        }

    }
}
