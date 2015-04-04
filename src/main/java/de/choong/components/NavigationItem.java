package de.choong.components;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NavigationItem extends Panel {

    private static final long serialVersionUID = -2762395730922061713L;
    private Logger log = LoggerFactory.getLogger(NavigationItem.class);
    private Class<? extends Page> pageClass;
    private String name;

    public NavigationItem(String id, Class<? extends Page> pageClass, String name) {
        super(id);
        this.pageClass = pageClass;
        this.name = name;
    }

    public NavigationItem(String id, String name) {
        this(id, null, name);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        WebMarkupContainer link = createNavigationLink("menuItem");
        link.add(new Label("name", name));
        add(link);
    }

    protected WebMarkupContainer createNavigationLink(String id) {
        if (pageClass != null) {
            return new BookmarkablePageLink<>(id, pageClass);
        }
        log.warn("MenuItem could not get rendered: " + name);
        return new WebMarkupContainer(id);
    }
}
