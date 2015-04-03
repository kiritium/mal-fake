package de.choong.components;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import de.choong.components.sortabletable.SortableTable;
import de.choong.model.AnimeDO;

public class AnimeTable extends Panel {

    private static final long serialVersionUID = -3768439988615983395L;

    private ISortableDataProvider<AnimeDO,String> dataProvider;

    public AnimeTable(String id, ISortableDataProvider<AnimeDO, String> dataProvider) {
        super(id);
        this.dataProvider = dataProvider;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        List<IColumn<AnimeDO, String>> columns = new ArrayList<>();
        columns.add(new PropertyColumn<AnimeDO, String>(Model.of("ID"), "id","id"));
        columns.add(new PropertyColumn<AnimeDO, String>(Model.of("Title"), "title", "title"));
        columns.add(new PropertyColumn<AnimeDO, String>(Model.of("Year"), "year", "year"));
        columns.add(new PropertyColumn<AnimeDO, String>(Model.of("Author"), "author", "author"));
        
        add(new SortableTable<AnimeDO, String>("test", columns, dataProvider, 10));
    }
}
