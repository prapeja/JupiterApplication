package com.jupiter2.cloud.planittesting.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.jupiter2.cloud.planittesting.utils.WebDriverUtilities;
/**
 * 
 * @author Prapeja
 *
 */
public class HomePage extends WebDriverUtilities{

	public HomePage(WebDriver driver) {
		super(driver);
	}

	By SHOPPAGELINK_LOCATOR=By.xpath("//li[@id='nav-shop']/a");
	By CONTACTPAGELINK_LOCATOR=By.xpath("//li[@id='nav-contact']/a");
	
	public void clickOnContact() {
		click(CONTACTPAGELINK_LOCATOR);
	}
	
	public void clickOnShop() {
		click(SHOPPAGELINK_LOCATOR);
	}
	
}
