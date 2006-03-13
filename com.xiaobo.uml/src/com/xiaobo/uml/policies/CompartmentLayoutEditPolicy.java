package com.xiaobo.uml.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;

import com.xiaobo.uml.model.CompartmentModel;
import com.xiaobo.uml.model.MemberModel;
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

	protected Command createAddCommand(EditPart child, EditPart after) {
		if (child.getModel().getClass() == MemberModel.class) {
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

	protected Command createMoveChildCommand(EditPart child, EditPart after) {
		if (child.getModel().getClass() == MemberModel.class) {
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

	protected Command getCreateCommand(CreateRequest request) {
		if (request.getNewObjectType() == MemberModel.class) {
			return new UmlElementCreateCommand((MemberModel) request
					.getNewObject(), (CompartmentModel) getHost().getModel(),
					getHost().getChildren().indexOf(
							getInsertionReference(request)));
		}
		return null;
	}

}
