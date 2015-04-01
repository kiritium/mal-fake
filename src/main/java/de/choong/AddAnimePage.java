package de.choong;

import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.components.AnimeForm;
import de.choong.components.FormMode;
import de.choong.model.AnimeDO;

public class AddAnimePage extends BasePage {

    private static final long serialVersionUID = 8623937508924029855L;

    public AddAnimePage() {
        super();
    }

    public AddAnimePage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        AnimeDO anime = new AnimeDO();
        add(new AnimeForm("form", Model.of(anime), FormMode.ADD));
    }

}
