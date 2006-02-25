package com.xiaobo.uml.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.xiaobo.uml.model.ConnectionModel;
import com.xiaobo.uml.model.TypeModel;
import com.xiaobo.uml.model.command.ConnectionCreateCommand;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */

public class TypeNodeEditPolicy extends GraphicalNodeEditPolicy {

	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		ConnectionCreateCommand command = new ConnectionCreateCommand(
				(ConnectionModel) request.getNewObject());
		command.setSource((TypeModel) getHost().getModel());
		request.setStartCommand(command);
		return command;
	}

	protected Command getConnectionCompleteCommand(
			CreateConnectionRequest request) {
		ConnectionCreateCommand command = (ConnectionCreateCommand) request
				.getStartCommand();
		if (command == null) {
			return null;
		}
		if (request.getSourceEditPart().equals(request.getTargetEditPart())) {
			return null;
		}
		command.setTarget((TypeModel) getHost().getModel());
		return command;
	}

	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
