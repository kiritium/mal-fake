package de.choong.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.AnimeTable;
import de.choong.components.sortable.SortableAnimeProvider;

public class MultiAnimePage extends BasePage {

    private static final long serialVersionUID = -8485039198076648005L;

    public MultiAnimePage() {
        super();
    }

    public MultiAnimePage(final PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new AnimeTable("animeTable", new SortableAnimeProvider()));
    }
}
