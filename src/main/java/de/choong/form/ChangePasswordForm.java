package de.choong.form;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

import de.choong.components.AjaxFeedbackPanel;
import de.choong.dao.IUserDao;
import de.choong.exceptions.DBException;
import de.choong.model.user.UserDO;
import de.choong.util.SpringUtil;
import de.choong.util.UserUtil;

public class ChangePasswordForm extends Panel {

    private static final long serialVersionUID = -9082737970536237666L;

    private IUserDao dao = (IUserDao) SpringUtil.getBean("userDao");
    private FeedbackPanel feedback;

    public ChangePasswordForm(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Form<String> form = new Form<String>("form");

        ChangePasswordInput input = new ChangePasswordInput("input");
        form.add(input);

        form.add(new AjaxSubmitLink("submit") {

            private static final long serialVersionUID = -2774287942746222984L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);

                String oldPassword1 = input.getOldPassword1();
                String oldPassword2 = input.getOldPassword2();
                String newPassword = input.getNewPassword();

                if (oldPassword1.equals(oldPassword2)) {
                    UserDO user = UserUtil.getCurrentUser();
                    if (UserUtil.hash(oldPassword1, user.getSalt()).equals(user.getPassword())) {
                        String salt = UserUtil.generateSalt();
                        user.setPassword(UserUtil.hash(newPassword, salt));
                        user.setSalt(salt);
                        try {
                            dao.update(user);
                            success("Password changed.");
                        } catch (DBException e) {
                            error("DB Error.");
                        }
                    } else {
                        error("Wrong password(s).");
                    }
                } else {
                    error("Wrong password(s).");
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
}
