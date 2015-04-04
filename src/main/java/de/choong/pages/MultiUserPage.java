package de.choong.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.UserTable;
import de.choong.components.sortable.SortableUserProvider;
import de.choong.model.user.UserRight;

public class MultiUserPage extends SecurePage {

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

    @Override
    public UserRight getAccessRight() {
        return UserRight.ADMIN;
    }
}
