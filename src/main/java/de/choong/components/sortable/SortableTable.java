package de.choong.components.sortable;

import java.util.List;

import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackHeadersToolbar;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxNavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NoRecordsToolbar;

public class SortableTable<T, S> extends DataTable<T, S> {

	private static final long serialVersionUID = -5349669651867050867L;
	private ISortableDataProvider<T, S> dataProvider;

	public SortableTable(String id, List<? extends IColumn<T, S>> columns,
			ISortableDataProvider<T, S> dataProvider, int rowsPerPage) {
		super(id, columns, dataProvider, rowsPerPage);
		this.dataProvider = dataProvider;
	}

	@Override
	protected void onConfigure() {
		super.onConfigure();
		
		setOutputMarkupId(true);
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();

		addTopToolbar(new AjaxFallbackHeadersToolbar<S>(this, dataProvider));
		addBottomToolbar(new NoRecordsToolbar(this));
		addBottomToolbar(new AjaxNavigationToolbar(this));
	}
	
//	@Override
//	protected Item<T> newRowItem(final String id, final int index, final IModel<T> model)
//	{
//		return new OddEvenItem<T>(id, index, model);
//	}
	
}
