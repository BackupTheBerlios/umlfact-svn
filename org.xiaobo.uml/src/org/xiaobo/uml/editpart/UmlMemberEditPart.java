package org.xiaobo.uml.editpart;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.xiaobo.uml.editor.LeftOrRightAnchor;
import org.xiaobo.uml.editpolicy.UmlMemberNodeEditPolicy;
import org.xiaobo.uml.figure.UmlMemberFigure;
import org.xiaobo.uml.model.UmlMember;
import org.xiaobo.uml.model.UmlElement;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlMemberEditPart extends UmlElementEditPart implements
		NodeEditPart {

	protected IFigure createFigure() {
		return new UmlMemberFigure((UmlMember) getModel());
	}

	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		return new LeftOrRightAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		return new LeftOrRightAnchor(getFigure());
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new LeftOrRightAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new EllipseAnchor(getFigure());
	}

	public List getModelSourceConnections() {
		return ((UmlMember) getModel()).getOuts();
	}

	public List getModelTargetConnections() {
		return ((UmlMember) getModel()).getIncomes();
	}

	protected void createEditPolicies() {
		super.createEditPolicies();
		this.installEditPolicy(EditPolicy.CONNECTION_ROLE,
				new UmlMemberNodeEditPolicy());
	}

	public void propertyChange(PropertyChangeEvent evt) {
		String name = evt.getPropertyName();
		if (name.equals(UmlElement.PRO_CONNECTION_IN))
			this.refreshTargetConnections();
		if (name.equals(UmlElement.PRO_CONNECTION_OUT))
			this.refreshSourceConnections();
		super.propertyChange(evt);
	}
}
