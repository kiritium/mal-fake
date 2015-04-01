package de.choong.components;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.choong.model.AnimeDO;

public abstract class AnimeEditForm extends Panel {

    private static final long serialVersionUID = -749279800147765490L;

    private FeedbackPanel feedback;

    public AnimeEditForm(String id, Model<AnimeDO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        AnimeDO anime = (AnimeDO) getDefaultModelObject();

        Form<AnimeDO> form = new Form<AnimeDO>("form", Model.of(anime));
        form.add(new TextField<String>("title", new PropertyModel<String>(
                anime, "title")));
        form.add(new TextField<String>("author", new PropertyModel<String>(
                anime, "author")));
        form.add(new TextField<String>("year", new PropertyModel<String>(anime,
                "year")));
        form.add(new AjaxSubmitLink("submit", form) {
            private static final long serialVersionUID = -2717359351525157884L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);
                AnimeEditForm.this.onSubmit(target, form);

                getFeedback().setVisible(true);
                target.add(getFeedback());
            }
        });
        add(form);
        form.setOutputMarkupId(true);
        feedback = new FeedbackPanel("feedback");
        feedback.setOutputMarkupId(true);
        feedback.setVisible(false);
        form.add(feedback);
    }

    public abstract void onSubmit(AjaxRequestTarget target, Form<?> form);

    public FeedbackPanel getFeedback() {
        return feedback;
    }

}
