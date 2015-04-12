package de.choong.pages.anime;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.AjaxFeedbackPanel;
import de.choong.dao.ICharacterDao;
import de.choong.exceptions.DBException;
import de.choong.form.CharacterInput;
import de.choong.model.anime.CharacterDO;
import de.choong.model.user.UserRight;
import de.choong.pages.SecurePage;
import de.choong.util.SpringUtil;

public class AddCharacterPage extends SecurePage {

    private static final long serialVersionUID = -5666377554538730088L;

    private ICharacterDao dao = (ICharacterDao) SpringUtil.getBean("characterBean");
    private FeedbackPanel feedback;

    public AddCharacterPage() {
        super();
    }

    public AddCharacterPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        CharacterDO character = new CharacterDO();
        Form<CharacterDO> form = new Form<>("form");

        form.add(new CharacterInput("input", Model.of(character)));
        form.add(createSubmitLink("submit", form));

        feedback = new AjaxFeedbackPanel("feedback");
        form.add(feedback);

        add(form);
    }

    private AjaxSubmitLink createSubmitLink(String id, Form<CharacterDO> form) {
        return new AjaxSubmitLink(id, form) {

            private static final long serialVersionUID = 2323634515821494286L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);

                CharacterDO character = (CharacterDO) form.getModelObject();

                try {
                    int id = dao.create(character);
                    // TODO SingleCharacterPage
                    success("Character added. " + id);
                } catch (DBException e) {
                    error("DB error.");
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
