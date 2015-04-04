package de.choong.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.model.user.UserRight;
import de.choong.util.UserUtil;

public abstract class SecurePage extends BasePage {

    private static final long serialVersionUID = 6657000565279332841L;

    public SecurePage() {
        super();
        canAccess();
    }

    public SecurePage(PageParameters parameters) {
        super(parameters);
        canAccess();
    }

    private void canAccess() {
        if (UserUtil.hasRight(getAccessRight()) == false) {
            setResponsePage(getApplication().getApplicationSettings().getAccessDeniedPage());
        }
    }

    public abstract UserRight getAccessRight();
}
