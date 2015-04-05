package de.choong.components.sortable;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.hibernate.criterion.Order;

import de.choong.dao.IAnimeDao;
import de.choong.exceptions.DBException;
import de.choong.model.anime.AnimeDO;
import de.choong.util.SpringUtil;

public class SortableAnimeProvider extends SortableDataProvider<AnimeDO, String> {

    private static final long serialVersionUID = 7588295684422539794L;

    private IAnimeDao dao = (IAnimeDao) SpringUtil.getBean("animeDao");

    public SortableAnimeProvider() {
        super();
        this.setSort("id", SortOrder.ASCENDING);
    }

    @Override
    public Iterator<AnimeDO> iterator(long first, long count) {
        try {
            return dao.readWithLimit((int) first, (int) count, getSortOrder()).iterator();
        } catch (DBException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long size() {
        try {
            return dao.countAll();
        } catch (DBException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public IModel<AnimeDO> model(AnimeDO object) {
        return Model.of(object);
    }

    public Order getSortOrder() {
        SortParam<String> sort = getSort();
        if (sort.isAscending()) {
            return Order.asc(sort.getProperty());
        } else {
            return Order.desc(sort.getProperty());
        }
    }

}
