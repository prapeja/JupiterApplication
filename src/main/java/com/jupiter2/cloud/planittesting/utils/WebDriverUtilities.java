package com.jupiter2.cloud.planittesting.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtilities {
		
		public static WebDriver driver;
		
		public WebDriverUtilities(WebDriver driver) {
			this.driver=driver;
		}
		
		public void click(By prop) {
			 driver.findElement(prop).click();
		}
		
		public String getText(By prop) {
			return driver.findElement(prop).getText();
		}
		
		public String getAttribute(By prop, String attribute) {
			return driver.findElement(prop).getAttribute(attribute);
		}
		
		public void clear(By prop) {
			driver.findElement(prop).clear();
		}
		
		public void sendKeys(By prop, String Text) {
			driver.findElement(prop).sendKeys(Text);
		}
		public void sendKeysInteger(By prop,String code) {
			driver.findElement(prop).sendKeys(code);
		}
		
		public void submit(By prop) {
			driver.findElement(prop).submit();
		}
		
		public boolean isElementEnabled(By prop) {
			return driver.findElement(prop).isEnabled();
		}
		
		public boolean isElementDisplayed(By prop) {
			return driver.findElement(prop).isDisplayed();
		}
		
		public boolean isElementSelected(By prop) {
			return driver.findElement(prop).isSelected();
		}
		
		public void selectValueFromDropDown(By prop, String value) {
			new Select(driver.findElement(prop)).selectByValue(value);
		}
		
		public void selectIndexFromDropDown(By prop, int state) {
			new Select(driver.findElement(prop)).selectByIndex(state);
		}
		
		public void selectTextFromDropDown(By prop, String text) {
			new Select(driver.findElement(prop)).selectByVisibleText(text);
		}
		
		public void deSelectAll(By prop) {
			new Select(driver.findElement(prop)).deselectAll();
		}
		
		public void mouseHover(By prop) {
			new Actions(driver).moveToElement(driver.findElement(prop)).perform();
		}
		
		public String getTitle(By prop) {
			return driver.getTitle();
		}
		
		public boolean validateText(By prop,String expectedText) {
			String actualText=getText(prop);
			if(actualText.equals(expectedText)) {
				return true;
			}else
				return false;
		}
		
		public void waitForVisibilityOfElement(By locator) {
			WebDriverWait wait = new WebDriverWait(driver, 5000000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			
		}
		
		public void waitElementToBeClickable(By locator) {
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		}
		
        public String CurrentURL() {
			return driver.getCurrentUrl();
		}
		

	}


