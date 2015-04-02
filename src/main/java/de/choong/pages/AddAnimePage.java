package de.choong.pages;

import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.form.AnimeForm;
import de.choong.form.FormMode;
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
