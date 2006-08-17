package com.xiaobo.uml.model.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.xiaobo.uml.model.IUmlConnection;
import com.xiaobo.uml.model.IUmlConnectionNode;

/**
 * 
 * @author Xiaobo Sun. Created on 19.07.2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All rights reserved.
 */
class OldConnection {

	private IUmlConnectionNode source;

	private IUmlConnectionNode target;

	private IUmlConnection connection;

	private OldConnection(IUmlConnection connection, IUmlConnectionNode source,
			IUmlConnectionNode target) {
		this.connection = connection;
		this.source = source;
		this.target = target;
	}

	public IUmlConnection getConnection() {
		return connection;
	}

	public IUmlConnectionNode getSource() {
		return source;
	}

	public IUmlConnectionNode getTarget() {
		return target;
	}

	public static List createOldConnections(
			IUmlConnectionNode connectionContainer) {
		List ins = connectionContainer.getIns();
		List outs = connectionContainer.getOuts();
		List connections = new ArrayList();
		for (Iterator i = ins.iterator(); i.hasNext();) {
			IUmlConnection connection = (IUmlConnection) i.next();
			connections.add(new OldConnection(connection, connection
					.getSource(), connection.getTarget()));
		}
		for (Iterator i = outs.iterator(); i.hasNext();) {
			IUmlConnection connection = (IUmlConnection) i.next();
			connections.add(new OldConnection(connection, connection
					.getSource(), connection.getTarget()));
		}
		for (Iterator i = connections.iterator(); i.hasNext();) {
			OldConnection oldConnection = (OldConnection) i.next();
			IUmlConnection connection = oldConnection.getConnection();
			connection.setSource(null);
			connection.setTarget(null);
		}
		return connections;
	}

	public static void restoreOldConnections(List connections) {
		for (Iterator i = connections.iterator(); i.hasNext();) {
			OldConnection oldConnection = (OldConnection) i.next();
			IUmlConnection connection = oldConnection.getConnection();
			connection.setTarget(oldConnection.getTarget());
			connection.setSource(oldConnection.getSource());
		}
	}
}