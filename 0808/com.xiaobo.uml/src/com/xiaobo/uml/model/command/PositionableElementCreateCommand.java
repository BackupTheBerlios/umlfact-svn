package com.xiaobo.uml.model.command;

import org.eclipse.draw2d.geometry.Rectangle;

import com.xiaobo.uml.model.INamedElement;
import com.xiaobo.uml.model.IPositionableElement;
import com.xiaobo.uml.model.IUmlContainer;
import com.xiaobo.uml.model.validation.NameValidator;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */

public class PositionableElementCreateCommand extends UmlElementCreateCommand {

	private Rectangle bounds;

	public PositionableElementCreateCommand(
			IPositionableElement positionableElement, IUmlContainer container,
			Rectangle bounds) {
		super(positionableElement, container);
		this.bounds = bounds;
	}

	public void execute() {
		super.execute();
		((IPositionableElement) child).setLocation(bounds.getLocation());
		((INamedElement) child).setNameValidator(new NameValidator(
				(INamedElement) child, parent));
	}
}
