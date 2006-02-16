package org.xiaobo.uml.command;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.xiaobo.uml.model.UmlType;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlTypeMoveCommand extends Command {

	private ChangeBoundsRequest request;

	private UmlType model;

	public void execute() {
		Point old = getModel().getLocation();
		int x = request.getMoveDelta().x;
		int y = request.getMoveDelta().y;

		getModel().setLocation(new Point(old.x + x, old.y + y));
	}

	public ChangeBoundsRequest getRequest() {
		return request;
	}

	public void setRequest(ChangeBoundsRequest request) {
		this.request = request;
	}

	public UmlType getModel() {
		return model;
	}

	public void setModel(UmlType model) {
		this.model = model;
	}
}
