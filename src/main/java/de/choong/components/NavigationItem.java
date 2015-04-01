package de.choong.components;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class NavigationItem extends Panel {

    private static final long serialVersionUID = -2762395730922061713L;
    private Page page;
    private String name;

    public NavigationItem(String id, Page page, String name) {
        super(id);
        this.page = page;
        this.name = name;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        AjaxFallbackLink<String> link = new AjaxFallbackLink<String>("menuitem") {

            private static final long serialVersionUID = 9048143806898239960L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                setResponsePage(page);
            }
        };
        link.add(new Label("name", name));
        add(link);
    }
}
