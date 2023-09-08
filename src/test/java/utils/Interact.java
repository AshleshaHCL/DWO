package utils;
import java.time.Duration;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * In this class we are declaring method which are reusable for all the element
 * and to control the click event flow
 * 
 */

public class Interact {

	WebDriver driver;
	final private Logger log = LogManager.getLogger(Interact.class);

	public void setDriver(WebDriver d) {
		this.driver = d;
	}

	/**
	 * This method is used to click on specific element
	 * 
	 * @param webElement
	 */
	public void clickElement(WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5,0));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webElement));
		webElement.click();
		log.info("Element is clicked. Element Description: " + webElement.toString());
	}

	public WebElement setElement(WebElement webElement, String text) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5,0));
		WebElement element = wait.until(ExpectedConditions.visibilityOf(webElement));
		element.sendKeys(text);
		log.info("Element is Set with text as: " + text + ". Element Description: " + webElement.toString());
		return element;
	}

	/**
	 * Method will return the text of element
	 * 
	 * @param webElement
	 * @return
	 */
	public String getText(WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5,0));
		WebElement element = wait.until(ExpectedConditions.visibilityOf(webElement));
		String value = element.getText().trim();
		log.info("Get Text for element: " + webElement.toString() + " Text : " + value);
		return value;
	}

	/**
	 * This method is used to check whether element is displayed and return data in
	 * boolean form
	 * 
	 * @param webElement
	 * @return
	 */
	public boolean validateElementIsDisplayed(WebElement webElement) {
		boolean b = webElement.isDisplayed();
		log.info("Element is Displayed status: " + webElement.toString());
		return b;
	}

}
