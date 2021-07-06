package com.qa.OpenCart.Utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	public WebDriver driver;

	public ElementUtil(WebDriver driver) { // this is the constructor to pass the driver
		this.driver = driver;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public void doSendkeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();

	}

	public boolean doElementDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public List<WebElement> doGetElements(By locator) {
		return driver.findElements(locator);
	}

	public int getLinksCount(By locator) {
		return doGetElements(locator).size();
	}

	public List<String> getLinksTextList(By locator) {
		List<WebElement> list = doGetElements(locator);
		List<String> textList = new ArrayList<String>();
		for (WebElement e : list) {
			String text = e.getText();
			if (!text.isEmpty()) {
				textList.add(text);

			}
		}
		return textList;
	}

	public List<String> getAttributeList(By locator, String attributeName) {
		List<WebElement> elelist = doGetElements(locator);
		List<String> attrList = new ArrayList<String>();

		for (WebElement e : elelist) {
			String attrVal = e.getAttribute(attributeName);
			attrList.add(attrVal);
		}
		return attrList;
	}

	public void verifyElementPresent(By locator) {
		List<WebElement> emaillist = doGetElements(locator);
		System.out.println(emaillist.size()); // 0 I WILL GET 0 as weblement is wrong

		try {
			if (emaillist.size() == 0) {
				System.out.println("element not present");
				throw new Exception("ElementNotPresentException");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/********
	 * 
	 * select dropdown with select utils
	 * 
	 */

	public void doSelectDropDownbyIndex(By locator, int Index) {
		Select Sel = new Select(getElement(locator));
		Sel.selectByIndex(Index);
	}

	public void doSelectDropDownbyVisibleText(By locator, String text) {
		Select Sel = new Select(getElement(locator));
		Sel.selectByVisibleText(text);
	}

	public void doSelectDropDownbyValue(By locator, String value) {
		Select Sel = new Select(getElement(locator));
		Sel.selectByValue(value);

	}

	// generic function without select by index,value, visibletext ....
	public List<String> doGetOptions(By locator) {
		List<String> OptiontextList = new ArrayList<String>();
		Select sel = new Select(getElement(locator));
		List<WebElement> List = sel.getOptions();
		System.out.println(List.size());

		for (WebElement e : List) {
			String text = e.getText();
			OptiontextList.add(text);
		}
		return OptiontextList;

	}

	public void doSelectValue(By locator, String Value) {
		Select Select = new Select(getElement(locator));
		List<WebElement> List = Select.getOptions();

		for (WebElement e : List) {
			String text = e.getText();
			if (text.contains(Value)) {
				e.click();
				break;
			}
		}
	}

	/*************
	 * 
	 * without select utils though we have select tag
	 * 
	 * 
	 */

	public void selectDropdownValue(By locator, String Value) {
		List<WebElement> optionList = doGetElements(locator);

		for (WebElement e : optionList) {
			String text = e.getText();
			if (text.contains(Value)) {
				e.click();
				break;
			}

		}
	}

	public void selectValueFromSuggestList(String value) {
		driver.findElement(By.xpath("//div[@class='ac_results']//li[text()= '" + value + "']")).click();

	}

	// ****************Footerlinklist**************//
	public void doFooterlink(By locator, String value) {
		List<WebElement> footerlinkList = doGetElements(locator);
		for (WebElement e : footerlinkList) {
			System.out.println(footerlinkList.size());
			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible. Default
	 * Intervla/polling Time = 500 ms
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement doPresenceOfElementLocated(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement doPresenceOfElementLocated(By locator, int timeout, int intervaltime) {
		WebDriverWait wait = new WebDriverWait(driver, timeout, intervaltime);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element, known to be present on the DOM
	 * of a page, is visible. Visibility means that the element is not only
	 * displayed but also has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */

	public WebElement isElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}

	public List<WebElement> VisibilityAllElements(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public Boolean waitForElementsToBeInVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void printAllElementsTextwithWait(By locator, int timeOut) {
		VisibilityAllElements(locator, timeOut).stream().forEach(e -> System.out.println(e.getText()));
	}

	public List<String> getElementsTextListWithWait(By locator, int timeOut) {
		List<WebElement> elementsList = VisibilityAllElements(locator, timeOut);
		List<String> elementsTextList = new ArrayList<String>();
		for (WebElement e : elementsList) {
			elementsTextList.add(e.getText().trim());
		}
		return elementsTextList;
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeOut
	 */
	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public void clickElementWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(getElement(locator))).click();
	}

	//////////// **************Alert**************//////////////////

	private Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	public void acceptAlert(int timeOut) {
		waitForAlert(timeOut).accept();
	}

	public void dismissAlert(int timeOut) {
		waitForAlert(timeOut).dismiss();
	}

	public String alertGetText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}

	public void alertSendKeys(int timeOut, String value) {
		waitForAlert(timeOut).sendKeys(value);
	}

// **************************framehandle*************************//

	public void frameToBeAvailableAndSwitchToIt(String nameORId, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameORId));

	}

	public void frameToBeAvailableAndSwitchToIt(By framelocator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(framelocator));
	}

	public void frameToBeAvailableAndSwitchToIt(int frameindex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameindex));
	}

	public void frameToBeAvailableAndSwitchToIt(WebElement frameElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	///////////// ****** waitfor URL ---NON WEBELEMENTS**************/////
	public boolean waitForURLFraction(String URLfraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlContains(URLfraction));
	}

	public boolean waitforURLToBe(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlToBe(url));
	}

	public String waitForTitleIs(String expTitle, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		boolean flag = wait.until(ExpectedConditions.titleIs(expTitle));
		if (flag)
			return driver.getTitle();
		return null;
	}

	///////////////////// ******fluentWait***************///////////////////////////

//	public WebElement waitForElementDemo(By locator, int timeOut, long pollingTime) {
//		WebDriverWait wait = new WebDriverWait(driver, timeOut);
//				wait.withTimeout(Duration.ofSeconds(timeOut))
//				.withMessage(Error.TIME_OUT_WEB_ELEMENT_MSG)
//					.pollingEvery(Duration.ofMillis(pollingTime))
//						.ignoring(NoSuchElementException.class, 
//									StaleElementReferenceException.class);
//		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//	}
//
//	public WebElement waitForElementWithFluentWait(By locator, int timeOut, long pollingTime) {
//		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//				.withTimeout(Duration.ofSeconds(timeOut))
//				.withMessage(Error.TIME_OUT_WEB_ELEMENT_MSG)
//				.pollingEvery(Duration.ofMillis(pollingTime))
//				.ignoring(NoSuchElementException.class);
//
//		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//
//	}

//	public Alert waitForAlertWithFluentWait(int timeOut, long pollingTime) {
//		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//				.withTimeout(Duration.ofSeconds(timeOut))
//				.withMessage(Error.TIME_OUT_ALERT_MSG)
//				 .pollingEvery(Duration.ofMillis(pollingTime))
//				 .ignoring(NoAlertPresentException.class);
//
//		return wait.until(ExpectedConditions.alertIsPresent());
//
//	}
//
//	public WebDriver waitForFrameWithFluentWait(String frameLocator, int timeOut, long pollingTime) {
//		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//				.withTimeout(Duration.ofSeconds(timeOut))
//				.withMessage(Error.TIME_OUT_FRAME_ELEMENT_MSG)
//				.pollingEvery(Duration.ofMillis(pollingTime))
//				.ignoring(NoSuchFrameException.class);
//
//		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
//
//	}


}
