package org.xiaobo.uml.editpolicy;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.xiaobo.uml.command.UmlElementCreateCommand;
import org.xiaobo.uml.editpart.UmlTypeEditPart;
import org.xiaobo.uml.model.UmlElement;
import org.xiaobo.uml.model.UmlType;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlModelLayoutEditPolicy extends LayoutEditPolicy {

	protected EditPolicy createChildEditPolicy(EditPart child) {
		if (child instanceof UmlTypeEditPart)
			return new UmlTypeNonResizableEditPolicy();
		return new NonResizableEditPolicy();
	}

	protected Command getCreateCommand(CreateRequest request) {
		Object obj = request.getNewObject();
		if (obj != null && request.getNewObjectType() == UmlType.class) {
			UmlElementCreateCommand command = new UmlElementCreateCommand();
			command.setParent((UmlElement) this.getHost().getModel());
			command.setChild((UmlElement) obj);
			((UmlType) obj).setLocation(request.getLocation());
			return command;
		}
		return null;
	}

	protected Command getDeleteDependantCommand(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	protected Command getMoveChildrenCommand(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

}
