package de.choong.components;

import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

import de.choong.model.Displayable;

public class DisplayableDropDownChoice<T extends Displayable> extends DropDownChoice<T> {

    private static final long serialVersionUID = -8654104575082224028L;

    public DisplayableDropDownChoice(String id, IModel<T> model, List<T> choices) {

        super(id, model, choices, new IChoiceRenderer<T>() {
            private static final long serialVersionUID = 8013073021547769860L;

            @Override
            public Object getDisplayValue(T object) {
                return object.getDisplayName();
            }

            @Override
            public String getIdValue(T object, int index) {
                return "" + index;
            }
        });
    }

}
