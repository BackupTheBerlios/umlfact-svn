package org.xiaobo.uml.editor;

import org.eclipse.jface.viewers.ICellEditorValidator;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class NumberCellEditorValidator implements ICellEditorValidator {

	private static NumberCellEditorValidator instance = new NumberCellEditorValidator();

	private NumberCellEditorValidator() {
	}

	public static NumberCellEditorValidator INSTANCE() {
		return instance;
	}

	public String isValid(Object value) {
		try {
			new Integer((String) value);
			return null;
		} catch (Exception e) {
			return "Must Be munber";
		}
	}

}
