package com.xiaobo.uml.model.command;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.xiaobo.uml.model.IUmlConnection;

/**
 * 
 * @author xiaobo. Created on Jun 15, 2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All rights reserved.
 * 
 */
public class ConnectionBendpointDeleteCommand extends Command {

	private IUmlConnection connection;

	private Point oldLocation;

	private int index;

	public void setConnection(Object model) {
		this.connection = (IUmlConnection) model;
	}

	public void setIndex(int i) {
		this.index = i;
	}

	public void undo() {
		connection.addBendpoints(index, oldLocation);
	}
}
