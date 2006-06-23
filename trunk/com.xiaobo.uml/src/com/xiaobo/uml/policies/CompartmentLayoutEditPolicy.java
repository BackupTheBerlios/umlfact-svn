package com.xiaobo.uml.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;

import com.xiaobo.uml.model.AttributeModel;
import com.xiaobo.uml.model.CompartmentModel;
import com.xiaobo.uml.model.MemberModel;
import com.xiaobo.uml.model.MethodModel;
import com.xiaobo.uml.model.command.UmlElementAddCommand;
import com.xiaobo.uml.model.command.UmlElementCreateCommand;
import com.xiaobo.uml.model.command.UmlElementMoveCommand;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class CompartmentLayoutEditPolicy extends ToolbarLayoutEditPolicy {
	/**
	 * TODO: from one compartment to the other, distinguish the attribute and
	 * method.
	 */
	protected Command createAddCommand(EditPart child, EditPart after) {
		if (child.getModel() instanceof MemberModel) {
			MemberModel member = (MemberModel) child.getModel();
			CompartmentModel newCompartment = (CompartmentModel) getHost()
					.getModel();
			CompartmentModel oldCompartment = (CompartmentModel) child
					.getParent().getModel();
			if (after != null) {
				return new UmlElementAddCommand(member, newCompartment,
						oldCompartment, (CompartmentModel) after.getModel());
			}
			return new UmlElementAddCommand(member, newCompartment,
					oldCompartment, null);
		}
		return null;
	}

	/**
	 * TODO: inside a compartment, distinguish the attribute and method.
	 */
	protected Command createMoveChildCommand(EditPart child, EditPart after) {
		if (child.getModel() instanceof MemberModel) {
			if (after != null) {
				return new UmlElementMoveCommand(
						(MemberModel) child.getModel(),
						(CompartmentModel) getHost().getModel(),
						(MemberModel) after.getModel());
			}
			return new UmlElementMoveCommand((MemberModel) child.getModel(),
					(CompartmentModel) getHost().getModel(), null);
		}
		return null;
	}

	/**
	 * Instead of using the "instanceof", here the "getNewObjectType()" must be
	 * used!
	 */
	protected Command getCreateCommand(CreateRequest request) {
		if (request.getNewObjectType() == AttributeModel.class
				&& ((CompartmentModel) getHost().getModel()).getId().equals(
						CompartmentModel.ATTRIBUTE_ID)) {
			return new UmlElementCreateCommand((AttributeModel) request
					.getNewObject(), (CompartmentModel) getHost().getModel(),
					getHost().getChildren().indexOf(
							getInsertionReference(request)));
		} else if (request.getNewObjectType() == MethodModel.class
				&& ((CompartmentModel) getHost().getModel()).getId().equals(
						CompartmentModel.METHOD_ID)) {
			return new UmlElementCreateCommand((MethodModel) request
					.getNewObject(), (CompartmentModel) getHost().getModel(),
					getHost().getChildren().indexOf(
							getInsertionReference(request)));
		}
		return null;
	}

}
