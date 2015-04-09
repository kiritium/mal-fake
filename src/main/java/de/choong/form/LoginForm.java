package de.choong.form;

import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import de.choong.components.AjaxFeedbackPanel;

public class LoginForm extends SignInPanel {

    private static final long serialVersionUID = -2072142982507657566L;

    public LoginForm(String id) {
        super(id);
        @SuppressWarnings("unchecked")
        TextField<String> txt = (TextField<String>) get("signInForm").get("username");
        txt.setLabel(Model.of("Username"));

        PasswordTextField pw = (PasswordTextField) get("signInForm").get("password");
        pw.setLabel(Model.of("Password"));
        addOrReplace(new AjaxFeedbackPanel("feedback"));
    }
}
