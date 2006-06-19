package com.xiaobo.uml.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import com.xiaobo.uml.figure.ILabeledFigure;
import com.xiaobo.uml.model.NamedElement;
import com.xiaobo.uml.model.command.NameChangeCommand;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */

public class LabelDirectEditPolicy extends DirectEditPolicy {

	protected Command getDirectEditCommand(DirectEditRequest request) {
		NamedElement element = (NamedElement) getHost().getModel();
		CellEditor cellEditor = request.getCellEditor();
		if (cellEditor.isValueValid()) {
			return new NameChangeCommand(element, (String) cellEditor
					.getValue());
		}
		return null;
	}

	protected void showCurrentEditValue(DirectEditRequest request) {
		ILabeledFigure figure = (ILabeledFigure) getHostFigure();
		figure.getLabel().setText((String) request.getCellEditor().getValue());
	}
}
