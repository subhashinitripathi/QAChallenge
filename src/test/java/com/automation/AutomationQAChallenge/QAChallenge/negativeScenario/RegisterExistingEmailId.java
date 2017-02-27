package com.automation.AutomationQAChallenge.QAChallenge.negativeScenario;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import com.automation.AutomationQAChallenge.QAChallenge.Common.CommonImpl;
import com.automation.AutomationQAChallenge_apis.QAChallenge_apis.conguration.Config;
import com.automation.AutomationQAChallenge_apis.QAChallenge_apis.conguration.driverUtils.BrowserDriver;

/**
 * Checking the negative scenario. 
 * This test case is trying to register the exiting email id which is already register into the system. 
 * It starts from the sign up window and completes the full flow
 * 
 */
public class RegisterExistingEmailId extends CommonImpl{
	
	@BeforeClass
	public static void setupClass() throws Exception {
		Config.readConfig();
		url = System.getProperty("app.url", Config.APPLICATION_URL);
		BrowserDriver.setupBrowser();
		driver = BrowserDriver.driver;
		email_id = "testuser."+System.currentTimeMillis()+"@nc.com";
		selectorMap = getPath(testdata);
	}
	
	@Before
	public void register() throws Exception{
		driver.get(url);										//launch the url
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(selectorMap.get("SIGN_UP_BUTTON"))).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(selectorMap.get("FREE_TRIAL"))).click();
		verifyPageTitle("Create credentials", "PAGE_TITLE");	//verify the Create Credentials page
		registerUser();											//register the user
		verifyAndClickPopUp();									//verify the popup window
		driver.close();											//closing the browser
		BrowserDriver.setupBrowser();
		driver = BrowserDriver.driver;
	}
	
	//Trying to register the already registered email id 
	@Test(timeout = 100000)
	public void signUPWithExistingEmail_Test() throws Exception {
		System.out.println("signUPWithExistingEmail_Test is runing....");
		driver.get(url);										//launch the url
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(selectorMap.get("SIGN_UP_BUTTON"))).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(selectorMap.get("FREE_TRIAL"))).click();
		verifyPageTitle("Create credentials", "PAGE_TITLE");	//verify the Create Credentials page
		System.out.println("Same Email Id is tring to register ..Email ID is :: "+email_id);	
		driver.findElement(By.cssSelector(selectorMap.get("EMAIL_ID")))
			.sendKeys(email_id);
		System.out.println("Password is :: "+password);
		driver.findElement(By.cssSelector(selectorMap.get("PASSWORD")))
			.sendKeys(password);
		driver.findElement(By.cssSelector(selectorMap.get("PASSWORD1")))
			.sendKeys(password);
		Thread.sleep(5000);
		String content = "The email you have entered is already in use with an existing Natural Cycles account";
		String message = getTextByCSSSelector(selectorMap.get("POPUP_BODY"));
		assertTrue("Incorrect message is displayed on the popUp. Message Contain ::"+message, message.equalsIgnoreCase(content));
	}
	
	@After
	public void closeBrowser(){
		driver.quit();
	}
}
