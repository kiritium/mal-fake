package de.choong.components;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

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
        addMenuItem(navigationItems, AddAnimePage.class, "Add Anime");
        addMenuItem(navigationItems, LoginPage.class, "Login");
        addMenuItem(navigationItems, AddUserPage.class, "Register");
        addMenuItem(navigationItems, MultiUserPage.class, "User-List");
        addLogout(navigationItems, "Logout");
        add(navigationItems);
    }

    private void addMenuItem(RepeatingView navigationItems, Class<? extends Page> page, String name) {
        navigationItems.add(new NavigationItem(navigationItems.newChildId(), page, name));
    }

    private void addLogout(RepeatingView navigationItems, String name) {
        navigationItems.add(new NavigationItem(navigationItems.newChildId(), name) {
            @Override
            protected WebMarkupContainer createNavigationLink(String id) {
                return new AjaxFallbackLink<String>(id) {

                    private static final long serialVersionUID = -6153926679146216248L;

                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        UserUtil.logout();
                    }

                };
            }
        });
    }

}
