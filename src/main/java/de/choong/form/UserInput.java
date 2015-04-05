package de.choong.form;

import java.util.Arrays;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.choong.model.user.UserDO;
import de.choong.model.user.UserRight;
import de.choong.util.UserUtil;

public class UserInput extends Panel {

    private static final long serialVersionUID = -6052123356031657622L;
    private WebMarkupContainer userRightWrapper;

    public UserInput(String id, IModel<UserDO> model) {
        super(id, model);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        userRightWrapper.setVisibilityAllowed(UserUtil.hasRight(UserRight.ADMIN));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        super.onInitialize();
        UserDO user = (UserDO) getDefaultModelObject();

        // TODO add validation to form
        Form<UserDO> form = new Form<UserDO>("form", Model.of(user));

        // Username
        form.add(new TextField<String>("username", new PropertyModel<String>(user, "username")));

        // Password
        form.add(new PasswordTextField("password", new PropertyModel<String>(user, "password")));

        // User Rights
        userRightWrapper = new WebMarkupContainer("userRightWrapper");
        DropDownChoice<UserRight> userRight = new DropDownChoice<UserRight>("userRight",
                new PropertyModel<UserRight>(user, "userRight"), Arrays.asList(UserRight.values()));
        // TODO ternary operator
        userRight.setDefaultModelObject(UserRight.USER);
        userRightWrapper.add(userRight);
        form.add(userRightWrapper);

        add(form);
    }
}
