package com.qa.OpenCart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.OpenCart.Utils.Constants;
import com.qa.OpenCart.Utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil ;
	
	private By header = By.xpath("//div[@id='logo']//a");
	private By accountSections = By.xpath("//div[@id='content']/h2");
	private By search = By.className("form-control input-lg");
	private By logoutlink = By.linkText("Logout");
	
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
		
	}
	

	public String getAccountPageTitle() {
		return elementUtil.waitForTitleIs(Constants.Accounts_PAGE_TITLE, 5);
	}
	
	public String getAccountsPageHeader() {
		return elementUtil.doGetText(header);
	}
	
	public boolean isLogoutLinkExist() {
		return elementUtil.doElementDisplayed(logoutlink);
	}
	
	public void logoutclick() {
		if(isLogoutLinkExist()) {
			elementUtil.doClick(logoutlink);
		}
	}
	
	public List<String> getAccountSectionList() {
		List<WebElement> accSecList = elementUtil.VisibilityAllElements(accountSections, 5);
		List<String> accSecValList = new ArrayList<String>() ;
		System.out.println(accSecList.size());
		for (WebElement e : accSecList) {
			String text = e.getText();
			accSecValList.add(text);
		}
		return accSecValList;
		
	}
	
	public boolean doSearchExist() {
		return elementUtil.doElementDisplayed(search);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
