package de.choong.components;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

public class NavigationItem extends Panel {

	private static final long serialVersionUID = -2762395730922061713L;
	private Class<? extends Page> pageClass;
	private String name;

	public NavigationItem(String id, Class<? extends Page> pageClass, String name) {
		super(id);
		this.pageClass = pageClass;
		this.name = name;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		BookmarkablePageLink<String> link = new BookmarkablePageLink<>("menuItem", pageClass);
		link.add(new Label("name", name));
		add(link);
	}
}
