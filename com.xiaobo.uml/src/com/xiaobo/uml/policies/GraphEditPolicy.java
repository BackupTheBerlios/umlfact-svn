package com.xiaobo.uml.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;

import com.xiaobo.uml.model.TypeModel;
import com.xiaobo.uml.model.UmlModel;
import com.xiaobo.uml.model.command.UmlElementCreateCommand;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */

public class GraphEditPolicy extends ContainerEditPolicy {

	protected Command getAddCommand(GroupRequest request) {
		// CompoundCommand command = new CompoundCommand();
		// for (Iterator i = request.getEditParts().iterator(); i.hasNext();) {
		// EditPart part = (EditPart) i.next();
		// if (part.getModel().getClass() == TypeModel.class) {
		// TypeModel type = (TypeModel) part.getModel();
		// UmlModel newUmlModel = (UmlModel) getHost().getModel();
		// UmlModel oldUmlModel = (UmlModel) part.getParent()
		// .getModel();
		// command.add(new UmlElementAddCommand(type, newUmlModel,
		// oldUmlModel,null));
		// }
		// }
		// if (command.size() > 0) {
		//			return command;
		//		}
		return null;
	}

	protected Command getCreateCommand(CreateRequest request) {
		if (request.getNewObjectType() == TypeModel.class) {
			return new UmlElementCreateCommand((TypeModel) request
					.getNewObject(), (UmlModel) getHost().getModel());
		}
		return null;
	}

	public EditPart getTargetEditPart(Request request) {
		if (REQ_CREATE.equals(request.getType())) {
			return getHost();
		}
		if (REQ_ADD.equals(request.getType())) {
			return getHost();
		}
		return super.getTargetEditPart(request);
	}
}
