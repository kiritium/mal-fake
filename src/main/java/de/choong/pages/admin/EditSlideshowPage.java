package de.choong.pages;

import java.io.File;
import java.io.IOException;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.Model;
import org.springframework.util.MimeTypeUtils;

import de.choong.components.Carousel;
import de.choong.components.FileTable;
import de.choong.form.validator.FileUploadValidator;
import de.choong.model.user.UserRight;
import de.choong.util.ImageUtil;

public class EditSlideshowPage extends SecurePage {

    private static final long serialVersionUID = 4617706874386311946L;

    private FileUploadField slideUpload;

    public EditSlideshowPage() {
        super();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new FileTable("table", Model.of(ImageUtil.getAbsoluteSlideshowPath())));

        add(new Carousel("carousel", Model.of(ImageUtil.getAbsoluteSlideshowPath())));

        Form<String> form = new Form<String>("form") {

            private static final long serialVersionUID = -1031946019410923517L;

            @Override
            protected void onSubmit() {
                super.onSubmit();
                FileUpload uploadedFile = slideUpload.getFileUpload();
                File newFile = new File(ImageUtil.getAbsoluteSlideshowPath()
                        + uploadedFile.getClientFileName());
                try {
                    uploadedFile.writeTo(newFile);
                    // TODO refresh
                    setResponsePage(getPage());
                } catch (IOException e) {
                    System.out.println("error");
                    e.printStackTrace();
                }
            }
        };

        // Cover
        slideUpload = new FileUploadField("upload");
        slideUpload.setLabel(Model.of("Upload"));
        // Size between 50B and 500kB
        slideUpload.add(FileUploadValidator.sizeBetween(50, 500 * 1000));
        slideUpload.add(FileUploadValidator.withContentTypes(MimeTypeUtils.IMAGE_GIF,
                MimeTypeUtils.IMAGE_JPEG, MimeTypeUtils.IMAGE_PNG));
        form.add(slideUpload);

        add(form);
    }

    @Override
    public UserRight getAccessRight() {
        return UserRight.ADMIN;
    }
}