package de.choong.components;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.hibernate.criterion.Order;

import de.choong.dao.AnimeHibernateDBDao;
import de.choong.model.AnimeDO;

public class SortableAnimeProvider extends SortableDataProvider<AnimeDO, String>{

	private static final long serialVersionUID = 7588295684422539794L;

	public SortableAnimeProvider() {
		super();
		this.setSort("id", SortOrder.ASCENDING);
	}
	
	@Override
	public Iterator<AnimeDO> iterator(long first, long count) {
		return new AnimeHibernateDBDao().readWithLimit((int) first, (int) count, getSortOrder()).iterator();
	}

	@Override
	public long size() {
		return new AnimeHibernateDBDao().countAll();
	}

	@Override
	public IModel<AnimeDO> model(AnimeDO object) {
		return Model.of(object);
	}
	
	public Order getSortOrder() {
		SortParam<String> sort = getSort();
		if(sort.isAscending()) {
			return Order.asc(sort.getProperty());
		} else {
			return Order.desc(sort.getProperty());
		}
	}


}
