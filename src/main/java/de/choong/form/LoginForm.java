package de.choong.form;

import org.apache.wicket.authroles.authentication.panel.SignInPanel;

import de.choong.components.AjaxFeedbackPanel;

public class LoginForm extends SignInPanel {

    private static final long serialVersionUID = -2072142982507657566L;

    public LoginForm(String id) {
        super(id);

        addOrReplace(new AjaxFeedbackPanel("feedback"));
    }

}
