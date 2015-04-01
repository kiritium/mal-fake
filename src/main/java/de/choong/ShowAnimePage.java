package de.choong;

import java.util.ArrayList;

import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.AnimeTable;
import de.choong.dao.AnimeSqliteDBDao;
import de.choong.dao.IAnimeDao;
import de.choong.exceptions.DBException;
import de.choong.model.AnimeDO;

public class ShowAnimePage extends BasePage {

    private static final long serialVersionUID = -8485039198076648005L;
    private IAnimeDao<AnimeDO> dao = new AnimeSqliteDBDao();

    public ShowAnimePage() {
        super();
    }

    public ShowAnimePage(final PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        ArrayList<AnimeDO> allAnimes = new ArrayList<>();
		try {
			allAnimes = dao.readAll();
		} catch (DBException e) {
			e.printStackTrace();
		}

        ListDataProvider<AnimeDO> provider = new ListDataProvider<AnimeDO>(
                allAnimes);
        add(new AnimeTable("animeTable", provider));

    }
}
