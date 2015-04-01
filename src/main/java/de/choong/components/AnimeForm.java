package de.choong.components;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.choong.dao.AnimeSqliteDBDao;
import de.choong.dao.IAnimeDao;
import de.choong.exceptions.DBException;
import de.choong.model.AnimeDO;

public class AnimeForm extends Panel {

    private static final long serialVersionUID = -749279800147765490L;
    private FeedbackPanel feedback;
    private IAnimeDao<AnimeDO> dao = new AnimeSqliteDBDao();
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
                
                AnimeDO anime = (AnimeDO) form.getModelObject();
                AnimeForm.this.onSubmit(anime, target);
                
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
    
    public void onSubmit(AnimeDO anime, AjaxRequestTarget target) {
    	switch(mode) {
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
        } catch(DBException ex) {
        	error("DB Error");
        }
    	success("Anime added.");
    }
    
    public void onEdit(AnimeDO anime, AjaxRequestTarget target) {
    	try {
    		dao.update(anime);
    	} catch(DBException ex) {
    		error("DB Error");
    	}
    	success("Anime updated.");
    }

    public FeedbackPanel getFeedback() {
        return feedback;
    }

}
