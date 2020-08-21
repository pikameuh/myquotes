package com.astek.myquotes.utility.bundles;

import java.util.ResourceBundle;

public  class ResDataBundle {
	
	static ResourceBundle mybundle = ResourceBundle.getBundle("bundles/data");

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
	
	public static String getEmailMessageStart() {
		System.out.println("------------------------------------");
		System.out.println("email.msg.start : " +  mybundle.getString("email.msg.start"));
		return mybundle.getString("email.msg.start");
	}
}
