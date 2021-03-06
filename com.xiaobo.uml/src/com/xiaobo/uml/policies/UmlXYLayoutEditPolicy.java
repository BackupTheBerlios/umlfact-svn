package com.xiaobo.uml.policies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;

import com.xiaobo.uml.model.IPositionableElement;
import com.xiaobo.uml.model.command.PositionChangeCommand;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public abstract class UmlXYLayoutEditPolicy extends XYLayoutEditPolicy {

	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		return new NonResizableEditPolicy();
	}

	@Override
	protected Command createAddCommand(EditPart child, Object constraint) {
		return null;
	}

	@Override
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		if (constraint instanceof Rectangle) {
			if (child.getModel() instanceof IPositionableElement) {
				Rectangle bounds = (Rectangle) translateToModelConstraint(constraint);
				IPositionableElement positionableElement = (IPositionableElement) child
						.getModel();
				if (bounds.getLocation().x >= 0 && bounds.getLocation().y >= 0) {
					return new PositionChangeCommand(positionableElement,
							bounds);
				}
			}
		}
		return null;
	}

	@Override
	protected Command getDeleteDependantCommand(Request request) {
		return null;
	}
}
