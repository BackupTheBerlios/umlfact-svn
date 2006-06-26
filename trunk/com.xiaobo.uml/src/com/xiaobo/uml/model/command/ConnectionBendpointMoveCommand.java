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
public class ConnectionBendpointMoveCommand extends Command {

	private IUmlConnection connection;

	private Point oldLocation, newLocation;

	private int index;

	public void execute() {
		oldLocation = (Point) connection.getBendpoints().get(index);
		connection.replaceBendpoints(index, newLocation);
	}

	public void setConnection(Object model) {
		this.connection = (IUmlConnection) model;
	}

	public void setIndex(int i) {
		this.index = i;
	}

	public void setNewLocation(Point newLoc) {
		this.newLocation = newLoc;
	}

	public void undo() {
		connection.replaceBendpoints(index, oldLocation);
	}
}
