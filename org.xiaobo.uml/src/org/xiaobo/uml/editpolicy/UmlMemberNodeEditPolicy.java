package org.xiaobo.uml.editpolicy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.xiaobo.uml.command.CreateUmlConnectionCommand;
import org.xiaobo.uml.model.UmlMember;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlMemberNodeEditPolicy extends GraphicalNodeEditPolicy {

	protected Command getConnectionCompleteCommand(
			CreateConnectionRequest request) {
		CreateUmlConnectionCommand command = (CreateUmlConnectionCommand) request
				.getStartCommand();
		if (command == null)
			return null;
		command.setTarget((UmlMember) getHost().getModel());

		return command;
	}

	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		CreateUmlConnectionCommand command = new CreateUmlConnectionCommand();
		command.setSource((UmlMember) getHost().getModel());
		request.setStartCommand(command);
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
