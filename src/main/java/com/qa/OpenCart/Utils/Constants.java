package com.qa.OpenCart.Utils;

import java.util.Arrays;
import java.util.List;

public class Constants {
	
	public final  static String LOGIN_PAGE_TITLE = "Account Login";
	public final static String Accounts_PAGE_TITLE = "My Account";
	
	public final static String Accounts_PAGE_HEADER = "Your Store";

	
	
	public static List<String> getAccSecListExpected() {
		return Arrays.asList("My Account" , "My Orders" , "My Affiliate Account" ,"Newsletter");
	}
}
