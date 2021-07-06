package com.qa.OpenCart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.OpenCart.Utils.Constants;
import com.qa.OpenCart.Utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	
	
	//By locators 
	private By emailID = By.id("input-email");
	private By password = By.id("input-password");
	private By forgottenPassword = By.linkText("Forgotten Password");
	private By loginbtn = By.xpath("//input[@class='btn btn-primary']");
	
	
	//2. constructors
	
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	//3. page actions
	
	public String getLoginPageTitle() {
		return elementUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE,5);
				
	}
		
	public boolean isForgotPwdLinkExist() {
		return elementUtil.doElementDisplayed(forgottenPassword);
		
	}
	
	public AccountsPage doLogin(String username , String pswd) {
         elementUtil.doSendkeys(emailID, username);		
         elementUtil.doSendkeys(password, pswd);	
         elementUtil.doClick(loginbtn);	
         return new AccountsPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
