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
public class ConnectionDeleteCommand extends Command {
	private IUmlConnection connection;

	private IUmlConnectionNode lastSource;

	private IUmlConnectionNode lastTarget;

	public ConnectionDeleteCommand(IUmlConnection con) {
		this.connection = con;
	}

	public void execute() {
		lastSource = connection.getSource();
		lastTarget = connection.getTarget();
		connection.setSource(null);
		connection.setTarget(null);
	}

	public void undo() {
		connection.setTarget(lastTarget);
		connection.setSource(lastSource);
	}

}
