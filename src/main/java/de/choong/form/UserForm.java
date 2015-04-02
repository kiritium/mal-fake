package de.choong.form;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.choong.UserRight;
import de.choong.components.AjaxFeedbackPanel;
import de.choong.dao.UserDao;
import de.choong.exceptions.DBException;
import de.choong.model.UserDO;
import de.choong.util.UserUtil;

public class UserForm extends Panel {

    private static final long serialVersionUID = -6052123356031657622L;

    private FeedbackPanel feedback;
    // TODO Load with spring + IUserDao
    private UserDao dao = new UserDao();
    private FormMode mode;

    public UserForm(String id, Model<UserDO> model, FormMode mode) {
        super(id, model);
        this.mode = mode;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        super.onInitialize();
        UserDO user = (UserDO) getDefaultModelObject();

        Form<UserDO> form = new Form<UserDO>("form", Model.of(user));
        form.add(new TextField<String>("username", new PropertyModel<String>(user, "username")));
        form.add(new PasswordTextField("password", new PropertyModel<String>(user, "password")));
        form.add(new AjaxSubmitLink("submit", form) {
            private static final long serialVersionUID = -2717359351525157884L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);

                UserDO user = (UserDO) form.getModelObject();
                user.setSalt(UserUtil.generateSalt());
                String hashedPassword = UserUtil.hash(user.getPassword(), user.getSalt());
                user.setPassword(StringUtils.substring(hashedPassword, 0, 20));
                
                // TODO set via Dropdown
                user.setUserRight(UserRight.USER);
                
                UserForm.this.onSubmit(user, target);

                target.add(feedback);
            }
        });
        add(form);
        
        feedback = new AjaxFeedbackPanel("feedback");
        form.add(feedback);
    }

    public void onSubmit(UserDO user, AjaxRequestTarget target) {
        switch (mode) {
        case ADD:
            onAdd(user, target);
            break;
        case EDIT:
            onEdit(user, target);
            break;
        default:
            // do nothing
        }
    }

    public void onAdd(UserDO user, AjaxRequestTarget target) {
        try {
            dao.create(user);
        } catch (DBException ex) {
        	feedback.error("DB Error");
        }
        feedback.success("User added.");
    }

    public void onEdit(UserDO user, AjaxRequestTarget target) {
        try {
            dao.update(user);
        } catch (DBException ex) {
        	feedback.error("DB Error");
        }
        feedback.success("User updated.");
    }
}
