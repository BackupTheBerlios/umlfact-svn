package org.xiaobo.uml.editpart;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.xiaobo.uml.model.UmlMember;
import org.xiaobo.uml.model.UmlConnection;
import org.xiaobo.uml.model.UmlModel;
import org.xiaobo.uml.model.UmlType;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlEditPartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof UmlModel) {
			part = new UmlModelEditPart();
		}
		if (model instanceof UmlType) {
			part = new UmlTypeEditPart();
		}
		if (model instanceof UmlMember) {
			part = new UmlMemberEditPart();
		}
		if (model instanceof UmlConnection)
			part = new UmlConnectionEditPart();
		if (part != null)
			part.setModel(model);
		return part;
	}

}
