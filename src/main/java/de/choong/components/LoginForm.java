package de.choong.components;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

public class LoginForm extends Panel {

    private static final long serialVersionUID = -2072142982507657566L;
    private String username;
    private String password;

    public LoginForm(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        TextField<String> userTextField = new TextField<String>("username",
                new PropertyModel<String>(this, "username"));
        userTextField.setRequired(true);
        add(userTextField);
        add(new PasswordTextField("password", new PropertyModel<String>(this, "password")));
        add(new AjaxSubmitLink("login") {

            @Override
            public void onSubmit(AjaxRequestTarget target, Form<?> form) {
                System.out.println("Login");
            }

        });
    }
}
