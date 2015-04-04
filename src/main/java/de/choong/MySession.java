package de.choong;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import de.choong.dao.IUserDao;
import de.choong.exceptions.DBException;
import de.choong.model.user.UserDO;
import de.choong.util.SpringUtil;
import de.choong.util.UserUtil;

public class MySession extends AuthenticatedWebSession {

    private static final long serialVersionUID = 6667431174009175165L;

    private IUserDao dao = (IUserDao) SpringUtil.getBean("userDao");
    private UserDO user;

    public MySession(Request request) {
        super(request);
    }

    @Override
    public boolean authenticate(String username, String password) {
        UserDO actualUser = null;
        try {
            actualUser = dao.readByName(username);
        } catch (DBException e) {
            e.printStackTrace();
            return false;
        }
        if (actualUser == null) {
            return false;
        }
        String salt = actualUser.getSalt();
        String hashedPassword = UserUtil.hash(password, salt);

        if (StringUtils.equals(hashedPassword, actualUser.getPassword())) {
            this.setUser(actualUser);
            return true;
        }
        return false;
    }

    @Override
    public Roles getRoles() {
        // TODO Auto-generated method stub
        return null;
    }

    public UserDO getUser() {
        return user;
    }

    public void setUser(UserDO user) {
        this.user = user;
    }

}
