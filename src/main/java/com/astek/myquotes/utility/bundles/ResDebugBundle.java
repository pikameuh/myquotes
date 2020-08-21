package com.astek.myquotes.utility.bundles;

import java.util.ResourceBundle;

public  class ResDebugBundle {
	
	static ResourceBundle mybundle = ResourceBundle.getBundle("bundles/debug");

	public static boolean isDebug() {
		System.out.println("------------------------------------");
		System.out.println("debug : " +  mybundle.getString("debug"));
		return mybundle.getString("debug").toLowerCase().equals("true");
	}
}
