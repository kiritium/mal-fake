package de.choong.form;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.StringValidator;

public class ChangePasswordInput extends Panel {

    private static final long serialVersionUID = 2385513506547650494L;

    private String oldPassword1;
    private String oldPassword2;
    private String newPassword;

    public ChangePasswordInput(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        Form<String> form = new Form<String>("form");

        // Old password
        form.add(new PasswordTextField("oldpassword1", new PropertyModel<String>(this,
                "oldPassword1"))
                .setLabel(Model.of("Old password"))
                .add(StringValidator.lengthBetween(6, 20)));

        // Confirm old password
        form.add(new PasswordTextField("oldpassword2", new PropertyModel<String>(this,
                "oldPassword2"))
                .setLabel(Model.of("Confirm old password"))
                .add(StringValidator.lengthBetween(6, 20)));

        // New password
        form.add(new PasswordTextField("newpassword",
                new PropertyModel<String>(this, "newPassword"))
                .setLabel(Model.of("New password"))
                .add(StringValidator.lengthBetween(6, 20)));

        add(form);
    }

    public String getOldPassword1() {
        return oldPassword1;
    }

    public String getOldPassword2() {
        return oldPassword2;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
