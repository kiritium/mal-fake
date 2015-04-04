package de.choong.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.UserTable;
import de.choong.components.sortable.SortableUserProvider;

public class MultiUserPage extends BasePage {

    private static final long serialVersionUID = 4569728237093473098L;

    public MultiUserPage() {
        super();
    }

    public MultiUserPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new UserTable("userTable", new SortableUserProvider()));
    }
}
