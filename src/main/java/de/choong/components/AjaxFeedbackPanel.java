package de.choong.components;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class AjaxFeedbackPanel extends FeedbackPanel {

	private static final long serialVersionUID = -4139802289071074689L;

	public AjaxFeedbackPanel(String id) {
		super(id);
	}
	
	public AjaxFeedbackPanel(String id, IFeedbackMessageFilter filter) {
		super(id, filter);
	}

	@Override
	protected void onConfigure() {
		super.onConfigure();
        setOutputMarkupPlaceholderTag(true);
	}
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		
		if(getFeedbackMessages().size() > 0) {
            setVisible(true);
            
            FeedbackMessage msg = getFeedbackMessages().first();
            if(msg.isError()) {
            	add(new AttributeModifier("class", "alert alert-danger"));
            } else if(msg.isSuccess()) {
            	add(new AttributeModifier("class", "alert alert-success"));
            } else if(msg.isInfo()) {
            	add(new AttributeModifier("class", "alert alert-info"));
            }
        } else {
        	setVisible(false);
        }
	}
}
