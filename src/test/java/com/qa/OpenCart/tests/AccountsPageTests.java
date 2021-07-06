package com.qa.OpenCart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.OpenCart.Utils.Constants;

public class AccountsPageTests extends BaseTest {

	@BeforeClass
	public void accPageSetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
	}
	
	
	@Test
	public void AccountPageTitleTest() {
		String actTitle = accountsPage.getAccountPageTitle();
		System.out.println("acc page title is :" + actTitle);
		Assert.assertEquals(actTitle, Constants.Accounts_PAGE_TITLE);
	}
	
	
	@Test
	public void  accPageHeaderTitle() {
		String actAccountHeader = accountsPage.getAccountsPageHeader();
		Assert.assertEquals(actAccountHeader, Constants.Accounts_PAGE_HEADER);
	}
	
	@Test
	public void accSectionListTest() {
		List<String> actualSeclist = accountsPage.getAccountSectionList();
		System.out.println("Acc sec list are :" + actualSeclist);
		Assert.assertEquals(actualSeclist, Constants.getAccSecListExpected());
	}
	
	@Test
	public void logoutlInkTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}
	
	
	@Test
	public boolean searchExistTest() {
		return accountsPage.doSearchExist();
	}
	
	
	
	
	
	
	
	
	
}
