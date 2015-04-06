package de.choong.components;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * A static image component. This way the displayed image has a readable and
 * fixed path.
 *
 */
public class StaticImage extends WebMarkupContainer {

    private static final long serialVersionUID = -2694090482093056495L;

    public StaticImage(String id, IModel<String> model) {
        super(id, model);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        tag.append("src", getDefaultModelObjectAsString(), ",");
    }
}
