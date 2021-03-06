package com.xiaobo.uml.parts;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
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

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new LabelDirectEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new UmlComponentEditPolicy());
	}

	@Override
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

	/**
	 * In GEF for a Editpart, the child editpart's figure is painted on this
	 * Editpart's figure, that means, this is a recurisve process. Here man
	 * decides the container figure of the editpart's figure for the child
	 * figure.
	 */
	@Override
	public IFigure getContentPane() {
		return ((ILabeledFigure) getFigure()).getContentPane();
	}

	@Override
	protected void refreshVisuals() {
		ILabeledFigure figure = (ILabeledFigure) getFigure();
		EditableLabel label = figure.getLabel();
		NamedElement model = (NamedElement) getModel();
		label.setText(model.getName());
		label.setTooltipText(model.getDescription());
	}

	public void propertyChange(PropertyChangeEvent event) {
		refreshVisuals();
	}
}
