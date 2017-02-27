package com.automation.AutomationQAChallenge.QAChallenge.Common;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.automation.AutomationQAChallenge_apis.QAChallenge_apis.conguration.Config;
import com.automation.AutomationQAChallenge_apis.QAChallenge_apis.conguration.utilites.CSVReaderUtility;

/*
*This class contains all the variables and methods with are common for all test case
*
*/
public class CommonImpl {
	
	public static WebDriver driver;
	public static String url;
	public static String email_id;
	public static String password = "password";
	public static String card_no;
	public static String expiration_date;
	public static String cvv_no;
	public static Map<String, String> selectorMap;
	public static String testdata = "webElements/selectors.csv";
	
	//Method to get the text by using css selector
	public String getTextByCSSSelector(String cssSelector){
		String text = driver.findElement(By.cssSelector(cssSelector)).getText();
		System.out.println("Text Contains :: "+text);
		return text;
	}
	
	//Method to get the text by using webelement name selector
	public String getTextByNameSelector(String name){
		String text = driver.findElement(By.name(name)).getText();
		System.out.println("Text Contains :: "+text);
		return text;
	}
	
	//Method for click event by using the css selector
	public void clickEventByCSSSelector(String cssSelector){
		driver.findElement(By.cssSelector(cssSelector)).click();
	}
	
	//Method to register the user into the system
	public void registerUser() throws InterruptedException{
		System.out.println("Email Id is :: "+email_id);
		driver.findElement(By.cssSelector(selectorMap.get("EMAIL_ID")))
			.sendKeys(email_id);
		System.out.println("Password is :: "+password);
		driver.findElement(By.cssSelector(selectorMap.get("PASSWORD")))
			.sendKeys(password);
		driver.findElement(By.cssSelector(selectorMap.get("PASSWORD1")))
			.sendKeys(password);
		Thread.sleep(2000);
		System.out.println("SIGN UP button will be clicked..");
		driver.findElement(By.cssSelector(selectorMap.get("SIGN_UP")))
			.click();
	}
	
	//Method to verfy the pop up and click pop up buthon
	public void verifyAndClickPopUp() throws InterruptedException{
		Thread.sleep(5000);
		String message = getTextByCSSSelector(selectorMap.get("POPUP"));
		assertTrue("Incorrect message is displayed on the popUp. Message Contain ::"+message, message.equalsIgnoreCase("We will now send an email to this address:"));
		List<WebElement> elements = driver.findElements(By.cssSelector(selectorMap.get("POPUP_BUTTON")));
		elements.get(1).click();
		Thread.sleep(15000);
	}
	
	//Method to verify the address on the pop up
	public void verifyAndClickAddressPopUp() throws InterruptedException{
		String message = getTextByCSSSelector(selectorMap.get("POPUP"));
		assertTrue("Incorrect message is displayed on the popUp. Message Contain ::"+message, message.equalsIgnoreCase("Once payment is complete, we will ship your"
				+ " thermometer to this address within 24 hours:"));
		String address = getTextByCSSSelector(selectorMap.get("POPUP_ADDRESS"));
		assertTrue("Incorrect address is displayed on the popUp. Message Contain ::"+address, address.contains("test user") && address.contains("ABCD") && 
				address.contains("123456") && address.contains("xyz") && address.contains("Finland"));
		List<WebElement> elements = driver.findElements(By.cssSelector(selectorMap.get("POPUP_BUTTON")));
		elements.get(1).click();
		Thread.sleep(15000);
	}
	
	//Method to verify the page
	public void verifyPageTitle(String content, String key) throws InterruptedException{
		Thread.sleep(5000);
		String pageTitle = getTextByCSSSelector(selectorMap.get(key));
		assertTrue("Incorrect page opened. Page header Contain ::"+pageTitle, pageTitle.equalsIgnoreCase(content));
	}
	
