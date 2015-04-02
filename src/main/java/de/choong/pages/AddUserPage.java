package de.choong.pages;

import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.form.FormMode;
import de.choong.form.UserForm;
import de.choong.model.UserDO;

public class AddUserPage extends BasePage {

    private static final long serialVersionUID = -154289454209633122L;

    public AddUserPage() {

    }

    public AddUserPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        UserDO user = new UserDO();
        add((new UserForm("form", Model.of(user), FormMode.ADD)));
    }

}
