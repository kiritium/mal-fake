package de.choong.components;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import de.choong.model.anime.AnimeDO;

public class AnimeActionPanel extends Panel {
	private static final long serialVersionUID = -2551106877467940713L;

	public AnimeActionPanel(String id, IModel<AnimeDO> model) {
		super(id, model);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
	}

}
