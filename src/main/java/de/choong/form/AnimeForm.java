package de.choong.form;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.choong.components.AjaxFeedbackPanel;
import de.choong.dao.IAnimeDao;
import de.choong.exceptions.DBException;
import de.choong.model.AnimeDO;
import de.choong.util.SpringUtil;

public class AnimeForm extends Panel {

    private static final long serialVersionUID = -749279800147765490L;
    private FeedbackPanel feedback;
    private IAnimeDao dao = (IAnimeDao) SpringUtil.getBean("animeDao");
    private FormMode mode;

    public AnimeForm(String id, Model<AnimeDO> model, FormMode mode) {
        super(id, model);
        this.mode = mode;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        AnimeDO anime = (AnimeDO) getDefaultModelObject();

        Form<AnimeDO> form = new Form<AnimeDO>("form", Model.of(anime));
        form.add(new TextField<String>("title", new PropertyModel<String>(anime, "title")));
        form.add(new TextField<String>("author", new PropertyModel<String>(anime, "author")));
        form.add(new TextField<String>("year", new PropertyModel<String>(anime, "year")));
        form.add(new AjaxSubmitLink("submit", form) {
            private static final long serialVersionUID = -2717359351525157884L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);

                AnimeDO anime = (AnimeDO) form.getModelObject();
                AnimeForm.this.onSubmit(anime, target);
                
                target.add(feedback);
            }
            
            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
            	super.onError(target, form);
            	
            	System.out.println("Error");
            }
        });
        add(form);
        feedback = new AjaxFeedbackPanel("feedback");

        form.add(feedback);
    }

    public void onSubmit(AnimeDO anime, AjaxRequestTarget target) {
        switch (mode) {
        case ADD:
            onAdd(anime, target);
            break;
        case EDIT:
            onEdit(anime, target);
            break;
        default:
            // do nothing
        }
    }

    public void onAdd(AnimeDO anime, AjaxRequestTarget target) {
        try {
            dao.create(anime);
        } catch (DBException ex) {
        	feedback.error("DB Error");
        }
        feedback.success("Anime added.");
    }

    public void onEdit(AnimeDO anime, AjaxRequestTarget target) {
        try {
            dao.update(anime);
        } catch (DBException ex) {
        	feedback.error("DB Error");
        }
        feedback.success("Anime updated.");
    }

    public FeedbackPanel getFeedback() {
        return feedback;
    }

}
