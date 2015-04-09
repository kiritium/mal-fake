package de.choong.pages.admin;

import java.io.File;
import java.io.IOException;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.springframework.util.MimeTypeUtils;

import de.choong.components.AjaxFeedbackPanel;
import de.choong.components.Carousel;
import de.choong.components.FileTable;
import de.choong.form.validator.FileUploadValidator;
import de.choong.model.user.UserRight;
import de.choong.pages.SecurePage;
import de.choong.util.ImageUtil;

public class EditSlideshowPage extends SecurePage {

    private static final long serialVersionUID = 4617706874386311946L;

    private FileUploadField slideUpload;
    private FeedbackPanel feedback;

    public EditSlideshowPage() {
        super();
    }

    // TODO feedback

    @Override
    protected void onInitialize() {
        super.onInitialize();

        // Slideshow images table
        add(new FileTable("table", Model.of(ImageUtil.getAbsoluteSlideshowPath())));

        // Slideshow
        add(new Carousel("carousel", Model.of(ImageUtil.getAbsoluteSlideshowPath())));

        // Upload
        Form<String> form = new Form<String>("form");

        slideUpload = new FileUploadField("upload");
        slideUpload.setLabel(Model.of("Upload"));
        // Size between 1MB and 5MB
        slideUpload.add(FileUploadValidator.sizeBetween(1 * 100000, 5 * 100000));
        slideUpload.add(FileUploadValidator.withContentTypes(MimeTypeUtils.IMAGE_GIF,
                MimeTypeUtils.IMAGE_JPEG, MimeTypeUtils.IMAGE_PNG));
        form.add(slideUpload);
        form.add(new AjaxSubmitLink("submit", form) {

            private static final long serialVersionUID = -5600370514998682986L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                super.onSubmit(target, form);
                FileUpload uploadedFile = slideUpload.getFileUpload();
                if (uploadedFile != null) {
                    File newFile = new File(ImageUtil.getAbsoluteSlideshowPath()
                            + uploadedFile.getClientFileName());
                    try {
                        uploadedFile.writeTo(newFile);
                        setResponsePage(getPage().getClass());
                    } catch (IOException e) {
                        error("Couldn't save file.");
                    }
                }
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                super.onError(target, form);
                target.add(feedback);
            }
        });

        feedback = new AjaxFeedbackPanel("feedback");
        form.add(feedback);

        add(form);
    }

    @Override
    public UserRight getAccessRight() {
        return UserRight.ADMIN;
    }
}