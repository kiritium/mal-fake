package de.choong.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.AjaxFeedbackPanel;
import de.choong.dao.IUserDao;
import de.choong.exceptions.DBException;
import de.choong.form.UserInput;
import de.choong.model.user.UserDO;
import de.choong.util.SpringUtil;
import de.choong.util.UserUtil;

// TODO SecurePage
public class AddUserPage extends BasePage {

    private static final long serialVersionUID = -154289454209633122L;

    private IUserDao dao = (IUserDao) SpringUtil.getBean("userDao");
    private FeedbackPanel feedback;

    public AddUserPage() {
        super();
    }

    public AddUserPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        UserDO user = new UserDO();
        Form<UserDO> form = new Form<>("form", Model.of(user));
        form.add(new UserInput("input", Model.of(user)));
        form.add(createSubmitLink("submit", form));

        feedback = new AjaxFeedbackPanel("feedback");
        form.add(feedback);

        add(form);
    }

    private AjaxSubmitLink createSubmitLink(String id, Form<UserDO> form) {
        return new AjaxSubmitLink(id, form) {
            private static final long serialVersionUID = -2717359351525157884L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);

                UserDO user = (UserDO) form.getModelObject();
                // Hashing password
                user.setSalt(UserUtil.generateSalt());
                String hashedPassword = UserUtil.hash(user.getPassword(), user.getSalt());
                user.setPassword(hashedPassword);

                try {
                    dao.create(user);
                } catch (DBException ex) {
                    feedback.error("DB Error");
                }
                feedback.success("User added.");

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
