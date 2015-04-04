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

import de.choong.components.sortable.SortableTable;
import de.choong.model.user.UserDO;

public class UserTable extends Panel {

    private static final long serialVersionUID = -5865038525815844628L;

    private ISortableDataProvider<UserDO, String> dataProvider;

    public UserTable(String id, ISortableDataProvider<UserDO, String> dataProvider) {
        super(id);
        this.dataProvider = dataProvider;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        List<IColumn<UserDO, String>> columns = new ArrayList<>();
        columns.add(new PropertyColumn<UserDO, String>(Model.of("ID"), "id", "id"));
        columns.add(new PropertyColumn<UserDO, String>(Model.of("Username"), "username", "username"));

        add(new SortableTable<UserDO, String>("table", columns, dataProvider, 10) {
            private static final long serialVersionUID = -1954053987488013638L;

            @Override
            protected Item<UserDO> newRowItem(final String id, final int index,
                    final IModel<UserDO> model) {
                Item<UserDO> item = super.newRowItem(id, index, model);
                item.add(new AjaxEventBehavior("onclick") {
                    private static final long serialVersionUID = 3673916438509361719L;

                    @Override
                    protected void onEvent(AjaxRequestTarget target) {
                        UserDO user = model.getObject();
                        int id = user.getId();
                        // Maybe SingleUserPage
                    }
                });
                return item;
            }
        });
    }
}
