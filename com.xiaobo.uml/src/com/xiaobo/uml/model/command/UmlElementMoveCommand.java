package com.xiaobo.uml.model.command;

import org.eclipse.gef.commands.Command;

import com.xiaobo.uml.model.IUmlContainer;
import com.xiaobo.uml.model.UmlElement;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */

public class UmlElementMoveCommand extends Command {

	private IUmlContainer parent;

	private UmlElement child;

	private UmlElement after;

	private int startIndex;

	private int destIndex;

	public UmlElementMoveCommand(UmlElement child, IUmlContainer parent,
			UmlElement after) {
		this.child = child;
		this.parent = parent;
		this.after = after;
	}

	public void execute() {
		startIndex = parent.getChildren().indexOf(child);
		parent.removeChild(child);
		if (after != null) {
			destIndex = parent.getChildren().indexOf(after);
			parent.addChild(child, destIndex);
		} else {
			parent.addChild(child);
		}
	}

	public void undo() {
		parent.removeChild(child);
		parent.addChild(child, startIndex);
	}
}