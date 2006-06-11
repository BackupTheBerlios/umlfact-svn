package com.xiaobo.uml;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * 
 * @author xiaobo
 * 
 * Copyright 2006 by Xiaobo Sun. All Rights reserved.
 */
public class UmlPlugin extends AbstractUIPlugin {

	private static final String PLUGIN_ID = "com.xiaobo.uml";

	// The shared instance.
	private static UmlPlugin plugin;

	/**
	 * The constructor.
	 */
	public UmlPlugin() {
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		registerIcons();
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static UmlPlugin getDefault() {
		return plugin;
	}

	private void registerIcons() {
		getImageRegistry().put(IIconConstants.TYPE_ICON,
				getDescriptor("icons/type.gif"));
		getImageRegistry().put(IIconConstants.MEMBER_ICON,
				getDescriptor("icons/member.gif"));
		getImageRegistry().put(IIconConstants.ARROW_ICON,
				getDescriptor("icons/arrow.gif"));
		getImageRegistry().put(IIconConstants.COMPARTMENT_ICON,
				getDescriptor("icons/compartment.gif"));
		getImageRegistry().put(IIconConstants.ATTRIBUTE_ICON,
				getDescriptor("icons/attribute.gif"));
		getImageRegistry().put(IIconConstants.METHOD_ICON,
				getDescriptor("icons/method.gif"));
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
