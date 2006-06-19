package com.xiaobo.uml.viewers;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.xiaobo.uml.IIconConstants;
import com.xiaobo.uml.UmlPlugin;
import com.xiaobo.uml.model.AttributeModel;
import com.xiaobo.uml.model.CompartmentModel;
import com.xiaobo.uml.model.MethodModel;
import com.xiaobo.uml.model.TypeModel;

/**
 * 
 * @author Xiaobo Sun. Created on 19.06.2006.
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class UmlElementLabelProvider extends LabelProvider {

	public Image getImage(Object element) {
		if (element instanceof TypeModel) {
			return UmlPlugin.getImage(IIconConstants.TYPE_ICON);
		} else if (element instanceof CompartmentModel) {
			return UmlPlugin.getImage(IIconConstants.COMPARTMENT_ICON);
		} else if (element instanceof AttributeModel) {
			return UmlPlugin.getImage(IIconConstants.ATTRIBUTE_ICON);
		} else if (element instanceof MethodModel) {
			return UmlPlugin.getImage(IIconConstants.METHOD_ICON);
		}
		return super.getImage(element);
	}
}
