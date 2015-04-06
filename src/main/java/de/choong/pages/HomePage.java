package de.choong.pages;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.StaticImage;

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
        List<File> list = Arrays.asList(file.listFiles());

        ListView<File> listView = new ListView<File>("slides", list) {
            // TODO clean up
            @Override
            protected void populateItem(ListItem<File> item) {

                String itemString = item.getModelObject().toString().replace('\\', '/');
                String itemPath = itemString.substring(15, itemString.length());
                System.out.println(itemPath);
                item.add(new StaticImage("slide", Model.of(itemPath)));
            }

            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                tag.append("id", "hey", ",");
            }
        };
        add(listView);
    }
}
