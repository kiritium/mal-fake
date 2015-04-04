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
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.sortable.SortableTable;
import de.choong.model.anime.AnimeDO;
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
		columns.add(createClickablePropertyColumn("ID", "id", "id"));
		columns.add(createClickablePropertyColumn("Title", "title", "title"));
		columns.add(createClickablePropertyColumn("Alt. Title", "altTitle", "altTitle"));
		columns.add(createClickablePropertyColumn("Type", "type", "type.displayName"));
		columns.add(createClickablePropertyColumn("Status", "status", "status.displayName"));
		columns.add(createClickablePropertyColumn("Creator", "creator", "creator"));
		columns.add(createClickablePropertyColumn("Studio", "studio", "studio"));
		columns.add(createClickablePropertyColumn("Season", "season", "season.displayName"));
		columns.add(createClickablePropertyColumn("Year", "year", "year"));

		// TODO show only if user has moderator rights
		columns.add(new AbstractColumn<AnimeDO, String>(new Model<String>("")) {
			private static final long serialVersionUID = 3431476079203912069L;

			@Override
			public void populateItem(Item<ICellPopulator<AnimeDO>> cellItem, String componentId,
					IModel<AnimeDO> rowModel) {
				// TODO action panel (delete, edit)
				cellItem.add(new AnimeActionPanel(componentId, rowModel));
			}
		});

		add(new SortableTable<AnimeDO, String>("table", columns, dataProvider, 10));
	}

	public PropertyColumn<AnimeDO, String> createClickablePropertyColumn(String display,
			String sortProperty, String propertyExpression) {
		return new PropertyColumn<AnimeDO, String>(Model.of(display), sortProperty,
				propertyExpression) {
			private static final long serialVersionUID = 7172331019091215968L;

			@Override
			public void populateItem(Item<ICellPopulator<AnimeDO>> item, String componentId,
					IModel<AnimeDO> rowModel) {
				super.populateItem(item, componentId, rowModel);
				item.add(new AjaxEventBehavior("onclick") {
					private static final long serialVersionUID = 3673916438509361719L;

					@Override
					protected void onEvent(AjaxRequestTarget target) {
						AnimeDO anime = rowModel.getObject();
						int id = anime.getId();
						PageParameters param = new PageParameters();
						param.set("id", id);
						setResponsePage(SingleAnimePage.class, param);
					}
				});
			}
		};
	}
}
