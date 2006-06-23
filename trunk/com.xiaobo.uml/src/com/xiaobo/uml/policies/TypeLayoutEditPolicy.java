package com.xiaobo.uml.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */

public class TypeLayoutEditPolicy extends ToolbarLayoutEditPolicy {

	protected Command createAddCommand(EditPart child, EditPart after) {
//		if (child.getModel().getClass() == CompartmentModel.class) {
//			CompartmentModel compartment = (CompartmentModel) child.getModel();
//			TypeModel newType = (TypeModel) getHost().getModel();
//			TypeModel oldType = (TypeModel) child.getParent().getModel();
//			if (after != null) {
//				return new UmlElementAddCommand(compartment, newType, oldType,
//						(CompartmentModel) after.getModel());
//			}
//			return new UmlElementAddCommand(compartment, newType, oldType, null);
//		}
		return null;
	}

	protected Command createMoveChildCommand(EditPart child, EditPart after) {
//		if (child.getModel().getClass() == CompartmentModel.class) {
//			if (after != null) {
//				return new UmlElementMoveCommand((CompartmentModel) child
//						.getModel(), (TypeModel) getHost().getModel(),
//						(CompartmentModel) after.getModel());
//			}
//			return new UmlElementMoveCommand((CompartmentModel) child
//					.getModel(), (TypeModel) getHost().getModel(), null);
//		}
		return null;
	}

	protected Command getCreateCommand(CreateRequest request) {
//		 if (request.getNewObjectType() == CompartmentModel.class) {
//		 return new UmlElementCreateCommand((CompartmentModel) request
//		 .getNewObject(), (TypeModel) getHost().getModel(),
//		 getHost().getChildren().indexOf(
//		 getInsertionReference(request)));
//				}
		return null;
	}
}
