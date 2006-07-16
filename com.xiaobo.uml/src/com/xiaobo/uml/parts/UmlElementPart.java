package com.xiaobo.uml.parts;

import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.xiaobo.uml.model.UmlElement;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public abstract class UmlElementPart extends AbstractGraphicalEditPart
		implements PropertyChangeListener {

	public void activate() {
		if (!isActive()) {
			super.activate();
			((UmlElement) getModel()).addPropertyChangeListener(this);
		}
		refreshChildren();
	}

	public void deactivate() {
		if (isActive()) {
			((UmlElement) getModel()).removePropertyChangeListener(this);
			super.deactivate();
		}
	}
}
