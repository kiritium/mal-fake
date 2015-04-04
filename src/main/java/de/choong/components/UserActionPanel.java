package de.choong.components;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.choong.dao.IUserDao;
import de.choong.exceptions.DBException;
import de.choong.model.user.UserDO;
import de.choong.util.SpringUtil;

public class UserActionPanel extends Panel {

    private static final long serialVersionUID = -9078649491703177796L;

    private int id;
    private IUserDao dao = (IUserDao) SpringUtil.getBean("userDao");

    public UserActionPanel(String id, IModel<UserDO> model) {
        super(id, model);
        this.id = model.getObject().getId();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new AjaxFallbackLink<String>("delete") {
            private static final long serialVersionUID = -983228768596472734L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                try {
                    dao.delete(id);
                    setResponsePage(getPage());
                } catch (DBException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
