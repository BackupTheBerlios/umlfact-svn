package com.xiaobo.uml.model;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class ConnectionModel extends UmlElement implements IUmlConnection {

	private TypeModel source;

	private TypeModel target;

	/**
	 * in order to store the no argument constructor from super.
	 */
	public ConnectionModel() {
	}

//	public ConnectionModel(TypeModel source, TypeModel target) {
//		setSource(source);
//		setTarget(target);
//	}

	public IUmlConnectionNode getSource() {
		return source;
	}

	public void setSource(IUmlConnectionNode source) {
		if (this.source != null) {
			this.source.removeOut(this);
		}
		this.source = (TypeModel) source;
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
		this.target = (TypeModel) target;
		if (this.target != null) {
			this.target.addIn(this);
		}
	}

}
