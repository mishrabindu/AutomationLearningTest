package com.qa.OpenCart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.OpenCart.Utils.Constants;

public class LoginPageTest extends BaseTest {
	
	@Test
	public void LoginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("page title is :" + actTitle);
		Assert.assertEquals(actTitle , Constants.LOGIN_PAGE_TITLE);
	}
	
	
	@Test
	public void ForgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
		
	}
	
	
	@Test
	public void LoginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertEquals(accountsPage.getAccountPageTitle()
				, Constants.Accounts_PAGE_TITLE);
	}
				
				
				
				
				
				
				
				
	}
	
	
	
	
	
	
	
	
	
	
	
	


