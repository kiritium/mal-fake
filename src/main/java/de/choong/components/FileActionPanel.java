package de.choong.components;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class FileActionPanel extends Panel {

    private static final long serialVersionUID = 6526452991851743237L;

    private File file;

    public FileActionPanel(String id, IModel<File> model) {
        super(id, model);
        this.file = model.getObject();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new AjaxFallbackLink<String>("delete") {

            private static final long serialVersionUID = 3627350249663028432L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                try {
                    Files.delete(file.toPath());
                    setResponsePage(getPage().getClass());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
