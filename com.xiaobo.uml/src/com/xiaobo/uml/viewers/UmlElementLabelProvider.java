package com.xiaobo.uml.viewers;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.xiaobo.uml.IIconConstants;
import com.xiaobo.uml.UmlPlugin;
import com.xiaobo.uml.model.Attribute;
import com.xiaobo.uml.model.Compartment;
import com.xiaobo.uml.model.Method;
import com.xiaobo.uml.model.Type;

/**
 * 
 * @author Xiaobo Sun. Created on 19.06.2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class UmlElementLabelProvider extends LabelProvider {

	public Image getImage(Object element) {
		if (element instanceof Type) {
			return UmlPlugin.getImage(IIconConstants.TYPE_ICON);
		} else if (element instanceof Compartment) {
			return UmlPlugin.getImage(IIconConstants.COMPARTMENT_ICON);
		} else if (element instanceof Attribute) {
			return UmlPlugin.getImage(IIconConstants.ATTRIBUTE_ICON);
		} else if (element instanceof Method) {
			return UmlPlugin.getImage(IIconConstants.METHOD_ICON);
		}
		return super.getImage(element);
	}
}
