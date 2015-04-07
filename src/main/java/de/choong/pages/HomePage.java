package de.choong.pages;

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
        add(new Carousel("carousel", ImageUtil.getAbsoluteSlideshowPath()));
    }
}
