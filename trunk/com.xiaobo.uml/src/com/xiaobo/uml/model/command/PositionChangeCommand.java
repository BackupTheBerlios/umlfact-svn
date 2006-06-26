package com.xiaobo.uml.model.command;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.xiaobo.uml.model.IPositionableElement;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */

public class PositionChangeCommand extends Command {

	private IPositionableElement component;

	private Rectangle newBounds;

	private Rectangle oldBounds;

	public PositionChangeCommand(IPositionableElement component,
			Rectangle bounds) {
		this.component = component;
		this.newBounds = bounds;
	}

	public void execute() {
		oldBounds = new Rectangle(component.getLocation(), new Dimension(0, 0));
		component.setLocation(newBounds.getLocation());
	}

	public void undo() {
		component.setLocation(oldBounds.getLocation());
	}
}
