package com.xiaobo.uml.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.TextFlow;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class EditableLabel extends Label {

	private boolean selected;

	private TextFlow tooltipFigure;

	public EditableLabel() {
		FlowPage tooltip = new FlowPage();
		tooltip.setBorder(new MarginBorder(2));

		tooltipFigure = new TextFlow();
		tooltip.add(tooltipFigure);
		setToolTip(tooltip);
	}

	private Rectangle getSelectionRectangle() {
		Rectangle bounds = getTextBounds().getCopy();
		bounds.expand(new Insets(2, 2, 0, 0));
		translateToParent(bounds);
		bounds.intersect(getBounds());
		return bounds;
	}

	public void setText(String s) {
		super.setText(s);
	}

	public void setTooltipText(String s) {
		tooltipFigure.setText(s);
	}

	protected void paintFigure(Graphics graphics) {
		if (selected) {
			graphics.pushState();
			graphics.setBackgroundColor(ColorConstants.menuBackgroundSelected);
			graphics.fillRectangle(getSelectionRectangle());
			graphics.popState();
			graphics.setForegroundColor(ColorConstants.white);
		}
		super.paintFigure(graphics);
	}

	public void setSelected(boolean b) {
		selected = b;
		repaint();
	}
}
