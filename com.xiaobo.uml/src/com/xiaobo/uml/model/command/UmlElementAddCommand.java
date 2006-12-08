package com.xiaobo.uml.model.command;

import org.eclipse.gef.commands.Command;

import com.xiaobo.uml.model.IUmlContainer;
import com.xiaobo.uml.model.IUmlElement;
import com.xiaobo.uml.model.UmlElement;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlElementAddCommand extends Command {

	protected IUmlElement child;

	protected UmlElement after;

	protected IUmlContainer newContainer;

	protected IUmlContainer oldContainer;

	protected int startIndex;

	protected int destIndex;

	public UmlElementAddCommand(IUmlElement child, IUmlContainer newContainer,
			IUmlContainer oldContainer, UmlElement after) {
		this.child = child;
		this.newContainer = newContainer;
		this.oldContainer = oldContainer;
		this.after = after;
	}

	@Override
	public void execute() {
		startIndex = oldContainer.getChildren().indexOf(child);
		oldContainer.removeChild(child);
		if (after != null) {
			destIndex = newContainer.getChildren().indexOf(after);
			newContainer.addChild(child, destIndex);
		} else {
			newContainer.addChild(child);
		}
	}

	@Override
	public void undo() {
		newContainer.removeChild(child);
		oldContainer.addChild(child, startIndex);
	}
}
