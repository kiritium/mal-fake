package de.choong.form;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.choong.components.AjaxFeedbackPanel;
import de.choong.dao.IUserDao;
import de.choong.exceptions.DBException;
import de.choong.model.user.UserDO;
import de.choong.util.SpringUtil;
import de.choong.util.UserUtil;

public class LoginForm extends Panel {

    private static final long serialVersionUID = -2072142982507657566L;

    private IUserDao dao = (IUserDao) SpringUtil.getBean("userDao");

    private AjaxFeedbackPanel feedback;

    public LoginForm(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        UserDO user = new UserDO();

        // TODO validation
        Form<UserDO> form = new Form<>("form", Model.of(user));

        feedback = new AjaxFeedbackPanel("feedback");
        form.add(feedback);

        // Username
        TextField<String> userTextField = new TextField<>("username", new PropertyModel<>(user,
                "username"));
        userTextField.setRequired(true);
        form.add(userTextField);

        // Password
        form.add(new PasswordTextField("password", new PropertyModel<>(user, "password")));

        // Submit
        form.add(new AjaxSubmitLink("login") {
            private static final long serialVersionUID = -8648337841887290056L;

            @Override
            public void onSubmit(AjaxRequestTarget target, Form<?> form) {
                UserDO user = (UserDO) form.getModelObject();
                UserDO actualUser = null;
                try {
                    actualUser = dao.readByName(user.getUsername());
                } catch (DBException e) {
                    e.printStackTrace();
                    return;
                }
                if (actualUser == null) {
                    feedback.error("Wrong username or password.");
                    target.add(feedback);
                    return;
                }
                String salt = actualUser.getSalt();
                String hashedPassword = UserUtil.hash(user.getPassword(), salt);

                if (StringUtils.equals(hashedPassword, actualUser.getPassword())) {
                    feedback.info("Success");
                } else {
                    feedback.error("Wrong username or password.");
                }

                target.add(feedback);
                // TODO session

            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                super.onError(target, form);

                // TODO error
            }
        });
        add(form);
    }
}
