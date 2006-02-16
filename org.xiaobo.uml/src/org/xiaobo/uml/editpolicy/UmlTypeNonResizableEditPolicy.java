package org.xiaobo.uml.editpolicy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.xiaobo.uml.command.UmlTypeMoveCommand;
import org.xiaobo.uml.model.UmlType;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlTypeNonResizableEditPolicy extends NonResizableEditPolicy {

	protected Command getMoveCommand(ChangeBoundsRequest request) {
		UmlTypeMoveCommand command = new UmlTypeMoveCommand();
		command.setModel((UmlType) getHost().getModel());
		command.setRequest(request);
		return command;
	}
}
