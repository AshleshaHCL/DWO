package utils;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import test.LoginSwagLab;

public class BrowserManager {

	static Logger log =LogManager.getLogger(BrowserManager.class);
	
	/**
	 * Initializing the bowser so that same object can be used through out the program
	 * Navigate to URL
	 * Validating title
	 * @param type
	 * @param url
	 * @param expTitle
	 * @return
	 */
	public static WebDriver getDriver(String type,String url,String expTitle) {
		WebDriver driver = null;
		if(type.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "E:/HCLWorkspace/chromeDriver/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(type.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();			
		}
		else {
			Assert.assertTrue(false,"No Browser type sent");
		}
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/v1/");
		String actualTitle = driver.getTitle().trim();
		Assert.assertEquals(actualTitle, expTitle);
		log.info("Initialized the browser : " +type+" and navigate to URL : "+url);
		return driver;
	}
}
