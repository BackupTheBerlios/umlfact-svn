package com.xiaobo.uml.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public abstract class ConnectionModel extends UmlElement implements
		IUmlConnection {

	public static final String P_BEND_POINT = "bend_point";

	private List bendpoints = new ArrayList();

	private Type source;

	private Type target;

	/**
	 * in order to store the no argument constructor from super. because there's
	 * also the constructor with argus.
	 */
	public ConnectionModel() {
	}

	// public ConnectionModel(TypeModel source, TypeModel target) {
	// setSource(source);
	// setTarget(target);
	// }

	public IUmlConnectionNode getSource() {
		return source;
	}

	public void setSource(IUmlConnectionNode source) {
		if (this.source != null) {
			this.source.removeOut(this);
		}
		this.source = (Type) source;
		if (this.source != null) {
			this.source.addOut(this);
		}
	}

	public IUmlConnectionNode getTarget() {
		return target;
	}

	public void setTarget(IUmlConnectionNode target) {
		if (this.target != null) {
			this.target.removeIn(this);
		}
		this.target = (Type) target;
		if (this.target != null) {
			this.target.addIn(this);
		}
	}

	public List getBendpoints() {
		return bendpoints;
	}

	public void addBendpoints(int index, Point point) {
		bendpoints.add(index, point);
		firePropertyChange(P_BEND_POINT);
	}

	public void removeBendpoints(int index) {
		bendpoints.remove(index);
		firePropertyChange(P_BEND_POINT);
	}

	public void replaceBendpoints(int index, Point point) {
		bendpoints.set(index, point);
		firePropertyChange(P_BEND_POINT);
	}

}
