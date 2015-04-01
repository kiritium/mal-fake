package de.choong;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.AnimeEditForm;
import de.choong.dao.AnimeSqliteDBDao;
import de.choong.dao.IAnimeDao;
import de.choong.model.AnimeDO;

public class AddAnimePage extends BasePage {

    private static final long serialVersionUID = 8623937508924029855L;
    private IAnimeDao<AnimeDO> dao = new AnimeSqliteDBDao();

    public AddAnimePage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        AnimeDO anime = new AnimeDO();
        add(new AnimeEditForm("form", Model.of(anime)) {
            private static final long serialVersionUID = 586666543348249303L;

            @Override
            public void onSubmit(AjaxRequestTarget target, Form<?> form) {
                AnimeDO anime = (AnimeDO) form.getModelObject();
                AddAnimePage.this.dao.create(anime);
                success("Anime added.");
            }

        });
    }

}
