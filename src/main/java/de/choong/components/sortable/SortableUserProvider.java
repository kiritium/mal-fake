package de.choong.components.sortable;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.hibernate.criterion.Order;

import de.choong.dao.IUserDao;
import de.choong.model.user.UserDO;
import de.choong.util.SpringUtil;

public class SortableUserProvider extends SortableDataProvider<UserDO, String> {
    private static final long serialVersionUID = -5430429109504375946L;

    private IUserDao dao = (IUserDao) SpringUtil.getBean("userDao");

    public SortableUserProvider() {
        super();
        this.setSort("id", SortOrder.ASCENDING);
    }

    @Override
    public Iterator<UserDO> iterator(long first, long count) {
        dao.readWithLimit((int) first, (int) count, getSortOrder()).iterator();
        return null;
    }

    @Override
    public long size() {
        return dao.countAll();
    }

    @Override
    public IModel<UserDO> model(UserDO object) {
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
