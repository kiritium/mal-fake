package de.choong.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.AjaxFeedbackPanel;
import de.choong.dao.IAnimeDao;
import de.choong.exceptions.DBException;
import de.choong.form.AnimeForm;
import de.choong.model.anime.AnimeDO;
import de.choong.util.SpringUtil;

public class AddAnimePage extends BasePage {

    private static final long serialVersionUID = 8623937508924029855L;

    private IAnimeDao dao = (IAnimeDao) SpringUtil.getBean("animeDao");
    private FeedbackPanel feedback;

    public AddAnimePage() {
        super();
    }

    public AddAnimePage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        AnimeDO anime = new AnimeDO();
        Form<AnimeDO> form = new Form<>("form", Model.of(anime));
        form.add(new AnimeForm("input", Model.of(anime)));
        form.add(createSubmitLink("submit", form));

        feedback = new AjaxFeedbackPanel("feedback");
        form.add(feedback);

        add(form);
    }

    private AjaxSubmitLink createSubmitLink(String id, Form<AnimeDO> form) {
        return new AjaxSubmitLink(id, form) {
            private static final long serialVersionUID = -2717359351525157884L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);

                AnimeDO anime = (AnimeDO) form.getModelObject();
                try {
                    dao.create(anime);
                } catch (DBException ex) {
                    feedback.error("DB Error");
                }
                feedback.success("Anime added.");

                target.add(feedback);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                super.onError(target, form);

                // TODO react to error
                error("from.onError");
                target.add(feedback);
            }
        };
    }
}
