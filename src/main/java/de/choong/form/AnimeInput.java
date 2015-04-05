package de.choong.form;

import java.util.Arrays;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;

import de.choong.model.anime.AiringStatus;
import de.choong.model.anime.AnimeDO;
import de.choong.model.anime.MediaType;
import de.choong.model.anime.Season;

public class AnimeInput extends Panel {

    private static final long serialVersionUID = -749279800147765490L;

    public AnimeInput(String id, Model<AnimeDO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        AnimeDO anime = (AnimeDO) getDefaultModelObject();

        // TODO add validation to form
        // TODO fix defaults in edit
        Form<AnimeDO> form = new Form<AnimeDO>("form", Model.of(anime));

        // Title
        form.add(new RequiredTextField<String>("title", new PropertyModel<String>(anime, "title"))
                .add(StringValidator.maximumLength(40)));

        // Alternative title
        form.add(new TextField<String>("alttitle", new PropertyModel<String>(anime, "altTitle")));

        // Summary
        form.add(new TextArea<String>("summary", new PropertyModel<String>(anime, "summary")));

        // Type
        DropDownChoice<MediaType> mediaType = new DropDownChoice<MediaType>("mediaType",
                new PropertyModel<MediaType>(anime, "type"), Arrays.asList(MediaType.values()),
                new IChoiceRenderer<MediaType>() {
                    private static final long serialVersionUID = 3603039597029515468L;

                    @Override
                    public Object getDisplayValue(MediaType object) {
                        return object.getDisplayName();
                    }

                    @Override
                    public String getIdValue(MediaType object, int index) {
                        return "" + index;
                    }
                });
        // TODO bug: this overides a loaded anime! (solution, remove this code
        // and add validation)
        mediaType.setDefaultModelObject(MediaType.TV);
        form.add(mediaType);

        // Status
        DropDownChoice<AiringStatus> status = new DropDownChoice<AiringStatus>("status",
                new PropertyModel<AiringStatus>(anime, "status"), Arrays.asList(AiringStatus
                        .values()), new IChoiceRenderer<AiringStatus>() {
                    private static final long serialVersionUID = -6626428213226462175L;

                    @Override
                    public Object getDisplayValue(AiringStatus object) {
                        return object.getDisplayName();
                    }

                    @Override
                    public String getIdValue(AiringStatus object, int index) {
                        return "" + index;
                    }
                });
        // TODO bug: this overides a loaded anime! (solution, remove this code
        // and add validation)
        status.setDefaultModelObject(AiringStatus.NOT_AIRED_YET);
        form.add(status);

        // Creator
        form.add(new TextField<String>("creator", new PropertyModel<String>(anime, "creator")));

        // Studio
        form.add(new TextField<String>("studio", new PropertyModel<String>(anime, "studio")));

        // Year
        form.add(new TextField<String>("year", new PropertyModel<String>(anime, "year"))
                .add(RangeValidator.range(1900, 9999)));

        // Season
        DropDownChoice<Season> season = new DropDownChoice<Season>("season",
                new PropertyModel<Season>(anime, "season"), Arrays.asList(Season.values()),
                new IChoiceRenderer<Season>() {
                    private static final long serialVersionUID = 8013073021547769860L;

                    @Override
                    public Object getDisplayValue(Season object) {
                        return object.getDisplayName();
                    }

                    @Override
                    public String getIdValue(Season object, int index) {
                        return "" + index;
                    }
                });
        // TODO bug: this overides a loaded anime! (solution, remove this code
        // and add validation)
        season.setDefaultModelObject(Season.NONE);
        form.add(season);

        // Cover
        form.add(new TextField<String>("coverPath", new PropertyModel<String>(anime, "coverPath")));

        add(form);
    }
}