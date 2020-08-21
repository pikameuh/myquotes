package com.astek.myquotes.utility;

import java.util.ResourceBundle;

public  class ResBundle {
	
	static ResourceBundle mybundle = ResourceBundle.getBundle("data");

	public static String getUrl() {
		System.out.println("------------------------------------");
		System.out.println("url : " +  mybundle.getString("url"));
		return mybundle.getString("url");
	}
	
	public static String getMyQuoteEmail() {
		System.out.println("------------------------------------");
		System.out.println("Email : " +  mybundle.getString("email"));
		return mybundle.getString("email");
	}
}
