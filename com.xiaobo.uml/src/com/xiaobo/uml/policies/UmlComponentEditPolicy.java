package com.xiaobo.uml.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.xiaobo.uml.model.IUmlContainer;
import com.xiaobo.uml.model.IUmlElement;
import com.xiaobo.uml.model.command.UmlElementDeleteCommand;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */

public class UmlComponentEditPolicy extends ComponentEditPolicy {

	protected Command getDeleteCommand(GroupRequest request) {
		Object model = getHost().getModel();
		Object parent = getHost().getParent().getModel();

		// if (model instanceof IFarmConnection) {
		// return new ConnectionDeleteCommand((IFarmConnection) model);
		// }
		//
		// if (model instanceof IFarmConnectionContainer) {
		// return new ConnectionContainerDeleteCommand(
		// (IFarmConnectionContainer) model, (IFarmContainer) parent);
		// }

		if (model instanceof IUmlElement) {
			return new UmlElementDeleteCommand((IUmlElement) model,
					(IUmlContainer) parent);
		}

		return null;
	}
}
