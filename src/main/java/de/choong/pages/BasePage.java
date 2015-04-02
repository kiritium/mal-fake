package de.choong.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.Navigation;

public class BasePage extends WebPage {

    private static final long serialVersionUID = -6402189466352001944L;

    public BasePage() {
        super();
    }

    public BasePage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Navigation("navlinks"));
    }

}
