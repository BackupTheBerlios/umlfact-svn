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
public class ConnectionBendpointCreateCommand extends Command {

	private IUmlConnection connection;

	private Point location;

	private int index;

	public void execute() {
		connection.addBendpoint(index, location);
	}

	public void setConnection(Object object) {
		this.connection = (IUmlConnection) object;
	}

	public void setIndex(int i) {
		this.index = i;
	}

	public void setLocation(Point loc) {
		this.location = loc;
	}

	public void undo() {
		connection.removeBendpoint(index);
	}
}
