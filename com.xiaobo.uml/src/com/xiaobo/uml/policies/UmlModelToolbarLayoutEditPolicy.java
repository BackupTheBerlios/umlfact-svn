package com.xiaobo.uml.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;

import com.xiaobo.uml.model.Type;
import com.xiaobo.uml.model.UmlModel;
import com.xiaobo.uml.model.command.UmlElementCreateCommand;

/**
 * 
 * @author xiaobo. Created on Jul 3, 2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All rights reserved.
 * 
 */
public class UmlModelToolbarLayoutEditPolicy extends ToolbarLayoutEditPolicy {

	@Override
	protected Command createAddCommand(EditPart child, EditPart after) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Command createMoveChildCommand(EditPart child, EditPart after) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		if (request.getNewObjectType() == Type.class) {
			return new UmlElementCreateCommand((Type) request.getNewObject(),
					(UmlModel) getHost().getModel(), getHost().getChildren()
							.indexOf(getInsertionReference(request)));
		}
		return null;
	}
}
