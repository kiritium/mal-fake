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

public class ChangeEmailForm extends Panel {

    private static final long serialVersionUID = -7309688276949041363L;

    private IUserDao dao = (IUserDao) SpringUtil.getBean("userDao");
    private FeedbackPanel feedback;

    public ChangeEmailForm(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Form<String> form = new Form<String>("form");

        ChangeEmailInput input = new ChangeEmailInput("input");
        form.add(input);

        form.add(new AjaxSubmitLink("submit") {

            private static final long serialVersionUID = -2774287942746222984L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);

                String password1 = input.getPassword1();
                String password2 = input.getPassword2();
                String email = input.getEmail();

                System.out.println(password1);
                System.out.println(password2);
                System.out.println(email);

                if (password1.equals(password2)) {
                    UserDO user = UserUtil.getCurrentUser();
                    if (UserUtil.hash(password1, user.getSalt()).equals(user.getPassword())) {
                        user.setEmail(email);
                        try {
                            System.out.println("try");
                            dao.update(user);
                            success("E-mail changed.");
                        } catch (DBException e) {
                            System.out.println("cath");
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
