package de.choong.pages;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.dao.IAnimeDao;
import de.choong.exceptions.DBException;
import de.choong.model.anime.AnimeDO;
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
        add(new Label("bigTitle", StringUtils.defaultIfBlank(anime.getTitle(), "-")));
        add(new Label("title", StringUtils.defaultIfBlank(anime.getTitle(), "-")));
        add(new Label("altTitle", StringUtils.defaultIfBlank(anime.getAltTitle(), "-")));
        if (anime.getType() == null) {
            add(new Label("type", "-"));
        } else {
            add(new Label("type", anime.getType().getDisplayName()));
        }
        if (anime.getStatus() == null) {
            add(new Label("status", "-"));
        } else {
            add(new Label("status", anime.getStatus().getDisplayName()));
        }
        if (anime.getSeason() == null) {
            add(new Label("season", "" + anime.getYear()));
        } else {
            add(new Label("season", anime.getSeason().getDisplayName() + " " + anime.getYear()));
        }
        add(new Label("creator", StringUtils.defaultIfBlank(anime.getCreator(), "-")));
        add(new Label("studio", StringUtils.defaultIfBlank(anime.getStudio(), "-")));
    }
}
