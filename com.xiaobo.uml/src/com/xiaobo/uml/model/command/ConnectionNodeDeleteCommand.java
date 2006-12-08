package com.xiaobo.uml.model.command;

import java.util.List;

import org.eclipse.gef.commands.Command;

import com.xiaobo.uml.model.IUmlConnectionNode;
import com.xiaobo.uml.model.IUmlContainer;

/**
 * 
 * @author Xiaobo Sun. Created on 19.07.2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All rights reserved.
 */
public class ConnectionNodeDeleteCommand extends Command {

	private IUmlConnectionNode connectionContainer;

	private IUmlContainer container;

	private List connections;

	private int index;

	public ConnectionNodeDeleteCommand(IUmlConnectionNode connectionContainer,
			IUmlContainer container) {
		this.connectionContainer = connectionContainer;
		this.container = container;
	}

	@Override
	public void execute() {
		deleteAllConnections();
		index = container.getChildren().indexOf(connectionContainer);
		container.removeChild(connectionContainer);
	}

	private void deleteAllConnections() {
		connections = OldConnection.createOldConnections(connectionContainer);
	}

	@Override
	public void undo() {
		container.addChild(connectionContainer, index);
		OldConnection.restoreOldConnections(connections);
	}
}
