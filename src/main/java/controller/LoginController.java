package controller;

import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utility.ConfigFileReader;
import utility.TestData;

public class LoginController extends BaseController {

	final private Logger log = Logger.getLogger(LoginController.class.getName());
	
	protected ConfigFileReader configreader = new ConfigFileReader();
	BaseController base = new BaseController();
	Properties webObject;
	Properties config;
	String filename= "LoginConfig";
	
	public LoginController()
	{
	 webObject = configreader.readWebObjectRepoFile(this.filename);
	 config =configreader.readConfigFile();
	}

	/**
	 * Method to login to application
	 * @param data 
	 * @return
	 * @throws Exception
	 */
	public boolean loginSwagLab(TestData data) throws Exception {

		base.enterText(data.getUsername(), webObject.getProperty("username"));
		log.info("UserName is Set with text as: " + data.getUsername());

		base.enterText(data.getPassword(), webObject.getProperty("password"));
		log.info("Password is Set with text as: " + data.getUsername());
		
		base.clickElement(webObject.getProperty("loginBtn"));
		log.info("Login button is clicked.");
		
		Boolean b = base.validateText("products",webObject.getProperty("product"));
		log.info("Login data is validated");
		
		return b;
	}
	
	public boolean loginSwagLabwithInvalidData(String username, String pwd) throws Exception {

		base.enterText(username, webObject.getProperty("username"));
		log.info("UserName is Set with text as: " + username);
		
		base.enterText(pwd, webObject.getProperty("password"));
		log.info("Password is Set with text as: " +pwd);
		
		base.clickElement(webObject.getProperty("loginBtn"));
		log.info("Login button is clicked.");
		
		Boolean b = base.validateText("products",webObject.getProperty("product"));
		log.info("Login data is validated");
		
		return b;
	}
	
	/**
	 * Method is used to logout through application
	 * @throws Exception 
	 */
	public boolean logoutSwagLab() throws Exception {

		base.clickElement(webObject.getProperty("menubar"));
		log.info("Menu Bar is clicked.");

		base.clickElement(webObject.getProperty("logout"));
		log.info("Logout button is clicked.");

		return isDisplayed(webObject.getProperty("loginLogo"));
//		Assert.assertTrue(b, "User not able to logout");
	}

}
