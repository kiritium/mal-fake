package de.choong.pages;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.util.string.StringValueConversionException;

import de.choong.dao.IAnimeDao;
import de.choong.exceptions.DBException;
import de.choong.model.anime.AnimeDO;
import de.choong.util.ConfigUtil;
import de.choong.util.SpringUtil;

public class SingleAnimePage extends BasePage {

    private static final String DEFAULT_TEXT = "-";

    private static final long serialVersionUID = -8384777450358070031L;

    private IAnimeDao dao = (IAnimeDao) SpringUtil.getBean("animeDao");
    private AnimeDO anime;

    public SingleAnimePage(final PageParameters parameters) {
        int id = -1;
        try {
            id = parameters.get("id").toInt();
        } catch (StringValueConversionException e) {
            setResponsePage(getApplication().getHomePage());
        }
        try {
            anime = dao.read(id);
        } catch (DBException e) {
            setResponsePage(getApplication().getHomePage());
        }
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(createCover("cover"));
        add(new Label("bigTitle", StringUtils.defaultIfBlank(anime.getTitle(), DEFAULT_TEXT)));
        add(new Label("title", StringUtils.defaultIfBlank(anime.getTitle(), DEFAULT_TEXT)));
        add(new Label("summary", StringUtils.defaultIfBlank(anime.getSummary(), DEFAULT_TEXT)));
        add(new Label("altTitle", StringUtils.defaultIfBlank(anime.getAltTitle(), DEFAULT_TEXT)));
        add(new Label("type", anime.getType().getDisplayName()));
        add(new Label("status", anime.getStatus().getDisplayName()));
        add(new Label("season", StringUtils.defaultIfBlank(getSeasonDisplay(anime), DEFAULT_TEXT)));
        add(new Label("creator", StringUtils.defaultIfBlank(anime.getCreator(), DEFAULT_TEXT)));
        add(new Label("studio", StringUtils.defaultIfBlank(anime.getStudio(), DEFAULT_TEXT)));
    }

    private Component createCover(String id) {
        if (ConfigUtil.isCoverAvailable(anime.getId())) {
            return new Image(id,
                    new ContextRelativeResource(ConfigUtil.getCoverPath(anime.getId())));
        } else {
            // TODO "no cover"-img (remove min-height in style!)
            return new WebMarkupContainer(id);
        }
    }

    private String getSeasonDisplay(AnimeDO anime) {
        String displayName = anime.getSeason().getDisplayName();

        if (anime.getYear() != null) {
            if (StringUtils.isNotBlank(displayName)) {
                displayName += " ";
            }
            displayName += anime.getYear();
        }
        return displayName;
    }

}
