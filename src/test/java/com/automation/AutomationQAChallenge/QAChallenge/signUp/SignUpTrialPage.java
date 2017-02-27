package com.automation.AutomationQAChallenge.QAChallenge.signUp;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.AutomationQAChallenge.QAChallenge.Common.CommonImpl;
import com.automation.AutomationQAChallenge_apis.QAChallenge_apis.conguration.Config;
import com.automation.AutomationQAChallenge_apis.QAChallenge_apis.conguration.driverUtils.BrowserDriver;

/**
 * This test case signs up for free trial. It starts from the sign up window and completes the full flow
 * 
 */
public class SignUpTrialPage extends CommonImpl{
	
	@BeforeClass
	public static void setupClass() throws Exception {
		Config.readConfig();
		url = System.getProperty("app.url", Config.APPLICATION_URL);
		selectorMap = getPath(testdata);
	}
	
	
	@AfterClass
	public static void reset(){
		//driver.close();
	}
	
	@Before
	public void setUp() throws Exception{
		BrowserDriver.setupBrowser();
		driver = BrowserDriver.driver;
		email_id = "testuser."+System.currentTimeMillis()+"@nc.com";
	}
	
	@After
	public void closeBrowser(){
		driver.quit();
	}
	
	
	//This test covers register user and free trial with own thermometer.
	@Test(timeout = 100000)
	public void signUPTrialWithOwnThermometer_Test() throws Exception {
		System.out.println("signUPTrialWithOwnThermometer_Test is running...");
		driver.get(url);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(selectorMap.get("SIGN_UP_BUTTON"))).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(selectorMap.get("FREE_TRIAL"))).click();
		verifyPageTitle("Create credentials", "PAGE_TITLE");
		registerUser();									//register user
		verifyAndClickPopUp();							//verify popup window
		String content = "Do you own a thermometer"+"\n"+"that shows two decimals? *";
		verifyPageTitle(content, "TRIAL_PAGE");			//verify page title 
		driver.findElement(By.cssSelector(selectorMap.get("YES_BUTTON"))).click();
		String content1 ="That is it. Now let's set up your personal experience.";
		verifyPageTitle(content1, "GET_STARTED_PAGE");	//verifying the get started page
	}
	
	//This test covers register, free trial and buy thermometer.
	@Test(timeout = 200000)
	public void signUPTrialBuyThermometer_Test() throws Exception {
		System.out.println("signUPTrialBuyThermometer_Test is running...");
		driver.get(url);								//launch the url
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(selectorMap.get("SIGN_UP_BUTTON"))).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(selectorMap.get("FREE_TRIAL"))).click();
		verifyPageTitle("Create credentials", "PAGE_TITLE");
		registerUser();									//register the user
		verifyAndClickPopUp();
		String content = "Do you own a thermometer"+"\n"+"that shows two decimals? *";
		verifyPageTitle(content, "TRIAL_PAGE");			//verify the free trial page
		driver.findElement(By.cssSelector(selectorMap.get("NO_BUTTON"))).click();
		verifyPageTitle("Basal thermometer", "TRIAL_PAGE_THERM_TITLE");
		verifyPageTitle("199 SEK", "TRIAL_PAGE_THERM_PRICE");
		verifyPageTitle("(Free shipping)", "SHIPPING_PRICE");
		driver.findElement(By.cssSelector(selectorMap.get("BUY_BUTTON"))).click();
		Thread.sleep(2000);
		verifyPageTitle("Delivery address", "PAGE_TITLE3");
		registerAddress();								//register the user address
		verifyAndClickAddressPopUp();					//verify the popup and the address
		verifyPageTitle("Payment", "PAGE_TITLE3");
		verifyPageTitle("Basal thermometer °C", "TRIAL_PAYMENT_PAGE");
		verifyPageTitle("199 SEK", "TRIAL_PAYMENT_THERM_PRICE");
		verifyPageTitle("Free shipping", "TRIAL_PAYMENT_SHIPPING_PRICE");
		cardPayment();									// payment by using the cerdit card
		Thread.sleep(10000);
		verifyPopupAndClick("Payment successful!");		//verify the payment success popup
		String content1 ="That is it. Now let's set up your personal experience.";
		verifyPageTitle(content1, "GET_STARTED_PAGE");	//verify the get started page
	}
	
	//This test covers register, free trial without buying thermometer(No Thanks scenario).
	@Test(timeout = 200000)
	public void signUPTrialNoThermometer_Test() throws Exception {
		System.out.println("signUPTrialNoThermometer_Test is running...");
		driver.get(url);								//launch the url
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(selectorMap.get("SIGN_UP_BUTTON"))).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(selectorMap.get("FREE_TRIAL"))).click();
		verifyPageTitle("Create credentials", "PAGE_TITLE");
		registerUser();									//register the user
		verifyAndClickPopUp();
		String content = "Do you own a thermometer"+"\n"+"that shows two decimals? *";
		verifyPageTitle(content, "TRIAL_PAGE");			//verify the free trial page
		driver.findElement(By.cssSelector(selectorMap.get("NO_BUTTON"))).click();
		verifyPageTitle("Basal thermometer", "TRIAL_PAGE_THERM_TITLE");
		verifyPageTitle("199 SEK", "TRIAL_PAGE_THERM_PRICE");
		verifyPageTitle("(Free shipping)", "SHIPPING_PRICE");
		driver.findElement(By.cssSelector(selectorMap.get("NO_THANKS_BUTTON"))).click();
		String content1 ="That is it. Now let's set up your personal experience.";
		verifyPageTitle(content1, "GET_STARTED_PAGE");	//verify the GET STARED page
	}
	
	//This test covers register, free trial and buy yearly plan
	@Test(timeout = 200000)
	public void signUPTrialYearlyPlan_Test() throws Exception {
		System.out.println("signUPTrialYearlyPlan_Test is running...");
		driver.get(url);								//launch url
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(selectorMap.get("SIGN_UP_BUTTON"))).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(selectorMap.get("FREE_TRIAL"))).click();
		verifyPageTitle("Create credentials", "PAGE_TITLE");
		registerUser();									//register the user
		verifyAndClickPopUp();							// verify the popup
		String content = "Do you own a thermometer"+"\n"+"that shows two decimals? *";
		verifyPageTitle(content, "TRIAL_PAGE");			//verify the free trial page
		driver.findElement(By.cssSelector(selectorMap.get("NO_BUTTON"))).click();
		Thread.sleep(5000);
		verifyTrialPage("Yearly subscription + thermometer included", "TRIAL_PAGE_THERM_TITLE");	
		verifyTrialPage("590 SEK", "TRIAL_PAGE_THERM_PRICE");
		verifyTrialPage("(40% discount)", "SHIPPING_PRICE");
		List<WebElement> element = driver.findElements(By.cssSelector(selectorMap.get("BUY_BUTTON")));
		element.get(1).click();
		Thread.sleep(2000);
		verifyPageTitle("Delivery address", "PAGE_TITLE3");
		registerAddress();								//register the user address
		verifyAndClickAddressPopUp();					//verify the address on the popup window
		verifyPageTitle("Payment", "PAGE_TITLE3");		//verify payment page
		verifyPageTitle("590 SEK", "TRIAL_PAYMENT_THERM_PRICE");
		verifyPageTitle("Payment", "PAGE_TITLE3");
		payment();										//payment by using credit card
		Thread.sleep(10000);
		verifyPopupAndClick("Payment successful!");		//verify the payment success popup
		String content1 ="That is it. Now let's set up your personal experience.";
		verifyPageTitle(content1, "GET_STARTED_PAGE");	//verify GET STARTED page
	}
	
	//Method to verify the free trial page 
	private void verifyTrialPage(String content, String key){
		List<WebElement> element = driver.findElements(By.cssSelector(selectorMap.get(key)));
		String subscription_msg = element.get(1).getText();
		System.out.println("Subscription message :: "+subscription_msg);
		assertTrue("Incorrect page opened. Page header Contain ::"+subscription_msg, subscription_msg
				.equalsIgnoreCase(content));
	}

}
