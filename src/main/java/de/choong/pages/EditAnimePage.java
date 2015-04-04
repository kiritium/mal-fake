package de.choong.pages;

import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.dao.IAnimeDao;
import de.choong.exceptions.DBException;
import de.choong.form.AnimeForm;
import de.choong.form.FormMode;
import de.choong.model.anime.AnimeDO;
import de.choong.util.SpringUtil;

public class EditAnimePage extends BasePage {
    private static final long serialVersionUID = 6382283308700306123L;

    private AnimeDO anime;
    private IAnimeDao dao = (IAnimeDao) SpringUtil.getBean("animeDao");

    public EditAnimePage() {
        super();
    }

    public EditAnimePage(PageParameters parameters) {
        super(parameters);
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
        add(new AnimeForm("form", Model.of(anime), FormMode.EDIT));
    }
}
