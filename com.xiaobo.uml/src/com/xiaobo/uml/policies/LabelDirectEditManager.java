package com.xiaobo.uml.policies;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Text;

import com.xiaobo.uml.figure.EditableLabel;
import com.xiaobo.uml.figure.ILabeledFigure;
import com.xiaobo.uml.parts.NamedElementPart;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */

public class LabelDirectEditManager extends DirectEditManager {

	NamedElementPart part;

	public LabelDirectEditManager(NamedElementPart source, Class editorType,
			LabelCellEditorLocator locator) {
		super(source, editorType, locator);
		part = source;
	}

	@Override
	protected void initCellEditor() {
		Text text = (Text) getCellEditor().getControl();
		// set the initial value of the
		ILabeledFigure figure = (ILabeledFigure) part.getFigure();
		EditableLabel label = figure.getLabel();
		String originalValue = label.getText();
		getCellEditor().setValue(originalValue);

		// calculate the font size of the underlying
		Font figureFont = figure.getFont();
		FontData data = figureFont.getFontData()[0];
		Dimension fontSize = new Dimension(0, data.getHeight());

		// set the font to be used
		label.translateToAbsolute(fontSize);
		data.setHeight(fontSize.height);
		figureFont = new Font(null, data);

		text.setFont(figureFont);
		text.selectAll();
	}
}
