package org.xiaobo.uml.command;

import org.eclipse.gef.commands.Command;
import org.xiaobo.uml.model.UmlMember;
import org.xiaobo.uml.model.UmlConnection;
/**
 * 
 * @author Xiaobo Sun
 *
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class CreateUmlConnectionCommand extends Command {

	private UmlMember source;

	private UmlMember target;

	public void execute() {
		new UmlConnection(source, target);
	}

	public UmlMember getSource() {
		return source;
	}

	public void setSource(UmlMember source) {
		this.source = source;
	}

	public UmlMember getTarget() {
		return target;
	}

	public void setTarget(UmlMember targe) {
		this.target = targe;
	}
}
