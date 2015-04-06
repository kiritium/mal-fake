package de.choong.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.AjaxFeedbackPanel;
import de.choong.components.UserTable;
import de.choong.components.sortable.SortableUserProvider;
import de.choong.dao.IUserDao;
import de.choong.exceptions.DBException;
import de.choong.form.UserInput;
import de.choong.model.user.UserDO;
import de.choong.model.user.UserRight;
import de.choong.util.SpringUtil;
import de.choong.util.UserUtil;

public class AdministrationPage extends SecurePage {

    private static final long serialVersionUID = 4569728237093473098L;

    FeedbackPanel feedback;
    IUserDao dao = (IUserDao) SpringUtil.getBean("userDao");

    public AdministrationPage() {
        super();
    }

    public AdministrationPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        // User-List
        add(new UserTable("userTable", new SortableUserProvider()));

        // Add user function
        UserDO user = new UserDO();
        Form<UserDO> form = new Form<>("form", Model.of(user));
        form.add(new UserInput("input", Model.of(user)));
        form.add(createSubmitLink("submit", form));

        feedback = new AjaxFeedbackPanel("feedback");
        form.add(feedback);

        add(form);
    }

    private AjaxSubmitLink createSubmitLink(String id, Form<UserDO> form) {
        return new AjaxSubmitLink(id, form) {
            private static final long serialVersionUID = -2717359351525157884L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);

                UserDO user = (UserDO) form.getModelObject();
                // Hashing password
                user.setSalt(UserUtil.generateSalt());
                String hashedPassword = UserUtil.hash(user.getPassword(), user.getSalt());
                user.setPassword(hashedPassword);

                try {
                    dao.create(user);
                    // TODO E-Mail Verification
                    success("User registered.");
                } catch (DBException ex) {
                    error("DB Error");
                }
                target.add(feedback);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                super.onError(target, form);
                target.add(feedback);
            }
        };
    }

    @Override
    public UserRight getAccessRight() {
        return UserRight.ADMIN;
    }
}
