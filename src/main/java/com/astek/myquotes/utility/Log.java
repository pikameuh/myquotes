package com.astek.myquotes.utility;

public class Log {
	private static boolean debug = true;
	
	public static void debug (String msg) {
		if(debug) {
			System.out.println(msg);
		}
	}
}
