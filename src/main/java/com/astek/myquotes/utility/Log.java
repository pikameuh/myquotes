package com.astek.myquotes.utility;

import com.astek.myquotes.utility.bundles.ResDebugBundle;

public class Log {
	private static boolean debug = ResDebugBundle.isDebug();
	
	public static void debug (String msg) {
		if(debug) {
			System.out.println(msg);
		}
	}
}
