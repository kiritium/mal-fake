package de.choong.form;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.springframework.util.MimeTypeUtils;

import de.choong.components.DisplayableDropDownChoice;
import de.choong.form.validator.FileUploadValidator;
import de.choong.model.anime.AiringStatus;
import de.choong.model.anime.AnimeDO;
import de.choong.model.anime.Genre;
import de.choong.model.anime.MediaType;
import de.choong.model.anime.Season;

public class AnimeInput extends Panel {

    private static final long serialVersionUID = -749279800147765490L;

    public AnimeInput(String id, IModel<AnimeDO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        AnimeDO anime = (AnimeDO) getDefaultModelObject();

        Form<AnimeDO> form = new Form<AnimeDO>("form", Model.of(anime));

        // Title
        form.add(new RequiredTextField<String>("title", new PropertyModel<String>(anime, "title"))
                .setLabel(Model.of("Title"))
                .add(StringValidator.maximumLength(40)));

        // Alternative title
        form.add(new TextField<String>("alttitle", new PropertyModel<String>(anime, "altTitle"))
                .setLabel(Model.of("Alt. title"))
                .add(StringValidator.maximumLength(40)));
        // Summary
        form.add(new TextArea<String>("summary", new PropertyModel<String>(anime, "summary"))
                .setLabel(Model.of("Summary"))
                .add(StringValidator.maximumLength(750)));

        // Type
        DropDownChoice<MediaType> mediaType = new DropDownChoice<MediaType>("mediaType",
                new PropertyModel<MediaType>(anime, "type"), Arrays.asList(MediaType.values()));
        mediaType.setDefaultModelObject(anime.getType() != null ? anime.getType() : MediaType.TV);
        mediaType.setLabel(Model.of("Type"));
        form.add(mediaType);

        // Status
        DropDownChoice<AiringStatus> status = new DisplayableDropDownChoice<AiringStatus>("status",
                new PropertyModel<AiringStatus>(anime, "status"), Arrays.asList(AiringStatus
                        .values()));
        status.setDefaultModelObject(anime.getStatus() != null ? anime.getStatus()
                : AiringStatus.NOT_AIRED_YET);
        status.setLabel(Model.of("Status"));
        form.add(status);

        // Creator
        form.add(new TextField<String>("creator", new PropertyModel<String>(anime, "creator"))
                .setLabel(Model.of("Creator"))
                .add(StringValidator.maximumLength(30)));

        // Studio
        form.add(new TextField<String>("studio", new PropertyModel<String>(anime, "studio"))
                .setLabel(Model.of("Studio"))
                .add(StringValidator.maximumLength(20)));

        // Year
        form.add(new TextField<String>("year", new PropertyModel<String>(anime, "year"))
                .add(RangeValidator.range(1900, 9999)));

        // Season
        DropDownChoice<Season> season = new DisplayableDropDownChoice<Season>("season",
                new PropertyModel<Season>(anime, "season"), Arrays.asList(Season.values()));
        season.setDefaultModelObject(anime.getSeason() != null ? anime.getSeason() : Season.NONE);
        season.setLabel(Model.of("Season"));
        form.add(season);

        // Genres
        CheckBoxMultipleChoice<Genre> checkBoxGenres = new CheckBoxMultipleChoice<Genre>("genres",
                new PropertyModel<>(anime, "genres"), Arrays.asList(Genre.values()));
        checkBoxGenres.setLabel(Model.of("Genres(s)"));
        checkBoxGenres.setPrefix("<div class='col-md-4 col-xs-6'>");
        checkBoxGenres.setSuffix("</div>");
        form.add(checkBoxGenres);

        // Cover
        FileUploadField cover = new FileUploadField("cover", new PropertyModel<List<FileUpload>>(
                anime, "covers"));
        cover.setLabel(Model.of("Cover"));
        // Size between 50B and 500kB
        cover.add(FileUploadValidator.sizeBetween(50, 500 * 1000));
        cover.add(FileUploadValidator.withContentTypes(MimeTypeUtils.IMAGE_GIF,
                MimeTypeUtils.IMAGE_JPEG, MimeTypeUtils.IMAGE_PNG));
        form.add(cover);

        add(form);
    }
}