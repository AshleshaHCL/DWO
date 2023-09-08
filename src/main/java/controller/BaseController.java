package controller;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.ConfigFileReader;

public class BaseController extends ConfigFileReader {

	final private Logger log = Logger.getLogger(BaseController.class.getName());
	
	public static WebDriver driver;
	protected ConfigFileReader config = new ConfigFileReader();
	public Properties prop;
	public static String fileType = null;

	public BaseController() {
		prop = config.readConfigFile();
	}

	WebDriver getdriver() throws Exception {
		try {
			prop = config.readConfigFile();
			if (driver == null) {
				switch (prop.getProperty("browser").toLowerCase()) {
				case "chrome":
					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.addArguments("disable-inforbars");
					chromeOptions.addArguments("detach");
					chromeOptions.addArguments("--remote-allow-origins=*");
//					WebDriverManager.chromedriver().setup();
					System.setProperty("webdriver.chrome.driver", "E:/HCLWorkspace/chromeDriver/chromedriver.exe");//user dir
					driver = new ChromeDriver(chromeOptions);
					driver.manage().window().maximize();
					driver.navigate().to(prop.getProperty("web_url"));
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
					break;
				case "ie":
					break;
				case "firefox":
					break;
				default:
					ChromeOptions chromeOptions1 = new ChromeOptions();
					chromeOptions1.addArguments("disable-inforbars");
//					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					driver.manage().window().maximize();
					driver.navigate().to(prop.getProperty("web_url").toString());
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}

			}
			return driver;
		} catch (Exception e) {
			System.out.println("Unable to create webDriver instance" + e.getMessage());
		}
		return driver;

	}

	WebElement findElement(String path) throws Exception {
		try {
			this.getdriver();

			if (driver == null)
				throw new Exception("WebDriver instance is not created");
			else {
				WebElement ele = null;
				String[] s = path.split("\\|");
				String locator = s[0].trim();
				String value = s[1].trim();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5, 0));

				switch (locator) {
				case "xpath":
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));
					ele = driver.findElement(By.xpath(path));
					break;

				case "id":
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id(value)));
					ele = driver.findElement(By.id(value));
					break;

				case "className":
					wait.until(ExpectedConditions.presenceOfElementLocated(By.className(value)));
					ele = driver.findElement(By.className(value));
					break;

				case "name":
					wait.until(ExpectedConditions.presenceOfElementLocated(By.name(value)));
					ele = driver.findElement(By.name(value));
					break;

				case "linkText":
					wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(value)));
					ele = driver.findElement(By.linkText(value));
					break;

				case "partialLinkText":
					wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(value)));
					ele = driver.findElement(By.partialLinkText(value));
					break;

				case "tagName":
					wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(value)));
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(value)));
					ele = driver.findElement(By.tagName(value));
					break;

				default:
					throw new Exception("Unable to find element");
				}
				return ele;
			}
		} catch (Exception e) {
			throw new Exception("Unable to find element" + e.getMessage());
		}
	}

	List<WebElement> findElements(String path) throws Exception {

		List<WebElement> eles = null;

		try {
			this.getdriver();

			if (driver == null)
				throw new Exception("WebDriver instance is not created");
			else {
				String[] s = path.split("\\|");
				String locator = s[0].trim();
				String value = s[1].trim();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20, 0));

				switch (locator) {
				case "xpath":
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(path)));
					eles = driver.findElements(By.xpath(path));
					break;

				case "id":
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(value)));
					eles = driver.findElements(By.id(value));
					break;

				case "className":
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(value)));
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(value)));
					break;

				case "name":
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name(value)));
					eles = driver.findElements(By.name(value));
					break;

				case "linkText":
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText(value)));
					eles = driver.findElements(By.linkText(value));
					break;

				case "partialLinkText":
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.partialLinkText(value)));
					eles = driver.findElements(By.partialLinkText(value));
					break;

				case "tagName":
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName(value)));
					eles = driver.findElements(By.tagName(value));
					break;

				default:
					throw new Exception("Unable to find element");
				}
			}
			return eles;
		} catch (Exception e) {
			throw new Exception("Unable to find elements" + e.getMessage());
		}
	}

	Boolean isDisplayed(String path) throws Exception {
		try {
			this.getdriver();
			WebElement ele = findElement(path);
			return ele.isDisplayed();
			
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public String getText(String path) throws Exception {
		return findElement(path).getText();
	}

	private void updateCommandLineProperty() {//This method is used t execute something from cmd
		// TODO Auto-generated method stub

	}

	public static String capture(String screenShotName) throws Exception {
		try {
			TakesScreenshot t = (TakesScreenshot) driver;
			File source = t.getScreenshotAs(OutputType.FILE);
			File logDir = new File(System.getProperty("user.dir") + "//screenshots//");
			if (!logDir.exists())
				logDir.mkdir();
			String destination = System.getProperty("user.dir") + "//screenshots//" + screenShotName + ".png";
			File dest = new File(destination);
			FileUtils.copyFile(source, dest);
			return destination;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;

	}

	public String logger(String stepname) {
		try {
			return this.capture(stepname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public void scrollIntoElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void jsClick(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public void moveToElement(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.clickAndHold();
		actions.release().perform();
	}
	
	/**
	 * Method to send text to specific element
	 * @param text - enter text 
	 * @param path - locator
	 * @return
	 * @throws Exception
	 */
	public Boolean enterText(String text, String path) throws Exception {
		WebElement usernme = findElement(path);

		if (usernme.isDisplayed()) {
			usernme.clear();
			usernme.sendKeys(text);
			return usernme.getAttribute("value").contentEquals(text);
		}
		return false;
	}
	
	/**
	 * Method to click on element
	 * 
	 * @return true if found, false otherwise
	 * @throws Exception
	 */
	public Boolean clickElement(String path) throws Exception {
		
		if (isDisplayed(path)) {
			findElement(path).click();
			return true;
		}
		return false;
	}
	
	/**
	 * Method to validate text
	 * 
	 * @return true if found, false otherwise
	 * @throws Exception
	 */
	public Boolean validateText(String expText, String path) throws Exception {
		String actualText = null;
		try {
			actualText = getText(path);
		} catch (Exception e) {

			log.info("Element is not identified :" + e);
		}
		Boolean b = actualText.equalsIgnoreCase(expText);
		Assert.assertTrue(b);
		Thread.sleep(4000);
		return b;
	}

}
