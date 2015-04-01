package de.choong.components;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.choong.dao.IUserDao;
import de.choong.dao.UserDao;
import de.choong.exceptions.DBException;
import de.choong.model.UserDO;

public class LoginForm extends Panel {

    private static final long serialVersionUID = -2072142982507657566L;
    private IUserDao dao = new UserDao();

    public LoginForm(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        
        UserDO user = new UserDO();
        
        Form<UserDO> form = new Form<>("form", Model.of(user));
        
        // Username
        TextField<String> userTextField = new TextField<>("username",
                new PropertyModel<>(user, "username"));
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
				try {
					login(user);
				} catch (DBException e) {
					e.printStackTrace();
				}
            }

        });
    }
    
    private boolean login(UserDO user) throws DBException {
    	return dao.login(user);
    }
}
