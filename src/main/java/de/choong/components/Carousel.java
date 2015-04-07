package de.choong.components;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import de.choong.util.ImageUtil;

public class Carousel extends Panel {

    private static final long serialVersionUID = 1978217406065088624L;

    public Carousel(String id, IModel<String> path) {
        super(id, path);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        File imagesPath = new File(getDefaultModelObjectAsString());
        List<File> images = Arrays.asList(imagesPath.listFiles());

        ListView<File> indicators = new ListView<File>("indicators", images) {

            private static final long serialVersionUID = 4217645638613748902L;

            @Override
            protected void populateItem(ListItem<File> item) {
                item.add(new Label("indicator", ""));
                item.add(new AttributeAppender("data-slide-to", item.getIndex()));
                if (item.getIndex() == 0) {
                    item.add(new AttributeAppender("class", "active"));
                }
            }
        };

        add(indicators);

        ListView<File> slides = new ListView<File>("slidediv", images) {

            private static final long serialVersionUID = 2554064251622341267L;

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
