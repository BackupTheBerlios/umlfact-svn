package com.xiaobo.uml.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ToolbarLayout;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class LabeledFigure extends Figure implements ILabeledFigure {

	private static final int BORDER_SIZE = 5;

	private EditableLabel label;

	private IFigure containerFigure;

	public LabeledFigure() {
		IFigure borderFigure = new Figure();

		setLabel(new EditableLabel());
		setContainerFigure(new Figure());

		getLabel().setBorder(new MarginBorder(0, 0, BORDER_SIZE, 0));

		borderFigure.setBorder(new MarginBorder(BORDER_SIZE));
		borderFigure.setLayoutManager(new ToolbarLayout(false));
		borderFigure.add(getLabel());
		borderFigure.add(getContentPane());

		setLayoutManager(new ToolbarLayout(false));
		add(borderFigure);

		setBorder(new LineBorder(ColorConstants.black, 1));
		setForegroundColor(ColorConstants.black);
		setOpaque(true);
	}

	public IFigure getContentPane() {
		return containerFigure;
	}

	public EditableLabel getLabel() {
		return label;
	}

	private void setLabel(EditableLabel label) {
		this.label = label;
	}

	private void setContainerFigure(IFigure containerFigure) {
		this.containerFigure = containerFigure;
	}
}
