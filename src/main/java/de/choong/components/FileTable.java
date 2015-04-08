package de.choong.components;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class FileTable extends Panel {

    private static final long serialVersionUID = 7463501607392831874L;

    public FileTable(String id, IModel<String> path) {
        super(id, path);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        File imagesPath = new File(getDefaultModelObjectAsString());
        List<File> images = Arrays.asList(imagesPath.listFiles());
        ListDataProvider<File> dataProvider = new ListDataProvider<>(images);

        DataView<File> dataView = new DataView<File>("row", dataProvider) {

            private static final long serialVersionUID = 4227943023721882245L;

            @Override
            protected void populateItem(Item<File> item) {
                File image = item.getModelObject();
                RepeatingView repeatingView = new RepeatingView("data");

                repeatingView.add(new Label(repeatingView.newChildId(), Model.of(image.getName())));
                repeatingView.add(new FileActionPanel(repeatingView.newChildId(), item.getModel()));
                item.add(repeatingView);

            }
        };
        add(dataView);
    }
}
