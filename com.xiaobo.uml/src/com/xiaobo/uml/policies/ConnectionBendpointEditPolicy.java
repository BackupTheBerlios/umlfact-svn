package com.xiaobo.uml.policies;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;

import com.xiaobo.uml.model.command.ConnectionBendpointCreateCommand;
import com.xiaobo.uml.model.command.ConnectionBendpointDeleteCommand;
import com.xiaobo.uml.model.command.ConnectionBendpointMoveCommand;

/**
 * 
 * @author xiaobo. Created on Jun 15, 2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All rights reserved.
 * 
 */
public class ConnectionBendpointEditPolicy extends BendpointEditPolicy {

	@Override
	protected Command getCreateBendpointCommand(BendpointRequest request) {
		Point point = request.getLocation();
		getConnection().translateToRelative(point);
		ConnectionBendpointCreateCommand command = new ConnectionBendpointCreateCommand();
		command.setLocation(point);
		command.setConnection(getHost().getModel());
		command.setIndex(request.getIndex());
		return command;
	}

	/**
	 * TODO: how to use ConnectionBendpointDeleteCommand?
	 */
	@Override
	protected Command getDeleteBendpointCommand(BendpointRequest request) {
		ConnectionBendpointDeleteCommand command = new ConnectionBendpointDeleteCommand();
		command.setConnection(getHost().getModel());
		command.setIndex(request.getIndex());
		return command;
	}

	@Override
	protected Command getMoveBendpointCommand(BendpointRequest request) {
		Point location = request.getLocation();
		getConnection().translateToRelative(location);

		ConnectionBendpointMoveCommand command = new ConnectionBendpointMoveCommand();
		command.setConnection(getHost().getModel());
		command.setIndex(request.getIndex());
		command.setNewLocation(location);
		return command;
	}
}
