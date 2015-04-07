package de.choong.pages;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.StaticImage;
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
        File file = new File("src/main/webapp/img/slideshow/");
        List<File> images = Arrays.asList(file.listFiles());

        ListView<File> indicators = new ListView<File>("indicators", images) {
            @Override
            protected void populateItem(ListItem<File> item) {
                item.add(new Label("ind", ""));
                item.add(new AttributeAppender("data-slide-to", item.getIndex()));
                if (item.getIndex() == 0) {
                    item.add(new AttributeAppender("class", "active"));
                }
            }
        };

        add(indicators);

        ListView<File> slides = new ListView<File>("slides", images) {

            @Override
            protected void populateItem(ListItem<File> item) {
                String itemName = item.getModelObject().getName();
                item.add(new StaticImage("slide", Model.of(ImageUtil.getSlideshowPath(itemName))));
                if (item.getIndex() == 0) {
                    item.add(new AttributeAppender("class", Model.of("active"), " "));
                }
            }
        };
        add(slides);
    }
}
