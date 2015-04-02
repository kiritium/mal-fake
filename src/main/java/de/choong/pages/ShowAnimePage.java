package de.choong.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.AnimeTable;
import de.choong.dao.IAnimeDao;
import de.choong.exceptions.DBException;
import de.choong.model.AnimeDO;
import de.choong.util.SpringUtil;

public class ShowAnimePage extends BasePage {

    private static final long serialVersionUID = -8485039198076648005L;
    private IAnimeDao dao = (IAnimeDao) SpringUtil.getBean("animeDao");

    public ShowAnimePage() {
        super();
    }

    public ShowAnimePage(final PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        List<AnimeDO> allAnimes = new ArrayList<>();
        try {
            allAnimes = dao.readAll();
        } catch (DBException e) {
            e.printStackTrace();
        }

        ListDataProvider<AnimeDO> provider = new ListDataProvider<AnimeDO>(allAnimes);
        add(new AnimeTable("animeTable", provider));

    }
}
