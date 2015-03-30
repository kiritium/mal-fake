package de.choong;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {

	private static final long serialVersionUID = -2274690262727601331L;

	public HomePage(final PageParameters parameters) {
		super(parameters);
    }
	
	@Override
	protected void onInitialize() {
	    super.onInitialize();
	    
	    add(createLink("showAnime", ShowAnimePage.class));
	    add(createLink("addAnime", AddAnimePage.class));
	}
	
	private AjaxFallbackLink<String> createLink(String id, Class<?> page) {
		return new AjaxFallbackLink<String>(id) {
			private static final long serialVersionUID = 3949370585208906040L;

			@Override
            public void onClick(AjaxRequestTarget target) {
                setResponsePage(AddAnimePage.class);
            }
		};
	}
}
