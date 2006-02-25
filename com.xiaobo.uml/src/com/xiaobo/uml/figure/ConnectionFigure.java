package com.xiaobo.uml.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.swt.SWT;

public class ConnectionFigure extends PolylineConnection {

    public void paintFigure(Graphics graphics) {
        graphics.setAntialias(SWT.DEFAULT);
        super.paintFigure(graphics);
    }
}
