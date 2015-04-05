package de.choong.form;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.markup.html.form.Form;

import de.choong.components.AjaxFeedbackPanel;
import de.choong.dao.IUserDao;
import de.choong.exceptions.DBException;
import de.choong.model.user.UserDO;
import de.choong.util.SpringUtil;
import de.choong.util.UserUtil;

public class LoginForm extends SignInPanel {

    private static final long serialVersionUID = -2072142982507657566L;

    private IUserDao dao = (IUserDao) SpringUtil.getBean("userDao");

    private AjaxFeedbackPanel feedback;

    public LoginForm(String id) {
        super(id);
    }

    public void onSubmit4(AjaxRequestTarget target, Form<?> form) {
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
    }
}
