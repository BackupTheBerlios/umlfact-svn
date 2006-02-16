package org.xiaobo.uml.model;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlConnection extends UmlElement {
	private UmlElement source;

	private UmlElement target;

	public UmlConnection(UmlElement source, UmlElement target) {
		this.source = source;
		this.target = target;
		((UmlMember) source).addOut(this);
		((UmlMember) target).addIncome(this);
	}

	public UmlElement getSource() {
		return source;
	}

	public void setSource(UmlElement source) {
		this.source = source;
	}

	public UmlElement getTarget() {
		return target;
	}

	public void setTarget(UmlElement target) {
		this.target = target;
	}
}
