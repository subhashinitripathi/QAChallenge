package com.automation.AutomationQAChallenge.QAChallenge.signUp;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import com.automation.AutomationQAChallenge.QAChallenge.Common.CommonImpl;
import com.automation.AutomationQAChallenge_apis.QAChallenge_apis.conguration.Config;
import com.automation.AutomationQAChallenge_apis.QAChallenge_apis.conguration.driverUtils.BrowserDriver;

/**
 * This test case signs up for an monthly plan. It starts from the sign up window and completes the full flow
 * 
 */
public class SignUpMonthlyPage extends CommonImpl{
	
	@BeforeClass
	public static void setupClass() throws Exception {
		Config.readConfig();
		BrowserDriver.setupBrowser();
		driver = BrowserDriver.driver;
		email_id = "testuser."+System.currentTimeMillis()+"@nc.com";
		url = System.getProperty("app.url", Config.APPLICATION_URL);
		selectorMap = getPath(testdata);
	}
	
	//This test covers register user, buy monthly plan and payment by using credit card.
	@Test(timeout = 200000)
	public void signUPMonthly_Test() throws Exception {
		System.out.println("signUPMonthly_Test is running...");
		driver.get(url);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(selectorMap.get("SIGN_UP_BUTTON"))).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(selectorMap.get("MONTHLY_PLAN"))).click();
		verifyPageTitle("Create credentials", "PAGE_TITLE");
		registerUser();									//register the user
		verifyAndClickPopUp();							//verifying the popup window
		verifyPageTitle("Payment", "PAGE_TITLE4");
		verifyPageTitle("95 SEK", "TRIAL_PAYMENT_THERM_PRICE");
		payment();										//payment by credit card
		Thread.sleep(10000);
		verifyPopupAndClick("Payment successful!");		//verifying the payment success popup
		String content ="That is it. Now let's set up your personal experience.";
		verifyPageTitle(content, "GET_STARTED_PAGE");	//verifying the get started page
	}
	
	@AfterClass
	public static void reset(){
		driver.quit();
	}

}
