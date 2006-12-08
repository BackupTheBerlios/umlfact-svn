package com.xiaobo.uml.model.command;

import org.eclipse.draw2d.geometry.Rectangle;

import com.xiaobo.uml.model.IPositionableElement;
import com.xiaobo.uml.model.IUmlContainer;

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

	@Override
	public void execute() {
		super.execute();
		((IPositionableElement) child).setLocation(bounds.getLocation());
	}
}
