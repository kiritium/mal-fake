package de.choong.components;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import de.choong.pages.AddAnimePage;
import de.choong.pages.LoginPage;
import de.choong.pages.ShowAnimePage;

public class Navigation extends Panel {

    private static final long serialVersionUID = 2414976312882871541L;

    public Navigation(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        RepeatingView navigationItems = new RepeatingView("navigation");
        addMenuItem(navigationItems, new ShowAnimePage(), "Anime-List");
        addMenuItem(navigationItems, new AddAnimePage(), "Add Anime");
        addMenuItem(navigationItems, new LoginPage(), "Login");
        add(navigationItems);
    }

    private void addMenuItem(RepeatingView navigationItems, Page page,
            String name) {
        navigationItems.add(new NavigationItem(navigationItems.newChildId(),
                page, name));
    }

}
