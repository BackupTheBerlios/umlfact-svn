package org.xiaobo.uml.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.xiaobo.uml.graphics.util.GraphicsUtil;
import org.xiaobo.uml.model.UmlType;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlTypeFigure extends Figure {

	private UmlType model;

	Figure containerFigure = null;

	private Label tableNameLabel = null;

	public UmlTypeFigure(UmlType model) {
		super();
		this.model = model;

		tableNameLabel = new Label();
		tableNameLabel.setText(model.getName());
		FontData fd = new FontData();

		fd.setHeight(10);
		fd.setName("Arial");
		fd.setStyle(SWT.BOLD);

		tableNameLabel.setFont(new Font(null, fd));
		// tableNameLabel.setIcon(ImageProvider.TABLE_ICON.createImage());
		tableNameLabel.setLabelAlignment(PositionConstants.MIDDLE);

		containerFigure = new Figure() {
			protected void paintFigure(Graphics g) {
				super.paintFigure(g);
				// g.setBackgroundColor(ColorConstants.red);
				// g.fillRectangle(getBounds());
			}

			public Dimension getPreferredSize(int wHint, int hHint) {
				Dimension dimension = super.getPreferredSize(wHint, hHint);

				int w = Math.max(dimension.width, wHint);
				int h = Math.max(dimension.height, 30);

				return new Dimension(w, h);
			}
		};

		ToolbarLayout tableLayout = new ToolbarLayout();
		tableLayout.setSpacing(4);

		tableLayout.setVertical(true);

		this.setBorder(new MarginBorder(8, 8, 8, 8));

		this.setLayoutManager(tableLayout);
		ToolbarLayout containerLayout = new ToolbarLayout();
		containerLayout.setMinorAlignment(ToolbarLayout.ALIGN_BOTTOMRIGHT);
		containerFigure.setLayoutManager(containerLayout);
		this.add(tableNameLabel);
		this.add(containerFigure);
		this.setOpaque(true);
	}

	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		graphics.setForegroundColor(ColorConstants.gray);
		Point plusPoint = new Point(getLocation().x, getLocation().y);
		Dimension plusDimension = new Dimension(getBounds().getSize().width, 20);
		GraphicsUtil.paintPlusArea(graphics, plusPoint, plusDimension);
		Rectangle bounds = getBounds();

		graphics.drawRectangle(new Rectangle(bounds.x, bounds.y,
				bounds.width - 1, bounds.height - 1));

		int y = getBounds().y + getBorder().getInsets(this).bottom
				+ this.tableNameLabel.getSize().height;
		graphics.drawLine(getBounds().x, y, getBounds().x + getBounds().width,
				y);
	}

	public void paint(Graphics graphics) {
		this.tableNameLabel.setText(model.getName());
		super.paint(graphics);
	}

	public Dimension getPreferredSize(int wHint, int hHint) {
		return super.getPreferredSize(wHint, hHint);
	}

	public Figure getContainerFigure() {
		return containerFigure;
	}

	public void setContainerFigure(Figure containerFigure) {
		this.containerFigure = containerFigure;
	}
}