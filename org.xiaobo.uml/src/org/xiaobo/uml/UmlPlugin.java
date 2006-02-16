package org.xiaobo.uml;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.*;
import org.osgi.framework.BundleContext;

import java.util.*;

/**
 * 
 * @author Xiaobo Sun
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights Reserved.
 */
public class UmlPlugin extends AbstractUIPlugin {

	private static UmlPlugin plugin;

	private ResourceBundle resourceBundle;

	public static final String PLUGIN_ID = "org.xiaobo.uml";

	public UmlPlugin() {
		super();
		plugin = this;
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		registerIcons();
	}

	public void stop(BundleContext context) throws Exception {
		super.stop(context);
	}

	public static UmlPlugin getDefault() {
		return plugin;
	}

	public static String getResourceString(String key) {
		ResourceBundle bundle = UmlPlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	private void registerIcons() {
		getImageRegistry().put(IIconConstants.TYPE_ICON,
				getDescriptor("icons/type.gif"));
		getImageRegistry().put(IIconConstants.MEMBER_ICON,
				getDescriptor("icons/member.gif"));
		getImageRegistry().put(IIconConstants.CONNECTION_ICON,
				getDescriptor("icons/connection.gif"));
	}

	private ImageDescriptor getDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public static ImageDescriptor getImageDescriptor(String id) {
		return getDefault().getImageRegistry().getDescriptor(id);
	}

	public static Image getImage(String id) {
		return getDefault().getImageRegistry().get(id);
	}
}
