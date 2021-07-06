package com.qa.OpenCart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.OpenCart.Factory.DriverFactory;
import com.qa.OpenCart.pages.AccountsPage;
import com.qa.OpenCart.pages.LoginPage;

public class BaseTest {
	
	public WebDriver driver;
	public DriverFactory driverFactory;
	public Properties prop;
	public LoginPage loginPage;
	public AccountsPage accountsPage;
	
	@BeforeTest
	public void setUp() {
		driverFactory = new DriverFactory();
		prop = driverFactory.init_prop();
		driver = driverFactory.init_driver(prop);
		loginPage = new LoginPage(driver);
	}

	
	
	
	
	
	
	
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
		
	}
}
