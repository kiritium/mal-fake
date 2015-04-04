package de.choong.form;

import java.util.Arrays;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.choong.model.user.UserDO;
import de.choong.model.user.UserRight;

public class UserInput extends Panel {

    private static final long serialVersionUID = -6052123356031657622L;

    public UserInput(String id, Model<UserDO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        super.onInitialize();
        UserDO user = (UserDO) getDefaultModelObject();

        Form<UserDO> form = new Form<UserDO>("form", Model.of(user));

        // Username
        form.add(new TextField<String>("username", new PropertyModel<String>(user, "username")));

        // Password
        form.add(new PasswordTextField("password", new PropertyModel<String>(user, "password")));

        // User-Right
        // TODO only visible to admin, else userRight default to USER
        DropDownChoice<UserRight> userRight = new DropDownChoice<UserRight>("userRight",
                new PropertyModel<UserRight>(user, "userRight"), Arrays.asList(UserRight.values()));
        userRight.setNullValid(false);
        userRight.setDefaultModelObject(UserRight.USER);
        form.add(userRight);

        add(form);
    }
}