	//Method to the money by using the credit card
	public void payment() throws InterruptedException{
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(selectorMap.get("CREDIT_CARD_BUTTON"))).click();
		Thread.sleep(5000);
		driver.switchTo().frame("braintree-hosted-field-number");
		System.out.println("Cerdit Card No. is :: "+Config.CARD_NO);
		driver.findElement(By.id("credit-card-number")).sendKeys(Config.CARD_NO);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("braintree-hosted-field-expirationDate");
		System.out.println("Expiration date is :: "+Config.EXPIRATION_DATE);
		driver.findElement(By.id("expiration")).sendKeys(Config.EXPIRATION_DATE);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("braintree-hosted-field-cvv");
		System.out.println("CVV No. is :: "+Config.CVV_NO);
		driver.findElement(By.id("cvv")).sendKeys(Config.CVV_NO);
		driver.switchTo().defaultContent();
		System.out.println("PAYMENT BUTTON is clicked...");
		driver.findElement(By.cssSelector(selectorMap.get("PAY_BUTTON"))).click();
	}
	
	//method to register the user address into the system
	public void registerAddress() throws InterruptedException{
		driver.findElement(By.name("name")).sendKeys("test user");
		driver.findElement(By.name("address1")).sendKeys("ABCD");
		driver.findElement(By.name("zip")).sendKeys("123456");
		driver.findElement(By.name("city")).sendKeys("xyz");
		Select dropdown = new Select(driver.findElement(By.name("countryCode")));
		dropdown.selectByVisibleText("Finland");
		Thread.sleep(2000);
		System.out.println("NEXT BUTTOn is clicked...");
		driver.findElement(By.cssSelector(selectorMap.get("NEXT_BUTTON"))).click();
		Thread.sleep(5000);
	}
	
	//This method is to used to pay the money by using credit card
	public void cardPayment(){
		driver.switchTo().frame("braintree-dropin-frame");
		System.out.println("Cerdit Card No. is :: "+Config.CARD_NO);
		driver.findElement(By.id("credit-card-number")).sendKeys(Config.CARD_NO);
		System.out.println("Expiration date is :: "+Config.EXPIRATION_DATE);
		driver.findElement(By.id("expiration")).sendKeys(Config.EXPIRATION_DATE);
		driver.switchTo().defaultContent();
		System.out.println("PAYMENT BUTTON is clicked...");
		driver.findElement(By.cssSelector(selectorMap.get("PAY_BUTTON1"))).click();
	}
	
	//This method validate the credit card details and pay
	public void verifyAndPay() throws InterruptedException{
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(selectorMap.get("CREDIT_CARD_BUTTON"))).click();
		Thread.sleep(5000);
		driver.switchTo().frame("braintree-hosted-field-number");
		System.out.println("Cerdit Card No. is :: "+card_no);
		driver.findElement(By.id("credit-card-number")).sendKeys(card_no);
		String classValue = driver.findElement(By.id("credit-card-number")).getAttribute("class");
		assertTrue("Invalid credit card... Card No. is :: "+card_no, classValue.equalsIgnoreCase("number valid"));	
		driver.switchTo().defaultContent();
		driver.switchTo().frame("braintree-hosted-field-expirationDate");
		System.out.println("Expiration date is :: "+expiration_date);
		driver.findElement(By.id("expiration")).sendKeys(expiration_date);
		String classValue1 = driver.findElement(By.id("expiration")).getAttribute("class");
		assertTrue("Invalid expiration date... Expiration date is :: "+expiration_date, classValue1.equalsIgnoreCase("expirationDate valid"));
		driver.switchTo().defaultContent();
		driver.switchTo().frame("braintree-hosted-field-cvv");
		System.out.println("CVV No. value is :::"+cvv_no);
		driver.findElement(By.id("cvv")).sendKeys(cvv_no);
		String classValue2 = driver.findElement(By.id("cvv")).getAttribute("class");
		assertTrue("Invalid cvv no... CVV no. is :: "+cvv_no, classValue2.equalsIgnoreCase("cvv valid"));
		driver.switchTo().defaultContent();
		System.out.println("PAYMENT BUTTON is clicked...");
		driver.findElement(By.cssSelector(selectorMap.get("PAY_BUTTON"))).click();
	}
	
	public void verifyPopupAndClick(String content){
		String message = getTextByCSSSelector(selectorMap.get("POPUP"));
		assertTrue("Incorrect message is displayed on the popUp. Message Contain ::"+message, message.equalsIgnoreCase(content));
		driver.findElement(By.cssSelector(selectorMap.get("OK_BUTTON"))).click();
	}
	
	public static Map<String,String> getPath(String testData) throws IOException{
		List<String[]> data = CSVReaderUtility.getCsvDataList(testData);
		Map<String, String> resultsMap = new HashMap<String, String>();
		  for (String[] o : data) {
		    resultsMap.put(o[0], o[1]);
		  }
		  return resultsMap;
	}

}
