package de.choong.pages;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.Carousel;
import de.choong.util.ImageUtil;

public class HomePage extends BasePage {

    private static final long serialVersionUID = -2274690262727601331L;

    public HomePage() {
        super();
    }

    public HomePage(final PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new BookmarkablePageLink<>("newhomepage", NewHomePage.class));
        Carousel carousel = new Carousel("carousel", Model.of(ImageUtil.getAbsoluteSlideshowPath()));
        carousel.setControlVisibility(false);
        add(carousel);
    }
}
