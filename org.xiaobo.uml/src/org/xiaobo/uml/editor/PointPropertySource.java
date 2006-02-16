package org.xiaobo.uml.editor;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class PointPropertySource implements IPropertySource {

	public static final String ID_X = "id x";

	public static final String ID_Y = "id y";

	public static IPropertyDescriptor[] descriptor;

	static {
		TextPropertyDescriptor t1 = new TextPropertyDescriptor(ID_X, "X");
		t1.setValidator(NumberCellEditorValidator.INSTANCE());
		TextPropertyDescriptor t2 = new TextPropertyDescriptor(ID_Y, "Y");
		t2.setValidator(NumberCellEditorValidator.INSTANCE());
		t2.setLabelProvider(TextPropertyLabelProvider.INSTANCE());
		t1.setLabelProvider(TextPropertyLabelProvider.INSTANCE());
		descriptor = new IPropertyDescriptor[] { t1, t2 };
	}

	private Point point = null;

	public PointPropertySource(Point point) {
		this.point = point.getCopy();
	}

	public Object getEditableValue() {
		return point;
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptor;
	}

	public Object getPropertyValue(Object id) {
		System.out.println();
		if (id.equals(ID_X)) {
			return String.valueOf(point.x);
		}
		if (id.equals(ID_Y)) {
			return String.valueOf(point.y);
		}
		return null;
	}

	public boolean isPropertySet(Object id) {
		// TODO Auto-generated method stub
		return true;
	}

	public void resetPropertyValue(Object id) {
		// TODO Auto-generated method stub
		System.out.println("Reset");

	}

	public void setPropertyValue(Object id, Object value) {
		System.out.println();
		if (id.equals(ID_X)) {
			point.x = Integer.parseInt((String) value);
		}
		if (id.equals(ID_Y)) {
			point.y = Integer.parseInt((String) value);
		}
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
}
