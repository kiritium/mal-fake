package de.choong.components;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.model.user.UserRight;
import de.choong.pages.admin.AdministrationPage;
import de.choong.pages.anime.AddAnimePage;
import de.choong.pages.anime.MultiAnimePage;
import de.choong.pages.user.AddUserPage;
import de.choong.pages.user.LoginPage;
import de.choong.pages.user.UserProfilePage;
import de.choong.pages.user.UserSettingsPage;
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

        // addProfileMenuItem(navigationItems, "Profile");
        addMenuItem(navigationItems, UserProfilePage.class, "Profile", UserRight.USER);
        addMenuItem(navigationItems, MultiAnimePage.class, "Anime-List");
        addMenuItem(navigationItems, AddAnimePage.class, "Add Anime", UserRight.MODERATOR);
        addMenuItem(navigationItems, LoginPage.class, "Login").setVisible(UserUtil.isNotLoggedIn());
        addMenuItem(navigationItems, AddUserPage.class, "Register").setVisible(
                UserUtil.isNotLoggedIn());
        addMenuItem(navigationItems, AdministrationPage.class, "Administration", UserRight.ADMIN);
        addMenuItem(navigationItems, UserSettingsPage.class, "Settings", UserRight.USER);
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
                return new StatelessLink<String>(id) {

                    private static final long serialVersionUID = -6153926679146216248L;

                    @Override
                    public void onClick() {
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

    private NavigationItem addProfileMenuItem(RepeatingView navigationItems, String name) {
        NavigationItem profile = new NavigationItem(navigationItems.newChildId(), name) {

            private static final long serialVersionUID = 281615390942815019L;

            @Override
            protected WebMarkupContainer createNavigationLink(String id) {
                return new StatelessLink<String>(id) {

                    private static final long serialVersionUID = 9169059229727869648L;

                    @Override
                    public void onClick() {
                        if (UserUtil.getCurrentUser() != null) {
                            PageParameters param = new PageParameters();
                            param.set("id", UserUtil.getCurrentUser().getId());
                            setResponsePage(UserProfilePage.class, param);
                        }
                    }
                };
            }
        };
        profile.setVisible(UserUtil.isLoggedIn());
        navigationItems.add(profile);
        return profile;
    }
}
