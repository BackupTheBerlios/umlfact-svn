package com.xiaobo.uml.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.swt.graphics.Image;

import com.xiaobo.uml.model.INamedElement;
import com.xiaobo.uml.model.IUmlContainer;
import com.xiaobo.uml.model.UmlElement;
import com.xiaobo.uml.viewers.UmlElementLabelProvider;

/**
 * 
 * @author Xiaobo Sun. Created on 19.06.2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class UmlElementTreePart extends AbstractTreeEditPart implements
		PropertyChangeListener {

	public void activate() {
		if (!isActive()) {
			super.activate();
			((UmlElement) getModel()).addPropertyChangeListener(this);
		}
	}

	public void deactivate() {
		if (isActive()) {
			((UmlElement) getModel()).removePropertyChangeListener(this);
			super.deactivate();
		}
	}

	public void propertyChange(PropertyChangeEvent event) {
		refreshVisuals();
		if (getModel() instanceof IUmlContainer) {
			refreshChildren();
		}
	}

	protected List getModelChildren() {
		Object model = getModel();
		if (model instanceof IUmlContainer) {
			return ((IUmlContainer) model).getChildren();
		}
		return Collections.EMPTY_LIST;
	}

	protected Image getImage() {
		if (getModel() instanceof INamedElement) {
			return new UmlElementLabelProvider().getImage(getModel());
		}
		return null;
	}

	protected String getText() {
		if (getModel() instanceof INamedElement) {
			return ((INamedElement) getModel()).getName();
		}
		return null;
	}
}
