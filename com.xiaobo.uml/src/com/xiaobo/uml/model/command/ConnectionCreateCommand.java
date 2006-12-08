package com.xiaobo.uml.model.command;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.xiaobo.uml.model.IUmlConnection;
import com.xiaobo.uml.model.IUmlConnectionNode;
import com.xiaobo.uml.model.Type;

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

	private Point sourceCenterPoint;

	public Point getSourceCenterPoint() {
		return sourceCenterPoint;
	}

	public void setSourceCenterPoint(Point sourceCenterPoint) {
		this.sourceCenterPoint = sourceCenterPoint;
	}

	public ConnectionCreateCommand(IUmlConnection connection) {
		this.connection = connection;
	}

	@Override
	public void execute() {
		connection.setSource(source);
		connection.setTarget(target);
		if (source.equals(target)) {
			addBendPoints();
		}
	}

	@Override
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

	public void addBendPoints() {
		Point point = ((Type) source).getLocation();
		Point cbp0 = new Point(point.x, point.y - 30);
		Point cbp1 = new Point(point.x + 100, point.y - 30);
		connection.addBendpoint(0, cbp0);
		connection.addBendpoint(1, cbp1);
	}
}
