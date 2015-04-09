package de.choong.pages;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.UserTable;
import de.choong.components.sortable.SortableUserProvider;
import de.choong.dao.IUserDao;
import de.choong.model.user.UserRight;
import de.choong.util.SpringUtil;

public class AdministrationPage extends SecurePage {

    private static final long serialVersionUID = 4569728237093473098L;

    FeedbackPanel feedback;
    IUserDao dao = (IUserDao) SpringUtil.getBean("userDao");

    public AdministrationPage() {
        super();
    }

    public AdministrationPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        // User-List
        add(new UserTable("userTable", new SortableUserProvider()));

        // Admin function
        add(new BookmarkablePageLink<>("reguser", AddUserPage.class));
        add(new BookmarkablePageLink<>("changeslides", EditSlideshowPage.class));

    }

    @Override
    public UserRight getAccessRight() {
        return UserRight.ADMIN;
    }
}
