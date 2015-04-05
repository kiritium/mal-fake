package de.choong.form;

import java.io.File;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.lang.Bytes;

public class UploadForm extends Panel {

    private static final long serialVersionUID = 5780363560336140714L;

    public UploadForm(String id) {
        super(id);
    }

    private FileUploadField fileUpload;
    private String UPLOAD_FOLDER = "D:\\";

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new FeedbackPanel("feedback"));

        Form<?> form = new Form<Void>("form") {
            private static final long serialVersionUID = 8246528529251680452L;

            @Override
            protected void onSubmit() {

                final FileUpload uploadedFile = fileUpload.getFileUpload();
                if (uploadedFile != null) {

                    // write to a new file
                    File newFile = new File(UPLOAD_FOLDER + uploadedFile.getClientFileName());

                    if (newFile.exists()) {
                        newFile.delete();
                    }

                    try {
                        newFile.createNewFile();
                        uploadedFile.writeTo(newFile);

                        info("Saved file: " + uploadedFile.getClientFileName());
                    } catch (Exception e) {
                        throw new IllegalStateException("Error");
                    }
                }

            }

        };

        // Enable multipart mode (need for uploads file)
        form.setMultiPart(true);

        // max upload size, 10k
        form.setMaxSize(Bytes.kilobytes(10));

        form.add(fileUpload = new FileUploadField("fileUpload"));

        add(form);
    }
}
