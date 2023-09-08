//package pom;
//import java.util.Properties;
//
//import org.apache.log4j.Logger;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.testng.Assert;
//
//
//
//import controller.LoginController;
//import utility.ConfigFileReader;
//import utils.Interact;
//
///**
// * It is a object repository use to manage the locator according to page: login
// * page
// */
//public class LoginPage extends LoginController {
//
//	final private Logger log = Logger.getLogger(LoginController.class.getName());
//	protected ConfigFileReader config = new ConfigFileReader();
//	public Properties prop;
//	
//	public LoginPage() throws Exception {
//		prop = config.readConfigFile();
//	}
//	
//	/**
//	 * Method is used to login to application
//	 * 
//	 * @param userid - username
//	 * @param pwd - password
//	 * @return 
//	 * @throws Exception 
//	 */
//	public boolean loginSwagLab(String userid, String pwd) throws Exception {
//
////		enterUserName(userid,prop.getProperty("username"));
////		log.info("UserName is Set with text as: " + userid);
//
//		enterPassword(pwd,prop.getProperty("password"));
//		log.info("Password is Set with text as: " + pwd);
//		
//		clickLoginBtn(prop.getProperty("loginBtn"));
//		log.info("Login button is clicked.");
//		
//		Boolean b = validateLoginText(prop.getProperty("product"));
//		log.info("Login data is validated");
//		
//		return b;
//	}
//
//	/**
//	 * Method is used to logout through application
//	 * @throws Exception 
//	 */
//	public boolean logoutSwagLab() throws Exception {
//
//		clickMenuBar(prop.getProperty("menubar"));
//		log.info("Menu Bar is clicked.");
//
//		clickLogoutBtn(prop.getProperty("logout"));
//		log.info("Logout button is clicked.");
//
//		Boolean b = validateElementIsDisplayed(prop.getProperty("loginLogo"));
//		Assert.assertTrue(b, "User not able to logout");
//		return b;
//	}
//
//}
