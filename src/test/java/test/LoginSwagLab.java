package test;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import controller.BaseController;
import controller.LoginController;
import utility.BaseTestScript;
import utility.TestData;
import utils.BrowserManager;

public class LoginSwagLab extends BaseTestScript {

	LoginController login = new LoginController();
	BaseController base = new BaseController();
	final private Logger log = LogManager.getLogger(LoginSwagLab.class);

	/**
	 * Method is use to login to application with valid credential
	 * 
	 * @param username - Valid username
	 * @param password - Valid Password
	 * @throws Exception
	 */
	@Test(priority = 1 , dataProvider = "getData")
	public void loginWithValidCredential(TestData data) throws Exception {

		test = reports.startTest("Verify that user is able to login with valid credential");
		stepName = "Verify User sucessfully log in to application";
		assertTrue(login.loginSwagLab(data), "Failed to " + stepName);
		logger();
		
//		test = reports.startTest("Verify that user is able to logout through application");
		stepName = "Verify User sucessfully logout through application";
		assertTrue(login.logoutSwagLab(), "Failed to " + stepName);
		logger();
	}

	@Test(dataProvider = "getData", priority = 2)
	public void loginWithIValidCredential(TestData data) throws Exception {

		test = reports.startTest("Verify that user is not able to login with invalid credential");
		stepName = "Verify User is not able to login to application";
		assertTrue(login.loginSwagLab(data), "Failed to " + stepName);
		logger();
	}
//	/**
//	 * This method will get failed as we are passing invalid credential
//	 * 
//	 * @param username - InValid username
//	 * @param password - InValid Password
//	 * @throws Exception
//	 */
//	@Test(dataProvider = "invalidCredential", priority = 2)
//	public void loginWithIValidCredential(String username, String password) throws Exception {
//
//		test = reports.startTest("Verify that user is notable to login with Invalid credential");
//		stepName = "Verify User is not able to login to application";
//		assertTrue(login.loginSwagLabwithInvalidData(username, password), "Failed to " + stepName);
//		logger();
//	}
//
//	@DataProvider(name = "invalidCredential")
//	public Object[][] dpMethod1() {
//		return new Object[][] { { "stand_user", "sec_sauce" } };
//	}
//
//	/**
//	 * Method will get skipped as it depend on failed method ie
//	 * loginWithIValidCredential
//	 */
	@Test(dependsOnMethods = "loginWithIValidCredential")
	public void skipTest() {

		System.out.println("Skipped testcase as login is failed");

	}
}