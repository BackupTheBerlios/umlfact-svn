package org.xiaobo.uml.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlMember extends UmlElement implements IPropertySource {
	public static final String ID_NAME = "__id__columnname";

	public static final String ID_TYPE = "__id__type";

	public static final String ID_NULLABLE = "__id__type";

	public static final String ID_ISPK = "__id__ispk";

	protected List outs = new ArrayList();

	protected List incomes = new ArrayList();

	protected String name = "member";

	protected boolean nullable = true;

	protected String columnType = "VRCHARS";

	protected int columnSize = 0;

	protected IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] { new TextPropertyDescriptor(
			ID_NAME, "Name") };

	public String getName() {
		return name;
	}

	public void setName(String columnName) {
		this.name = columnName;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public Object getEditableValue() {
		return null;
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}

	public Object getPropertyValue(Object id) {
		if (id.equals(ID_NAME))
			return this.getName();
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
	}

	public void fireConnectionChange(String type) {
		support.firePropertyChange(type, new Object(), null);
	}

	public List getOuts() {
		return outs;
	}

	public void setOuts(List outs) {
		this.outs = outs;
	}

	public List getIncomes() {
		return incomes;
	}

	public void setIncomes(List incomes) {
		this.incomes = incomes;
	}

	public void addOut(UmlConnection out) {
		getOuts().add(out);
		this.fireConnectionChange(PRO_CONNECTION_OUT);
	}

	public void addIncome(UmlConnection in) {
		getIncomes().add(in);
		this.fireConnectionChange(PRO_CONNECTION_IN);
	}
}
