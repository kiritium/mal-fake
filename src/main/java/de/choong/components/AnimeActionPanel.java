package de.choong.components;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.dao.IAnimeDao;
import de.choong.exceptions.DBException;
import de.choong.model.anime.AnimeDO;
import de.choong.pages.anime.EditAnimePage;
import de.choong.util.SpringUtil;

public class AnimeActionPanel extends Panel {
    private static final long serialVersionUID = -2551106877467940713L;

    private int id;
    private IAnimeDao dao = (IAnimeDao) SpringUtil.getBean("animeDao");

    public AnimeActionPanel(String id, IModel<AnimeDO> model) {
        super(id, model);
        this.id = model.getObject().getId();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new AjaxFallbackLink<String>("delete") {
            private static final long serialVersionUID = -983228768596472734L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                try {
                    dao.delete(id);
                    setResponsePage(getPage());
                } catch (DBException e) {
                    e.printStackTrace();
                }
            }

        });

        add(new AjaxFallbackLink<String>("edit") {

            private static final long serialVersionUID = -871356345740847018L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                PageParameters param = new PageParameters();
                param.set("id", AnimeActionPanel.this.id);
                setResponsePage(EditAnimePage.class, param);
            }

        });
    }
}
