package com.jupiter2.cloud.planittesting.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.jupiter2.cloud.planittesting.base.TestBase;
import com.jupiter2.cloud.planittesting.utils.WebDriverUtilities;
/**
 * 
 * @author Prapeja
 *
 */
public class ContactPage extends WebDriverUtilities  {

	WebDriver driver;
	TestBase testBase;
	public ContactPage(WebDriver driver) {
		super(driver);
	}
	By SUBMITBUTTON_LOCATOR=By.xpath("//a[contains(text(),'Submit')]");
	By HEADERERRORMESSAGE_LOCATOR=By.xpath("//*[@id='header-message']/div");
	By FORNAMEERRORTEXT_LOCATOR=By.xpath("//*[@id='forename-err']");
	By EMAILERRORTEXT_LOCATOR=By.xpath("//*[@id='email-err']");
	By MESSAGEBOXERRORTEXT_LOCATOR=By.xpath("//*[@id='header-message']/div");
	By FORNAME_LOCATOR=By.xpath("//*[@id='forename']");
	By EMAIL_LOCATOR=By.xpath("//*[@id='email']");
	By MESSAGE_LOCATOR=By.xpath("//*[@id='message']");
	By VALIDHEADERMESSAGE_LOCATOR=By.xpath("//*[@id='header-message']/div");
	By SUBMISSIONMESSAGE_LOCATOR=By.xpath("//*[contains(@class,'alert')]");
	
	
	String randomAlphabatic=RandomStringUtils.randomAlphabetic(8);
	String randomAlphNumeric=RandomStringUtils.randomAlphanumeric(8);
	
	public void clicksubmit() {
		click(SUBMITBUTTON_LOCATOR);
	}
	
	public boolean validateHeaderMessageErrors() {
		validateText(HEADERERRORMESSAGE_LOCATOR,"We welcome your feedback - but we won't get it unless you complete the form correctly.");
		return validateText(HEADERERRORMESSAGE_LOCATOR,"We welcome your feedback - but we won't get it unless you complete the form correctly.");
	}
	public boolean validateFornameErrorText() {
		validateText(FORNAMEERRORTEXT_LOCATOR,"We welcome your feedback - but we won't get it unless you complete the form correctly.");
		return validateText(FORNAMEERRORTEXT_LOCATOR,"We welcome your feedback - but we won't get it unless you complete the form correctly.");
	}
	public boolean validateEmailErrorText() {
		validateText(EMAILERRORTEXT_LOCATOR,"We welcome your feedback - but we won't get it unless you complete the form correctly.");
		return validateText(EMAILERRORTEXT_LOCATOR,"We welcome your feedback - but we won't get it unless you complete the form correctly.");
	}
	public boolean validateMessageBoxErrorText() {
		validateText(MESSAGEBOXERRORTEXT_LOCATOR,"We welcome your feedback - but we won't get it unless you complete the form correctly.");
		return validateText(MESSAGEBOXERRORTEXT_LOCATOR,"We welcome your feedback - but we won't get it unless you complete the form correctly.");
	}
	
	public void enterForname() {
		sendKeys(FORNAME_LOCATOR,randomAlphabatic);
	}
	public void enterEmail() {
		sendKeys(EMAIL_LOCATOR,randomAlphNumeric+"@planit.net.au");
	}
	public void enterMessage() {
		sendKeys(MESSAGE_LOCATOR,randomAlphNumeric);
	}
	
	public boolean validateHeaderMessage() {
		return validateText(VALIDHEADERMESSAGE_LOCATOR,"We welcome your feedback - tell it how it is.");
	}
	
	public boolean fornameErrorIsDisplayed() {
		return isElementEnabled(FORNAMEERRORTEXT_LOCATOR);
	}
	
	public boolean successMessageAfterSubmit() {
		return validateText(SUBMISSIONMESSAGE_LOCATOR,"Thanks "+randomAlphabatic+", we appreciate your feedback.");
	}
	public void waitTillPageLoad() {
		waitForVisibilityOfElement(SUBMISSIONMESSAGE_LOCATOR);
	}	
	
	public boolean validateErrors(WebDriver driver) {
		clicksubmit();
		validateHeaderMessageErrors();
		validateFornameErrorText();
		validateEmailErrorText();
		validateMessageBoxErrorText();
		enterForname();
		enterEmail();
		enterMessage();
		validateHeaderMessage();
		
		return validateHeaderMessage(); 
	}
	
	public boolean validateSubmissionMessage(WebDriver driver) throws InterruptedException {
		
		validateHeaderMessage();
		enterForname();
		enterEmail();
		enterMessage(); 
		clicksubmit();
		Thread.sleep(10000);
	 return successMessageAfterSubmit();
	}
}
