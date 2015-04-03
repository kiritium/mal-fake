package de.choong.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.AnimeTable;
import de.choong.components.SortableAnimeProvider;
import de.choong.dao.IAnimeDao;
import de.choong.util.SpringUtil;

public class ShowAnimePage extends BasePage {

    private static final long serialVersionUID = -8485039198076648005L;

    public ShowAnimePage() {
        super();
    }

    public ShowAnimePage(final PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new AnimeTable("animeTable", new SortableAnimeProvider()));
    }
}
