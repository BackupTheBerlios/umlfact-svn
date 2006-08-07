package com.xiaobo.uml.model.command;

import org.eclipse.gef.commands.Command;

import com.xiaobo.uml.model.IUmlContainer;
import com.xiaobo.uml.model.IUmlElement;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */

public class UmlElementDeleteCommand extends Command {

	private IUmlElement child;

	private IUmlContainer container;

	private int index;

	public UmlElementDeleteCommand(IUmlElement component,
			IUmlContainer container) {
		this.child = component;
		this.container = container;
	}

	public void execute() {
		index = container.getChildren().indexOf(child);
		container.removeChild(child);
	}

	public void undo() {
		container.addChild(child, index);
	}
}
