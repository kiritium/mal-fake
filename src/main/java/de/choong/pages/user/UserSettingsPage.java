package de.choong.pages.user;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.form.ChangeEmailForm;
import de.choong.form.ChangePasswordForm;
import de.choong.model.user.UserRight;
import de.choong.pages.SecurePage;

public class UserSettingsPage extends SecurePage {

    private static final long serialVersionUID = 2036772335084382755L;

    public UserSettingsPage() {
        super();
    }

    public UserSettingsPage(PageParameters parameters) {
        super(parameters);
    }

    // TODO fix

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new ChangePasswordForm("pwform"));
        add(new ChangeEmailForm("emailform"));
    }

    @Override
    public UserRight getAccessRight() {
        return UserRight.USER;
    }
}