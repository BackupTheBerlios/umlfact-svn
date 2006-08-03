package com.xiaobo.uml.policies;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class LabelCellEditorLocator implements CellEditorLocator {

	private Label m_label;

	public LabelCellEditorLocator(Label _label) {
		m_label = _label;
	}

	public void relocate(CellEditor celleditor) {
		Text text = (Text) celleditor.getControl();
		org.eclipse.swt.graphics.Point pref = text.computeSize(SWT.DEFAULT,
				SWT.DEFAULT);
		Rectangle rect = m_label.getTextBounds().getCopy();
		m_label.translateToAbsolute(rect);
		text.setBounds(rect.x - 1, rect.y - 1, pref.x + 1, pref.y + 1);
	}
}
