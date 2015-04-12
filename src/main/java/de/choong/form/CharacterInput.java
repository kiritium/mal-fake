package de.choong.form;

import java.util.List;

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
import org.apache.wicket.validation.validator.StringValidator;
import org.springframework.util.MimeTypeUtils;

import de.choong.form.validator.FileUploadValidator;
import de.choong.model.anime.CharacterDO;

public class CharacterInput extends Panel {

    private static final long serialVersionUID = -7524046497834928443L;

    public CharacterInput(String id, IModel<CharacterDO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        CharacterDO character = (CharacterDO) getDefaultModelObject();

        Form<CharacterDO> form = new Form<>("form");

        // Name
        form.add(new RequiredTextField<String>("name", new PropertyModel<>(character, "name"))
                .setLabel(Model.of("Name"))
                .add(StringValidator.maximumLength(40)));

        // Alternative name
        form.add(new TextField<String>("altName", new PropertyModel<>(character, "altName"))
                .setLabel(Model.of("Alt. name"))
                .add(StringValidator.maximumLength(80)));

        // Description
        form.add(new TextArea<String>("summary",
                new PropertyModel<String>(character, "description"))
                .setLabel(Model.of("Description"))
                .add(StringValidator.maximumLength(750)));

        // Profile image
        FileUploadField profileImg = new FileUploadField("profileImg",
                new PropertyModel<List<FileUpload>>(
                        character, "profileImgs"));
        profileImg.setLabel(Model.of("Profile image"));
        // Size between 50B and 500kB
        profileImg.add(FileUploadValidator.sizeBetween(50, 500 * 1000));
        profileImg.add(FileUploadValidator.withContentTypes(MimeTypeUtils.IMAGE_GIF,
                MimeTypeUtils.IMAGE_JPEG, MimeTypeUtils.IMAGE_PNG));
        form.add(profileImg);

        add(form);
    }
}
