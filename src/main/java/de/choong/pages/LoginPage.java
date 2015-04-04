package de.choong.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.form.LoginForm;
import de.choong.util.UserUtil;

public class LoginPage extends BasePage {

    private static final long serialVersionUID = 966488026111069731L;

    public LoginPage() {
        super();
    }

    public LoginPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        redirect();
        add(new LoginForm("form"));
    }

    public void redirect() {
        if (UserUtil.isLoggedIn()) {
            setResponsePage(getApplication().getHomePage());
        }
    }
}
