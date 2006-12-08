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
public class ConnectionSourceReconnectCommand extends Command {

	private IUmlConnectionNode newSource;

	private IUmlConnectionNode oldSource;

	private IUmlConnection connection;

	public ConnectionSourceReconnectCommand(IUmlConnection connection,
			IUmlConnectionNode newSource) {
		this.connection = connection;
		this.newSource = newSource;
	}

	@Override
	public void execute() {
		oldSource = connection.getSource();
		connection.setSource(newSource);
	}

	@Override
	public void undo() {
		connection.setSource(oldSource);
	}
}