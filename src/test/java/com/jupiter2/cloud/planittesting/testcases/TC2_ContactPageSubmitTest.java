package com.jupiter2.cloud.planittesting.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jupiter2.cloud.planittesting.base.TestBase;
import com.jupiter2.cloud.planittesting.pages.ContactPage;
import com.jupiter2.cloud.planittesting.pages.HomePage;

/**
 * 
 * @author Prapeja
 *
 */
public class TC2_ContactPageSubmitTest extends TestBase{
	
	HomePage homePage;
	ContactPage contactPage;
	
	@BeforeMethod
	public void setup() {
		homePage=new HomePage(driver);
	    contactPage=new ContactPage(driver);
	}
	
	@Test(invocationCount = 5, description="Validate errors are gone and Run this test 5 times to ensure 100% pass rate")
	public void VerifySuccessfulSubmission() throws InterruptedException {
		homePage.clickOnContact();
		Assert.assertTrue(contactPage.validateSubmissionMessage(driver));
		
	}

}
