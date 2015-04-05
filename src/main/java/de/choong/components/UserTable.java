package de.choong.components;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
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
        columns.add(createClickablePropertyColumn("ID", "id", "id"));
        columns.add(createClickablePropertyColumn("Username", "username", "username"));
        columns.add(createClickablePropertyColumn("User-Right", "userRight", "userRight"));

        columns.add(new AbstractColumn<UserDO, String>(new Model<String>("")) {
            private static final long serialVersionUID = 3431476079203912069L;

            @Override
            public void populateItem(Item<ICellPopulator<UserDO>> cellItem, String componentId,
                    IModel<UserDO> rowModel) {
                cellItem.add(new UserActionPanel(componentId, rowModel));
            }
        });

        add(new SortableTable<UserDO, String>("table", columns, dataProvider, 10));
    }

    public PropertyColumn<UserDO, String> createClickablePropertyColumn(String display,
            String sortProperty, String propertyExpression) {
        return new PropertyColumn<UserDO, String>(Model.of(display), sortProperty,
                propertyExpression) {
            private static final long serialVersionUID = 7172331019091215968L;

            @Override
            public void populateItem(Item<ICellPopulator<UserDO>> item, String componentId,
                    IModel<UserDO> rowModel) {
                super.populateItem(item, componentId, rowModel);
                item.add(new AjaxEventBehavior("onclick") {
                    private static final long serialVersionUID = 3673916438509361719L;

                    @Override
                    protected void onEvent(AjaxRequestTarget target) {
                        // UserDO user = rowModel.getObject();
                    }
                });
            }
        };
    }
}