package de.choong.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.dao.IAnimeDao;
import de.choong.exceptions.DBException;
import de.choong.model.AnimeDO;
import de.choong.util.SpringUtil;

public class SingleAnimePage extends BasePage {

    private static final long serialVersionUID = -8384777450358070031L;

    private IAnimeDao dao = (IAnimeDao) SpringUtil.getBean("animeDao");
    private AnimeDO anime;

    // TODO ID NULL OR NOT INT
    public SingleAnimePage(final PageParameters parameters) {
        int id = parameters.get("id").toInt();
        try {
            anime = dao.read(id);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        // TODO fill information
        add(new Label("title", anime.getTitle()));
    }
}
