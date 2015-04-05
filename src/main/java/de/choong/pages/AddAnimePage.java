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
import de.choong.form.AnimeInput;
import de.choong.model.anime.AnimeDO;
import de.choong.model.user.UserRight;
import de.choong.util.SpringUtil;

public class AddAnimePage extends SecurePage {

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
        form.add(new AnimeInput("input", Model.of(anime)));
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

                System.out.println(anime.getCover().getSize());
                try {
                    int id = dao.create(anime);
                    PageParameters param = new PageParameters();
                    param.set("id", id);
                    setResponsePage(SingleAnimePage.class, param);
                } catch (DBException ex) {
                    error("DB Error");
                }

                target.add(feedback);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                super.onError(target, form);
                target.add(feedback);
            }
        };
    }

    @Override
    public UserRight getAccessRight() {
        return UserRight.MODERATOR;
    }
}
