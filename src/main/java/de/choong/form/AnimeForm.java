package de.choong.form;

import java.util.Arrays;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import de.choong.components.AjaxFeedbackPanel;
import de.choong.dao.IAnimeDao;
import de.choong.exceptions.DBException;
import de.choong.model.anime.AiringStatus;
import de.choong.model.anime.AnimeDO;
import de.choong.model.anime.MediaType;
import de.choong.model.anime.Season;
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

        // TODO add validation to form
        Form<AnimeDO> form = new Form<AnimeDO>("form", Model.of(anime));

        // title
        form.add(new TextField<String>("title", new PropertyModel<String>(anime, "title")));

        // alternative title
        form.add(new TextField<String>("alttitle", new PropertyModel<String>(anime, "altTitle")));

        // type
        DropDownChoice<MediaType> mediaType = new DropDownChoice<MediaType>("mediaType",
                new PropertyModel<MediaType>(anime, "type"), Arrays.asList(MediaType.values()));
        mediaType.setDefaultModelObject(MediaType.TV);
        form.add(mediaType);

        // status
        DropDownChoice<AiringStatus> status = new DropDownChoice<AiringStatus>("status",
                new PropertyModel<AiringStatus>(anime, "status"), Arrays.asList(AiringStatus
                        .values()));
        status.setDefaultModelObject(AiringStatus.NOT_AIRED_YET);
        form.add(status);

        // creator
        form.add(new TextField<String>("creator", new PropertyModel<String>(anime, "creator")));

        // studio
        form.add(new TextField<String>("studio", new PropertyModel<String>(anime, "studio")));

        // year
        form.add(new TextField<String>("year", new PropertyModel<String>(anime, "year")));

        // season
        DropDownChoice<Season> season = new DropDownChoice<Season>("season",
                new PropertyModel<Season>(anime, "season"), Arrays.asList(Season.values()));
        season.setDefaultModelObject(Season.SPRING);
        form.add(season);

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

                // TODO react to error
                error("from.onError");
                target.add(feedback);
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

}
