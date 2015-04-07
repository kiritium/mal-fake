package de.choong.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.AjaxFeedbackPanel;
import de.choong.dao.IUserDao;
import de.choong.exceptions.DBException;
import de.choong.form.ChangePasswordForm;
import de.choong.model.user.UserDO;
import de.choong.model.user.UserRight;
import de.choong.util.SpringUtil;
import de.choong.util.UserUtil;

public class UserSettingsPage extends SecurePage {

    private static final long serialVersionUID = 2036772335084382755L;

    private IUserDao dao = (IUserDao) SpringUtil.getBean("userDao");
    private FeedbackPanel feedback;

    public UserSettingsPage() {
        super();
    }

    public UserSettingsPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Form<String> form = new Form<String>("form");

        ChangePasswordForm changePasswordForm = new ChangePasswordForm("input");

        form.add(changePasswordForm);
        form.add(new AjaxSubmitLink("submit") {

            private static final long serialVersionUID = -2774287942746222984L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);

                String oldPw1 = changePasswordForm.getOldPw1();
                String oldPw2 = changePasswordForm.getOldPw2();
                String newPw = changePasswordForm.getNewPw();

                if (oldPw1.equals(oldPw2)) {
                    UserDO user = UserUtil.getCurrentUser();
                    if (UserUtil.hash(oldPw1, user.getSalt()).equals(user.getPassword())) {
                        String salt = UserUtil.generateSalt();
                        user.setPassword(UserUtil.hash(newPw, salt));
                        user.setSalt(salt);
                        try {
                            dao.update(user);
                            success("Password changed.");
                        } catch (DBException e) {
                            error("DB Error.");
                        }
                    }
                }
                target.add(feedback);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                super.onError(target, form);
                target.add(feedback);
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