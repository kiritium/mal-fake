package de.choong.form;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.choong.dao.UserDao;
import de.choong.exceptions.DBException;
import de.choong.model.UserDO;

public class UserForm extends Panel {

    private static final long serialVersionUID = -6052123356031657622L;

    private FeedbackPanel feedback;
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
                UserForm.this.onSubmit(user, target);

                feedback.setVisible(feedback.getFeedbackMessages().size() > 0);
                target.add(feedback);
            }
        });
        add(form);
        feedback = new FeedbackPanel("feedback");

        feedback.setOutputMarkupPlaceholderTag(true);
        feedback.setVisible(false);
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
            error("DB Error");
        }
        success("User added.");
    }

    public void onEdit(UserDO user, AjaxRequestTarget target) {
        try {
            dao.update(user);
        } catch (DBException ex) {
            error("DB Error");
        }
        success("User updated.");
    }

    public FeedbackPanel getFeedback() {
        return feedback;
    }
}
