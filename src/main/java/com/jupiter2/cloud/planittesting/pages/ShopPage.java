package com.jupiter2.cloud.planittesting.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jupiter2.cloud.planittesting.utils.WebDriverUtilities;
/**
 * 
 * @author Prapeja
 *
 */
public class ShopPage extends WebDriverUtilities {

	public ShopPage(WebDriver driver) {
		super(driver);
	}

	WebDriverWait wait1 = new WebDriverWait(driver, 30);

	By CARTBUTTON_LOCATOR = By.xpath("//li[@id='nav-cart']");
	By actual_totalCount = By.xpath("//li[@id='nav-cart']/a/span");
	
	public void clickBuyOption(int count, String product, WebDriver driver) {
		WebElement buyButton = driver.findElement(By.xpath("//h4[text()='" + product + "']/..//a[@class='btn btn-success']"));
		for (int i = 0; i < count; i++) {
			wait1.until(ExpectedConditions.elementToBeClickable(buyButton));
			buyButton.click();
		}
	}
	
	public static String getpriceOfproducts(String products) {
		String result="";
		String[] product=products.split(",");
		for(int i=0;i<product.length;i++) {
			String getPrice="";
			getPrice = driver.findElement(By.xpath("//h4[text()='" + product[i] + "']/..//span[contains(@class,'product-price')]")).getText();
		    result=result+getPrice;
		}
		return result;
	}
	
	public String getTotalCount() {
		return getText(actual_totalCount);
	}

	public void clickOnCartIcon() {
		click(CARTBUTTON_LOCATOR);
	}
	
	public void buyItemstocart(String productName,String count) throws InterruptedException {		
		String[] products=productName.split(",");
		String[] counts=count.split(",");
		boolean found=false;
		List<WebElement> itemsNames = driver.findElements(By.xpath("//h4[contains(@class,'product-title')]"));
		for(int i=0;i<products.length;i++) {
		for (WebElement itemName : itemsNames) {
			String item = itemName.getText().trim();
			if (item.equalsIgnoreCase(products[i])) {
				clickBuyOption(Integer.parseInt(counts[i]),products[i],driver);
				found=true;
				break;
			}
		}
			if(found==false)
				throw new RuntimeException("Product not found"+productName);
		}
	}
	
	public void productprice(String productName,String productPrice) throws InterruptedException {		
		
		double double_price = 0;
		List<WebElement> itemsNames = driver.findElements(By.xpath("//h4[contains(@class,'product-title')]"));
		List<WebElement> itemsPrice = driver.findElements(By.xpath("//span[contains(@class,'product-price ng-binding')]"));
		for(int i=0;i<itemsNames.size();i++) {
			String item = itemsNames.get(i).getText().trim();
		for(int j=0;j<itemsPrice.size();j++) {
			String product_price = itemsPrice.get(i).getText();
			String product_subTotal_replace = product_price.replaceAll("[^0-9.]", "");
			double_price = Double.parseDouble(product_subTotal_replace);
		}
		System.out.println("producct and price:   "   +  item + "&" + double_price);
		}
	}	
}
