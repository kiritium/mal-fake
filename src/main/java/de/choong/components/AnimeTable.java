package de.choong.components;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.sortable.SortableTable;
import de.choong.model.AnimeDO;
import de.choong.pages.SingleAnimePage;

// TODO this extra component is unnessesary, now that we have a sortable table component.
public class AnimeTable extends Panel {

    private static final long serialVersionUID = -3768439988615983395L;

    private ISortableDataProvider<AnimeDO, String> dataProvider;

    public AnimeTable(String id, ISortableDataProvider<AnimeDO, String> dataProvider) {
        super(id);
        this.dataProvider = dataProvider;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        List<IColumn<AnimeDO, String>> columns = new ArrayList<>();
        columns.add(new PropertyColumn<AnimeDO, String>(Model.of("ID"), "id", "id"));
        columns.add(new PropertyColumn<AnimeDO, String>(Model.of("Title"), "title", "title"));
        columns.add(new PropertyColumn<AnimeDO, String>(Model.of("Alt. Title"), "altTitle",
                "altTitle"));
        columns.add(new PropertyColumn<AnimeDO, String>(Model.of("Creator"), "creator", "creator"));
        columns.add(new PropertyColumn<AnimeDO, String>(Model.of("Studio"), "studio", "studio"));
        columns.add(new PropertyColumn<AnimeDO, String>(Model.of("Year"), "year", "year"));

        add(new SortableTable<AnimeDO, String>("test", columns, dataProvider, 10) {
            private static final long serialVersionUID = 6781390593244163407L;

            @Override
            protected Item<AnimeDO> newRowItem(final String id, final int index,
                    final IModel<AnimeDO> model) {
                Item<AnimeDO> item = super.newRowItem(id, index, model);
                item.add(new AjaxEventBehavior("onclick") {
                    private static final long serialVersionUID = 3673916438509361719L;

                    @Override
                    protected void onEvent(AjaxRequestTarget target) {
                        // TODO redirect to SingleAnimePage
                        AnimeDO anime = model.getObject();
                        int id = anime.getId();
                        PageParameters param = new PageParameters();
                        param.set("id", id);
                        setResponsePage(SingleAnimePage.class, param);
                    }
                });
                return item;
            }
        });
    }
}
