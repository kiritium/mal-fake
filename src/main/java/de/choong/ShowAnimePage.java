package de.choong;

import java.util.ArrayList;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.choong.dao.AnimeSqliteDBDao;
import de.choong.dao.IAnimeDao;
import de.choong.model.AnimeDO;

public class ShowAnimePage extends BasePage {

	private static final long serialVersionUID = -8485039198076648005L;
	private IAnimeDao<AnimeDO> dao = new AnimeSqliteDBDao();
	
    public ShowAnimePage(final PageParameters parameters) {
        super(parameters);
    }
    
    @Override
    protected void onInitialize() {
        super.onInitialize();
        ArrayList<AnimeDO> allAnimes = dao.readAll();
        
        ListDataProvider<AnimeDO> provider = new ListDataProvider<AnimeDO>(allAnimes);
        
        DataView<AnimeDO> animeRow = new DataView<AnimeDO>("animeRow", provider) {
            private static final long serialVersionUID = -2390389602389777774L;

            @Override
            protected void populateItem(Item<AnimeDO> item) {
                AnimeDO anime = item.getModelObject();
                RepeatingView animeInfo = new RepeatingView("animeInfo");
                
                animeInfo.add(new Label(animeInfo.newChildId(), anime.getId()));
                animeInfo.add(new Label(animeInfo.newChildId(), anime.getTitle()));
                animeInfo.add(new Label(animeInfo.newChildId(), anime.getYear()));
                animeInfo.add(new Label(animeInfo.newChildId(), anime.getAuthor()));
                
                item.add(animeInfo);
            }
        };
        add(animeRow);    
    }
}
