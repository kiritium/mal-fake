package de.choong.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.StringValidator;

import de.choong.components.AjaxFeedbackPanel;
import de.choong.dao.IUserDao;
import de.choong.exceptions.DBException;
import de.choong.model.user.UserDO;
import de.choong.model.user.UserRight;
import de.choong.util.SpringUtil;
import de.choong.util.UserUtil;

public class UserSettingsPage extends SecurePage {

    private IUserDao dao = (IUserDao) SpringUtil.getBean("userDao");
    private FeedbackPanel feedback;
    private String oldPw1;
    private String oldPw2;
    private String newPw = null;

    public UserSettingsPage() {
        super();
    }

    public UserSettingsPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Form<String> form = new Form<String>("pwform");
        form.add(new PasswordTextField("oldpw1", new PropertyModel<String>(this, "oldPw1"))
                .add(StringValidator.lengthBetween(6, 20)));
        form.add(new PasswordTextField("oldpw2", new PropertyModel<String>(this, "oldPw2"))
                .add(StringValidator.lengthBetween(6, 20)));
        form.add(new PasswordTextField("newpw", new PropertyModel<String>(this, "newPw"))
                .add(StringValidator.lengthBetween(6, 20)));
        form.add(new AjaxSubmitLink("submit") {

            @Override
            protected void onAfterSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onAfterSubmit(target, form);
                // TODO error if input doesn't match validation
                if (oldPw1.equals(oldPw2)) {
                    UserDO user = UserUtil.getCurrentUser();
                    if (UserUtil.hash(oldPw1, user.getSalt()).equals(user.getPassword())) {
                        String salt = UserUtil.generateSalt();
                        user.setPassword(UserUtil.hash(newPw, salt));
                        user.setSalt(salt);
                        try {
                            dao.update(user);
                        } catch (DBException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        feedback = new AjaxFeedbackPanel("feedback");
        form.add(feedback);
        add(form);
    }

    @Override
    public UserRight getAccessRight() {
        return UserRight.USER;
    }
}