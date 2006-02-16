package org.xiaobo.uml.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.xiaobo.uml.model.UmlMember;

public class UmlMemberFigure extends Label {
	private UmlMember model;

	private boolean selected;

	private boolean hasFocus;

	public UmlMemberFigure(UmlMember model) {
		super();
		this.model = model;
		model.setName(model.getName());

		FontData fd = new FontData();

		fd.setHeight(8);
		fd.setName("Arial");
		fd.setStyle(SWT.BOLD);
		// this.setIcon(ImageProvider.COLUMN_ICON.createImage());
		this.setLabelAlignment(PositionConstants.LEFT);
		this.setFont(new Font(null, fd));
	}

	protected void paintFigure(Graphics graphics) {
		this.setText(model.getName());
		if (selected) {
			graphics.pushState();
			graphics.setBackgroundColor(ColorConstants.menuBackgroundSelected);
			graphics.fillRectangle(getSelectionRectangle());
			graphics.popState();
			graphics.setForegroundColor(ColorConstants.white);
		}
		if (hasFocus) {
			graphics.pushState();
			graphics.setXORMode(true);
			graphics.setForegroundColor(ColorConstants.menuBackgroundSelected);
			graphics.setBackgroundColor(ColorConstants.white);
			graphics.drawFocus(getSelectionRectangle().resize(-1, -1));
			graphics.popState();
		}
		super.paintFigure(graphics);
	}

	private Rectangle getSelectionRectangle() {
		Rectangle bounds = getTextBounds();
		bounds.expand(new Insets(2, 2, 0, 0));
		translateToParent(bounds);
		bounds.intersect(getBounds());
		return bounds;
	}

	public void setSelected(boolean b) {
		selected = b;
		repaint();
	}

	public void setFocus(boolean b) {
		hasFocus = b;
		repaint();
	}
}
