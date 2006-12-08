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

public class UmlElementCreateCommand extends Command {

	protected IUmlContainer parent;

	protected IUmlElement child;

	protected int index;

	public UmlElementCreateCommand(IUmlElement child, IUmlContainer parent,
			int index) {
		this(child, parent);
		this.index = index;
	}

	public UmlElementCreateCommand(IUmlElement child, IUmlContainer parent) {
		this.child = child;
		this.parent = parent;
		this.index = -1;
	}

	@Override
	public void execute() {
		setChildName();
		if (index < 0) {
			parent.addChild(child);
		} else {
			parent.addChild(child, index);
		}
	}

	private void setChildName() {
		// TODO name counter and name validator
	}

	@Override
	public void undo() {
		parent.removeChild(child);
	}
}
