package de.choong.pages.anime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValueConversionException;

import de.choong.components.StaticImage;
import de.choong.dao.IAnimeDao;
import de.choong.exceptions.DBException;
import de.choong.model.anime.AnimeDO;
import de.choong.model.anime.Genre;
import de.choong.pages.BasePage;
import de.choong.util.ImageUtil;
import de.choong.util.SpringUtil;

public class SingleAnimePage extends BasePage {

    private static final String DEFAULT_TEXT = "-";

    private static final long serialVersionUID = -8384777450358070031L;

    private IAnimeDao dao = (IAnimeDao) SpringUtil.getBean("animeDao");
    private AnimeDO anime;

    public SingleAnimePage() {
        setResponsePage(getApplication().getHomePage());
    }

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
        if (anime == null) {
            return;
        }

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
        add(new Label("genre", getGenreString()));
    }

    private Component createCover(String id) {
        if (ImageUtil.isCoverAvailable(anime.getId())) {
            return new StaticImage(id, Model.of(ImageUtil.getCoverPath(anime.getId())));
        } else {
            return new StaticImage(id, Model.of(ImageUtil.getCoverDefaultPath()));
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

    private String getGenreString() {
        List<Genre> genres = new ArrayList<>(anime.getGenres());
        Collections.sort(genres, new Comparator<Genre>() {
            @Override
            public int compare(Genre o1, Genre o2) {
                return o1.getDisplayName().compareTo(o2.getDisplayName());
            }
        });

        if (genres != null && genres.isEmpty() == false) {
            StringBuilder sb = new StringBuilder();
            for (Genre genre : genres) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(genre.getDisplayName());
            }
            return sb.toString();
        }
        return DEFAULT_TEXT;
    }
}
