package de.choong.components;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import de.choong.pages.AddAnimePage;
import de.choong.pages.AddUserPage;
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

		addMenuItem(navigationItems, ShowAnimePage.class, "Anime-List");
		addMenuItem(navigationItems, AddAnimePage.class, "Add Anime");
		addMenuItem(navigationItems, LoginPage.class, "Login");
		addMenuItem(navigationItems, AddUserPage.class, "Register");
		add(navigationItems);
	}

	private void addMenuItem(RepeatingView navigationItems, Class<? extends Page> page, String name) {
		navigationItems.add(new NavigationItem(navigationItems.newChildId(), page, name));
	}

}
