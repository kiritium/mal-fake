package de.choong.form;

import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

public class ChangeEmailInput extends Panel {

    private static final long serialVersionUID = -8875741561476839318L;

    private String password1;
    private String password2;
    private String email;

    public ChangeEmailInput(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Form<String> form = new Form<String>("form");

        // Password
        form.add(new PasswordTextField("password1", new PropertyModel<String>(this, "password1"))
                .setLabel(Model.of("Password"))
                .add(StringValidator.lengthBetween(6, 20)));

        // Confirm password
        form.add(new PasswordTextField("password2", new PropertyModel<String>(this, "password2"))
                .setLabel(Model.of("Confirm password"))
                .add(StringValidator.lengthBetween(6, 20)));

        // New email
        form.add(new EmailTextField("email", new PropertyModel<String>(this, "email"))
                .setLabel(Model.of("E-mail"))
                .setRequired(true)
                .add(EmailAddressValidator.getInstance())
                .add(StringValidator.lengthBetween(10, 50)));

        add(form);
    }

    public String getPassword1() {
        return password1;
    }

    public String getPassword2() {
        return password2;
    }

    public String getEmail() {
        return email;
    }
}
