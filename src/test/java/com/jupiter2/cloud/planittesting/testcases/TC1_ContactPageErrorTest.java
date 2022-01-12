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
public class TC1_ContactPageErrorTest extends TestBase{
	
	HomePage homePage;
	ContactPage contactPage;

	@BeforeMethod
	public void setup() {
		homePage=new HomePage(driver);
	    contactPage=new ContactPage(driver);
	}
	
	@Test(description="Validate errors on Contact Page")
	public void validateErrors() {
		homePage.clickOnContact();
		Assert.assertTrue(contactPage.validateErrors(driver));
	}

}
