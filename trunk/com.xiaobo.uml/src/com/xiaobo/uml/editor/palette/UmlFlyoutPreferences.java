package com.xiaobo.uml.editor.palette;

import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.jface.preference.IPreferenceStore;

import com.xiaobo.uml.UmlPlugin;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlFlyoutPreferences implements FlyoutPreferences {

	private static final String PALETTE_DOCK_LOCATION = "UmlEditor.Location";

	private static final String PALETTE_SIZE = "UmlEditor.Size";

	private static final String PALETTE_STATE = "UmlEditor.State";

	private IPreferenceStore getPreferenceStore() {
		return UmlPlugin.getDefault().getPreferenceStore();
	}

	public int getDockLocation() {
		return getPreferenceStore().getInt(PALETTE_DOCK_LOCATION);
	}

	public int getPaletteState() {
		return getPreferenceStore().getInt(PALETTE_STATE);
	}

	public int getPaletteWidth() {
		return getPreferenceStore().getInt(PALETTE_SIZE);
	}

	public void setDockLocation(int location) {
		getPreferenceStore().setValue(PALETTE_DOCK_LOCATION, location);
	}

	public void setPaletteState(int state) {
		getPreferenceStore().setValue(PALETTE_STATE, state);
	}

	public void setPaletteWidth(int width) {
		getPreferenceStore().setValue(PALETTE_SIZE, width);
	}
}
