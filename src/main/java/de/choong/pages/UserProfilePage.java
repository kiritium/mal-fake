package de.choong.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValueConversionException;

import de.choong.dao.IUserDao;
import de.choong.exceptions.DBException;
import de.choong.model.user.UserDO;
import de.choong.model.user.UserRight;
import de.choong.util.SpringUtil;
import de.choong.util.UserUtil;

public class UserProfilePage extends SecurePage {

    private static final long serialVersionUID = 6693061036414189990L;

    private UserDO user;
    private IUserDao dao = (IUserDao) SpringUtil.getBean("userDao");

    public UserProfilePage() {
        if (UserUtil.isLoggedIn()) {
            user = UserUtil.getCurrentUser();
        } else {
            setResponsePage(getApplication().getHomePage());
        }
    }

    public UserProfilePage(final PageParameters parameters) {
        int id = -1;
        try {
            id = parameters.get("id").toInt();
        } catch (StringValueConversionException e) {
            setResponsePage(getApplication().getHomePage());
        }
        try {
            user = dao.read(id);
        } catch (DBException e) {
            setResponsePage(getApplication().getHomePage());
        }
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        if (user != null) {
            add(new Label("bigUsername", user.getUsername()));
        }
    }

    @Override
    public UserRight getAccessRight() {
        return UserRight.USER;
    }
}
