package de.choong.components;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import de.choong.model.user.UserRight;
import de.choong.pages.AddAnimePage;
import de.choong.pages.AddUserPage;
import de.choong.pages.LoginPage;
import de.choong.pages.MultiAnimePage;
import de.choong.pages.MultiUserPage;
import de.choong.util.UserUtil;

public class Navigation extends Panel {

    private static final long serialVersionUID = 2414976312882871541L;

    public Navigation(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        RepeatingView navigationItems = new RepeatingView("navigation");

        addMenuItem(navigationItems, MultiAnimePage.class, "Anime-List");
        addMenuItem(navigationItems, AddAnimePage.class, "Add Anime", UserRight.MODERATOR);
        addMenuItem(navigationItems, LoginPage.class, "Login").setVisible(UserUtil.isNotLoggedIn());
        addMenuItem(navigationItems, AddUserPage.class, "Register").setVisible(
                UserUtil.isNotLoggedIn());
        addMenuItem(navigationItems, MultiUserPage.class, "User-List", UserRight.ADMIN);
        addLogoutMenuItem(navigationItems, "Logout");
        add(navigationItems);
    }

    private NavigationItem addMenuItem(RepeatingView navigationItems, Class<? extends Page> page,
            String name, UserRight userRight) {
        NavigationItem item = new NavigationItem(navigationItems.newChildId(), page, name);
        if (userRight != null) {
            item.setVisible(UserUtil.hasRight(userRight));
        }
        navigationItems.add(item);
        return item;
    }

    private NavigationItem addMenuItem(RepeatingView navigationItems, Class<? extends Page> page,
            String name) {
        return addMenuItem(navigationItems, page, name, null);
    }

    private NavigationItem addLogoutMenuItem(RepeatingView navigationItems, String name) {
        NavigationItem logout = new NavigationItem(navigationItems.newChildId(), name) {
            private static final long serialVersionUID = -2089970305767481794L;

            @Override
            protected WebMarkupContainer createNavigationLink(String id) {
                return new AjaxFallbackLink<String>(id) {

                    private static final long serialVersionUID = -6153926679146216248L;

                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        UserUtil.logout();
                        setResponsePage(getApplication().getHomePage());
                    }

                };
            }
        };
        logout.setVisible(UserUtil.isLoggedIn());
        navigationItems.add(logout);
        return logout;
    }

}
