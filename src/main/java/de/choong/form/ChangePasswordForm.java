package de.choong.form;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.StringValidator;

public class ChangePasswordForm extends Panel {

    private static final long serialVersionUID = 2385513506547650494L;

    private String oldPw1;
    private String oldPw2;
    private String newPw;

    public ChangePasswordForm(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        Form<String> form = new Form<String>("form");

        // TODO error if input doesn't match validation

        // Old password
        form.add(new PasswordTextField("oldpw1", new PropertyModel<String>(this, "oldPw1"))
                .add(StringValidator.lengthBetween(6, 20)));

        // Confirm old password
        form.add(new PasswordTextField("oldpw2", new PropertyModel<String>(this, "oldPw2"))
                .add(StringValidator.lengthBetween(6, 20)));

        // New password
        form.add(new PasswordTextField("newpw", new PropertyModel<String>(this, "newPw"))
                .add(StringValidator.lengthBetween(6, 20)));

        add(form);
    }

    public String getOldPw1() {
        return oldPw1;
    }

    public String getOldPw2() {
        return oldPw2;
    }

    public String getNewPw() {
        return newPw;
    }
}
