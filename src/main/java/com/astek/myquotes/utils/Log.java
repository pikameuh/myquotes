package com.astek.myquotes.utils;

public class Log {
	private static boolean debug = false;
	
	public static void debug (String msg) {
		if(debug) {
			System.out.println(msg);
		}
	}
}
