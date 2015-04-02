package de.choong.components;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

/**
 * FeedbackPanel that sets it's visibility and visuals according to
 * feedbackMessages in the back. You need to add this component to the
 * AjaxResponseTarget to update it's internals.
 *
 */
public class AjaxFeedbackPanel extends FeedbackPanel {

	private static final long serialVersionUID = -4139802289071074689L;

	public AjaxFeedbackPanel(String id) {
		super(id);
	}

	public AjaxFeedbackPanel(String id, IFeedbackMessageFilter filter) {
		super(id, filter);
	}

	/**
	 * Called before onBeforeRender. Sets this component visible when messages
	 * are available.
	 */
	@Override
	protected void onConfigure() {
		super.onConfigure();
		setOutputMarkupPlaceholderTag(true);

		if (getFeedbackMessages().isEmpty()) {
			setVisible(false);
		} else {
			setVisible(true);
		}

	}

	/**
	 * Called when the compenent is visible and about to be rendered. E.g. when
	 * the component is added to the AjaxRequestTarget.
	 */
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();

		if (getFeedbackMessages().isEmpty() == false) {
			FeedbackMessage msg = getFeedbackMessages().first();
			if (msg.isError()) {
				add(new AttributeModifier("class", "alert alert-danger"));
			} else if (msg.isSuccess()) {
				add(new AttributeModifier("class", "alert alert-success"));
			} else if (msg.isInfo()) {
				add(new AttributeModifier("class", "alert alert-info"));
			}
		}
	}
}
