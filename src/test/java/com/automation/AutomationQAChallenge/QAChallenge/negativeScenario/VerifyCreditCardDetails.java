package com.automation.AutomationQAChallenge.QAChallenge.negativeScenario;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import com.automation.AutomationQAChallenge.QAChallenge.Common.CommonImpl;
import com.automation.AutomationQAChallenge_apis.QAChallenge_apis.conguration.Config;
import com.automation.AutomationQAChallenge_apis.QAChallenge_apis.conguration.driverUtils.BrowserDriver;

/**
 * This test case is verify the credit card details. 
 * It starts from the sign up window and completes the full flow
 * 
 */
public class VerifyCreditCardDetails extends CommonImpl{
	
	@BeforeClass
	public static void setupClass() throws Exception {
		Config.readConfig();
		BrowserDriver.setupBrowser();
		driver = BrowserDriver.driver;
		email_id = "testuser."+System.currentTimeMillis()+"@nc.com";
		url = System.getProperty("app.url", Config.APPLICATION_URL);
		card_no = System.getProperty("card.no", Config.CARD_NO);
		expiration_date = System.getProperty("expiration.date", Config.EXPIRATION_DATE);
		cvv_no = System.getProperty("cvv.no", Config.CVV_NO);
		selectorMap = getPath(testdata);
	}
	
	//Verify credit card number, expiration date and cvv no
	@Test(timeout = 200000)
	public void verifyCardAndPay_Test() throws Exception {
		System.out.println("signUPMonthly_Test is running...");
		driver.get(url);										//launch the url
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(selectorMap.get("SIGN_UP_BUTTON"))).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(selectorMap.get("MONTHLY_PLAN"))).click();
		verifyPageTitle("Create credentials", "PAGE_TITLE");	//verify the Create credentials page
		registerUser();											//register the user
		verifyAndClickPopUp();									//verify the popup
		verifyAndPay();											//validate credit card details and pay
		Thread.sleep(10000);
		verifyPopupAndClick("Payment successful!");				//verify the payment success popup
		String content ="That is it. Now let's set up your personal experience.";
		verifyPageTitle(content, "GET_STARTED_PAGE");			//verify the GET STARTED page
	}
	
	@AfterClass
	public static void reset(){
		driver.quit();
	}


}
