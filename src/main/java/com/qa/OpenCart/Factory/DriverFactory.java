package com.qa.OpenCart.Factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
    /*
     * this method is used to initialize the driver
     * 
     */
	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser");
		System.out.println("browser name is :" + browserName);

		if (browserName.equalsIgnoreCase("CHROME")) {
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
		} 
		else if (browserName.equals("fireFox")) {
			WebDriverManager.firefoxdriver().setup();
		 driver = new FirefoxDriver();
		}
		
		else if(browserName.equals("safari")) {
			driver = new SafariDriver();
		}
		
		else {
			System.out.println("please pass the right browser :" + browserName);
		}
		driver.get(prop.getProperty("url") );
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;
	}

	
	public Properties init_prop() {
		
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/Config.Properties");
			try {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return prop;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
