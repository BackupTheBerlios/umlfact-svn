package com.xiaobo.uml.model.command;

import org.eclipse.gef.commands.Command;

import com.xiaobo.uml.model.IUmlConnection;
import com.xiaobo.uml.model.IUmlConnectionNode;

/**
 * 
 * @author xiaobo. Created on Jun 15, 2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All rights reserved.
 * 
 */
public class ConnectionTargetReconnectCommand extends Command {

	protected IUmlConnectionNode newTarget;

	protected IUmlConnectionNode oldTarget;

	protected IUmlConnection connection;

	public ConnectionTargetReconnectCommand(IUmlConnection connection,
			IUmlConnectionNode newTarget) {
		this.connection = connection;
		this.newTarget = newTarget;
	}

	@Override
	public void execute() {
		oldTarget = connection.getTarget();
		connection.setTarget(newTarget);
	}

	@Override
	public void undo() {
		connection.setTarget(oldTarget);
	}
}
