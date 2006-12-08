/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
package com.xiaobo.uml.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;

import com.xiaobo.uml.model.ConnectionModel;
import com.xiaobo.uml.policies.ConnectionBendpointEditPolicy;
import com.xiaobo.uml.policies.ConnectionSelectEditPolicy;
import com.xiaobo.uml.policies.UmlComponentEditPolicy;

public abstract class ConnectionPart extends AbstractConnectionEditPart
		implements PropertyChangeListener {

	@Override
	public void activate() {
		if (!isActive()) {
			super.activate();
			((ConnectionModel) getModel()).addPropertyChangeListener(this);
		}
	}

	@Override
	public void deactivate() {
		if (isActive()) {
			((ConnectionModel) getModel()).removePropertyChangeListener(this);
			super.deactivate();
		}
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new UmlComponentEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
				new ConnectionSelectEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE,
				new ConnectionBendpointEditPolicy());
	}

	public void propertyChange(PropertyChangeEvent evt) {
		refreshVisuals();
	}

	private void refreshBendpoints() {
		List bendpoints = ((ConnectionModel) getModel()).getBendpoints();
		List<AbsoluteBendpoint> constraint = new ArrayList<AbsoluteBendpoint>();
		for (int i = 0; i < bendpoints.size(); i++) {
			constraint.add(new AbsoluteBendpoint((Point) bendpoints.get(i)));
		}
		getConnectionFigure().setRoutingConstraint(constraint);
	}

	@Override
	protected void refreshVisuals() {
		refreshBendpoints();
	}
}
