package com.xiaobo.uml.model.command;

import org.eclipse.gef.commands.Command;

import com.xiaobo.uml.model.NamedElement;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class NameChangeCommand extends Command {

	private String name;

	private String oldName;

	private NamedElement element;

	public NameChangeCommand(NamedElement element, String name) {
		this.element = element;
		this.name = name;
	}

	@Override
	public void execute() {
		oldName = element.getName();
		element.setName(name);
	}

	@Override
	public void undo() {
		element.setName(oldName);
	}
}
