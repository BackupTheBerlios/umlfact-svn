package com.xiaobo.uml.parts;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;

import com.xiaobo.uml.figure.EditableLabel;
import com.xiaobo.uml.figure.ILabeledFigure;
import com.xiaobo.uml.model.NamedElement;
import com.xiaobo.uml.policies.LabelCellEditorLocator;
import com.xiaobo.uml.policies.LabelDirectEditManager;
import com.xiaobo.uml.policies.LabelDirectEditPolicy;
import com.xiaobo.uml.policies.UmlComponentEditPolicy;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */

public abstract class NamedElementPart extends UmlElementPart {

	protected DirectEditManager manager;

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new LabelDirectEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new UmlComponentEditPolicy());
	}

	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_DIRECT_EDIT) {
			if (request instanceof DirectEditRequest
					&& !directEditHitTest(((DirectEditRequest) request)
							.getLocation().getCopy()))
				return;
			performDirectEdit();
		}
	}

	private boolean directEditHitTest(Point requestLoc) {
		ILabeledFigure figure = (ILabeledFigure) getFigure();
		EditableLabel nameLabel = figure.getLabel();
		nameLabel.translateToRelative(requestLoc);
		return nameLabel.containsPoint(requestLoc);
	}

	protected void performDirectEdit() {
		if (manager == null) {
			ILabeledFigure figure = (ILabeledFigure) getFigure();
			manager = new LabelDirectEditManager(this, TextCellEditor.class,
					new LabelCellEditorLocator(figure.getLabel()));
		}
		manager.show();
	}

	public IFigure getContentPane() {
		return ((ILabeledFigure) getFigure()).getContentPane();
	}

	protected void refreshVisuals() {
		ILabeledFigure figure = (ILabeledFigure) getFigure();
		Label figureLabel = figure.getLabel();
		NamedElement model = (NamedElement) getModel();
		figureLabel.setText(model.getName());

		getFigure().revalidate();
	}

	public void propertyChange(PropertyChangeEvent event) {
		refreshVisuals();
	}
}
