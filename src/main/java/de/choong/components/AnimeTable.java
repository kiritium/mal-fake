package de.choong.components;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;

import de.choong.model.AnimeDO;

public class AnimeTable extends Panel {

    private static final long serialVersionUID = -3768439988615983395L;

    private IDataProvider<AnimeDO> dataProvider;

    public AnimeTable(String id, IDataProvider<AnimeDO> dataProvider) {
        super(id);
        this.dataProvider = dataProvider;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        DataView<AnimeDO> animeRow = new DataView<AnimeDO>("animeRow", dataProvider) {
            private static final long serialVersionUID = -2390389602389777774L;

            @Override
            protected void populateItem(Item<AnimeDO> item) {
                AnimeDO anime = item.getModelObject();
                RepeatingView animeInfo = new RepeatingView("animeInfo");

                animeInfo.add(new Label(animeInfo.newChildId(), anime.getId()));
                animeInfo.add(new Label(animeInfo.newChildId(), anime.getTitle()));
                animeInfo.add(new Label(animeInfo.newChildId(), anime.getCreator()));
                animeInfo.add(new Label(animeInfo.newChildId(), anime.getStudio()));
                animeInfo.add(new Label(animeInfo.newChildId(), anime.getYear()));

                item.add(animeInfo);
            }
        };
        add(animeRow);
    }
}
