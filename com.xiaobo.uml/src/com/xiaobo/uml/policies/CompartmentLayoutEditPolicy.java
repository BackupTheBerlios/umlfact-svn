package com.xiaobo.uml.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;

import com.xiaobo.uml.model.Attribute;
import com.xiaobo.uml.model.Compartment;
import com.xiaobo.uml.model.Member;
import com.xiaobo.uml.model.Method;
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
	@Override
	protected Command createAddCommand(EditPart child, EditPart after) {
		if (child.getModel() instanceof Member) {
			Member member = (Member) child.getModel();
			Compartment newCompartment = (Compartment) getHost()
					.getModel();
			Compartment oldCompartment = (Compartment) child
					.getParent().getModel();
			if (after != null) {
				return new UmlElementAddCommand(member, newCompartment,
						oldCompartment, (Compartment) after.getModel());
			}
			return new UmlElementAddCommand(member, newCompartment,
					oldCompartment, null);
		}
		return null;
	}

	/**
	 * TODO: inside a compartment, distinguish the attribute and method.
	 */
	@Override
	protected Command createMoveChildCommand(EditPart child, EditPart after) {
		if (child.getModel() instanceof Member) {
			if (after != null) {
				return new UmlElementMoveCommand(
						(Member) child.getModel(),
						(Compartment) getHost().getModel(),
						(Member) after.getModel());
			}
			return new UmlElementMoveCommand((Member) child.getModel(),
					(Compartment) getHost().getModel(), null);
		}
		return null;
	}

	/**
	 * Instead of using the "instanceof", here the "getNewObjectType()" must be
	 * used!
	 */
	@Override
	protected Command getCreateCommand(CreateRequest request) {
		if (request.getNewObjectType() == Attribute.class
				&& ((Compartment) getHost().getModel()).getId().equals(
						Compartment.ATTRIBUTE_ID)) {
			return new UmlElementCreateCommand((Attribute) request
					.getNewObject(), (Compartment) getHost().getModel(),
					getHost().getChildren().indexOf(
							getInsertionReference(request)));
		} else if (request.getNewObjectType() == Method.class
				&& ((Compartment) getHost().getModel()).getId().equals(
						Compartment.METHOD_ID)) {
			return new UmlElementCreateCommand((Method) request
					.getNewObject(), (Compartment) getHost().getModel(),
					getHost().getChildren().indexOf(
							getInsertionReference(request)));
		}
		return null;
	}

}
