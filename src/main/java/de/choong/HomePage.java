package de.choong;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);
    }
	
	@Override
	protected void onInitialize() {
	    super.onInitialize();
	    
	    add(new AjaxFallbackLink<String>("showAnime") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                setResponsePage(ShowAnimePage.class);
            }
        });
	    
	    add(new AjaxFallbackLink<String>("addAnime") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                setResponsePage(AddAnimePage.class);
            }
        });
	}
}
