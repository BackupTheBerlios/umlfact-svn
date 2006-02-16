package org.xiaobo.uml.model;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.xiaobo.uml.editor.PointPropertySource;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlType extends UmlElement implements IPropertySource {

	public static final String ID_NAME = "__id__name";

	public static final String ID_LOCATION = "__id__point";

	protected Point location = new Point(0, 0);

	protected String name = "Type";

	protected IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] {
			new TextPropertyDescriptor(ID_NAME, "Name"),
			new PropertyDescriptor(ID_LOCATION, "Location") };

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		Point old = this.location;
		this.location = location;
		this.fireFigurePropertyChange(old, this.location);
	}

	public String getName() {
		return name;
	}

	public void setName(String tableName) {
		String old = this.name;
		this.name = tableName;
		this.fireFigurePropertyChange(old, this.name);
	}

	public Object getEditableValue() {
		return null;
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		return this.descriptors;
	}

	public Object getPropertyValue(Object id) {
		if (id.equals(ID_NAME))
			return this.getName();
		if (id.equals(ID_LOCATION))
			return new PointPropertySource(this.getLocation());
		return null;
	}

	public boolean isPropertySet(Object id) {
		return true;
	}

	public void resetPropertyValue(Object id) {
	}

	public void setPropertyValue(Object id, Object value) {
		if (id.equals(ID_NAME))
			setName((String) value);
		if (id.equals(ID_LOCATION))
			this.setLocation((Point) value);
	}
}
