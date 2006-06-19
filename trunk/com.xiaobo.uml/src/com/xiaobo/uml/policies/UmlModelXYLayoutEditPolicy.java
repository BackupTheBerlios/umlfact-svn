package com.xiaobo.uml.policies;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;

import com.xiaobo.uml.model.TypeModel;
import com.xiaobo.uml.model.UmlModel;
import com.xiaobo.uml.model.command.PositionableElementCreateCommand;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */

public class UmlModelXYLayoutEditPolicy extends UmlXYLayoutEditPolicy {

	protected Command getCreateCommand(CreateRequest request) {
		if (request.getNewObjectType() == TypeModel.class) {
			UmlModel model = ((UmlModel) getHost().getModel());
			Rectangle bounds = new Rectangle(
					((Rectangle) getConstraintFor(request)).getLocation(),
					new Dimension(0, 0));
			return new PositionableElementCreateCommand((TypeModel) request
					.getNewObject(), model, bounds);
		}
		return null;
	}
}
