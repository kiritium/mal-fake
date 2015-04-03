package de.choong;

import org.apache.wicket.core.request.handler.ListenerInterfaceRequestHandler;
import org.apache.wicket.core.request.mapper.MountedMapper;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.mapper.info.PageComponentInfo;

public class NoVersioningMount extends MountedMapper {

	public NoVersioningMount(String mountPath, Class<? extends IRequestablePage> pageClass) {
		super(mountPath, pageClass);
	}

	@Override
	protected void encodePageComponentInfo(Url url, PageComponentInfo info) {
		// Normally wicket would render the versioning into the url here
	}

	@Override
	public Url mapHandler(IRequestHandler requestHandler) {
		if (requestHandler instanceof ListenerInterfaceRequestHandler) {
			return null;
		} else {
			return super.mapHandler(requestHandler);
		}
	}

}
