package org.xiaobo.uml.editpolicy;

import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.xiaobo.uml.editpart.UmlMemberEditPart;
import org.xiaobo.uml.figure.UmlMemberFigure;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright ? 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlMemberSelectionEditPolicy extends NonResizableEditPolicy {

	private UmlMemberFigure getLabel() {
		UmlMemberEditPart part = (UmlMemberEditPart) getHost();
		return ((UmlMemberFigure) part.getFigure());
	}

	protected void hideFocus() {
		getLabel().setFocus(false);
	}

	protected void hideSelection() {
		getLabel().setSelected(false);
		getLabel().setFocus(false);

	}

	protected void showFocus() {
		getLabel().setFocus(true);
	}

	protected void showPrimarySelection() {
		getLabel().setSelected(true);
		getLabel().setFocus(true);
	}

	protected void showSelection() {
		getLabel().setSelected(true);
		getLabel().setFocus(false);
	}
}
