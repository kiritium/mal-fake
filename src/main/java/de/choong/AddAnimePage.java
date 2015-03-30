package de.choong;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.dao.AnimeDao;
import de.choong.model.AnimeDO;

public class AddAnimePage extends WebPage {

    private static final long serialVersionUID = 8623937508924029855L;
    private AnimeDao dao = new AnimeDao();

    public AddAnimePage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        
        AnimeDO anime = new AnimeDO();
        
        Form<AnimeDO> form = new Form<AnimeDO>("form", Model.of(anime));
        form.add(new TextField<String>("title", new PropertyModel<String>(anime, "title")));
        form.add(new TextField<String>("year", new PropertyModel<String>(anime, "year")));
        form.add(new TextField<String>("author", new PropertyModel<String>(anime, "author")));
        form.add(new AjaxSubmitLink("submit", form) {
            private static final long serialVersionUID = -2717359351525157884L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);
                AnimeDO anime = (AnimeDO) form.getModelObject();
                dao.create(anime);
            }
        });
        add(form);
    }

}
