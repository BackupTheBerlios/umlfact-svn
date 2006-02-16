package org.xiaobo.uml.command;

import org.eclipse.gef.commands.Command;
import org.xiaobo.uml.model.UmlElement;

public class UmlElementCreateCommand extends Command {
	private UmlElement parent;

	private UmlElement child;

	private int index = -1;

	public void execute() {
		if (parent == null || child == null)
			throw new NullPointerException();
		else
			parent.addChild(index, child);
	}

	public void redo() {
		execute();
	}

	public void undo() {
		if (parent == null || child == null)
			throw new NullPointerException();
		else
			parent.removeChild(child);
	}

	public UmlElement getParent() {
		return parent;
	}

	public void setParent(UmlElement parent) {
		this.parent = parent;
	}

	public UmlElement getChild() {
		return child;
	}

	public void setChild(UmlElement child) {
		this.child = child;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
