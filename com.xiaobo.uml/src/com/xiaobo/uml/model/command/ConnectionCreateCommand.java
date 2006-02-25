package com.xiaobo.uml.model.command;

import org.eclipse.gef.commands.Command;

import com.xiaobo.uml.model.IUmlConnection;
import com.xiaobo.uml.model.IUmlConnectionNode;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */

public class ConnectionCreateCommand extends Command {

	protected IUmlConnectionNode source;

	protected IUmlConnectionNode target;

	protected IUmlConnection connection;

	public ConnectionCreateCommand(IUmlConnection connection) {
		this.connection = connection;
	}

	public void execute() {
		connection.setSource(source);
		connection.setTarget(target);
	}

	public void undo() {
		connection.setTarget(null);
		connection.setSource(null);
	}

	public void setSource(IUmlConnectionNode source) {
		this.source = source;
	}

	public void setTarget(IUmlConnectionNode target) {
		this.target = target;
	}

	public IUmlConnectionNode getSource() {
		return source;
	}

	public IUmlConnectionNode getTarget() {
		return target;
	}
}
