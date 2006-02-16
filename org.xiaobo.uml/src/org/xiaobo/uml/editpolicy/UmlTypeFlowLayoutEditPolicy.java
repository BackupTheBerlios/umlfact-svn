package org.xiaobo.uml.editpolicy;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.xiaobo.uml.command.UmlElementCreateCommand;
import org.xiaobo.uml.editpart.UmlMemberEditPart;
import org.xiaobo.uml.model.UmlMember;
import org.xiaobo.uml.model.UmlElement;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlTypeFlowLayoutEditPolicy extends FlowLayoutEditPolicy {

	protected EditPolicy createChildEditPolicy(EditPart child) {
		if (child instanceof UmlMemberEditPart)
			return new UmlMemberSelectionEditPolicy();
		return super.createChildEditPolicy(child);
	}

	protected Command createAddCommand(EditPart child, EditPart after) {
		// TODO Auto-generated method stub
		return null;
	}

	protected Command createMoveChildCommand(EditPart child, EditPart after) {
		// TODO Auto-generated method stub
		return null;
	}

	protected Command getCreateCommand(CreateRequest request) {
		Object obj = request.getNewObject();
		if (obj != null && request.getNewObjectType() == UmlMember.class) {
			UmlElementCreateCommand command = new UmlElementCreateCommand();
			command.setParent((UmlElement) this.getHost().getModel());
			command.setChild((UmlElement) obj);

			EditPart after = getInsertionReference(request);
			int index = getHost().getChildren().indexOf(after);
			command.setIndex(index);
			return command;
		}
		return null;
	}

	protected Command getDeleteDependantCommand(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	protected boolean isHorizontal() {
		IFigure figure = ((GraphicalEditPart) getHost()).getContentPane();
		LayoutManager layout = figure.getLayoutManager();
		if (layout instanceof FlowLayout)
			return ((FlowLayout) figure.getLayoutManager()).isHorizontal();
		if (layout instanceof ToolbarLayout)
			return ((ToolbarLayout) figure.getLayoutManager()).isHorizontal();
		return false;
	}
}
