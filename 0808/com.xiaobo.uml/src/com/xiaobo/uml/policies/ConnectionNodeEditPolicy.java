package com.xiaobo.uml.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.xiaobo.uml.model.ConnectionModel;
import com.xiaobo.uml.model.Type;
import com.xiaobo.uml.model.command.ConnectionCreateCommand;
import com.xiaobo.uml.model.command.ConnectionSourceReconnectCommand;
import com.xiaobo.uml.model.command.ConnectionTargetReconnectCommand;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class ConnectionNodeEditPolicy extends GraphicalNodeEditPolicy {

	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		ConnectionCreateCommand command = new ConnectionCreateCommand(
				(ConnectionModel) request.getNewObject());
		command.setSource((Type) getHost().getModel());
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
		command.setTarget((Type) getHost().getModel());
		return command;
	}

	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		/**
		 * old target is equal to the new target
		 */
		if (request.getConnectionEditPart().getTarget().equals(
				request.getTarget())) {
			return null;
		}
		/**
		 * old and new target are in the same parent
		 */
		if (!request.getConnectionEditPart().getTarget().getParent().equals(
				request.getTarget().getParent())) {
			return null;
		}
		return new ConnectionTargetReconnectCommand((ConnectionModel) request
				.getConnectionEditPart().getModel(), (Type) request.getTarget()
				.getModel());
	}

	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		if (request.getConnectionEditPart().getTarget().equals(
				request.getTarget())) {
			return null;
		}
		if (!request.getConnectionEditPart().getSource().getParent().equals(
				request.getTarget().getParent())) {
			return null;
		}
		return new ConnectionSourceReconnectCommand((ConnectionModel) request
				.getConnectionEditPart().getModel(), (Type) request.getTarget()
				.getModel());
	}
}
