package com.jupiter2.cloud.planittesting.pages;

import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.jupiter2.cloud.planittesting.utils.WebDriverUtilities;

/**
 * 
 * @author Prapeja
 *
 */

public class CartPage extends WebDriverUtilities {

	String product_name;
	ShopPage shoppage;
	public CartPage(WebDriver driver) {
		super(driver);
	}
	List<WebElement> tableRows=driver.findElements(By.xpath("//table[contains(@class,'cart-items')]/tbody/tr"));
	public static WebDriverWait wait=new WebDriverWait(driver, 100);
	By totalPrice_locator= By.xpath("//strong[@class='total ng-binding']");
	
	public void verifyCurrentPageURL() {
		String cartPageURL=CurrentURL();
		if(!(cartPageURL.contains("cart"))) {
			throw new RuntimeException("verification of cart page URL failed :  "+cartPageURL);	
		}
	}
	
	public void verifyQuantityAndItemsCart(String products,String quantity) throws InterruptedException {
		String[] quantitySP=quantity.split(",");
		String[] productsSP=products.split(",");
		String quanitiesUI=getQuantityOfProducts(quantitySP);
		String itemNames=getDetailsOfProducts(2,productsSP);
		
		if(!(quantity == quanitiesUI) && (products == itemNames))
			throw new RuntimeException("Verification of Items and quantity in cart failed : Expected products: "+products+" quanity : "+quantity+" Actual products: "+itemNames+"  quantity "+quanitiesUI);
	}
	
	public void verifyPriceOfEachItem(String products,String itemPriceBefore) throws InterruptedException {
		String[] productsSP=products.split(",");
		String itemPrices=getDetailsOfProducts(3,productsSP);
		String item_PriceAfter=itemPrices.replace("$", "");
		String[] item_PriceAfterreplaced=item_PriceAfter.split(" , ");
		String item_PriceBefore=itemPriceBefore.replace("$", "");
		String[] item_PriceBrforereplaced=item_PriceBefore.split(" , ");
		if(!(item_PriceAfterreplaced.length == item_PriceBrforereplaced.length))
			throw new RuntimeException("verification of each item price failed :  "+itemPrices+ "    "+itemPriceBefore);	
	}
	
	
	public String getQuantityOfProducts(String[] quantity) throws InterruptedException {
		String result="";	
	    Thread.sleep(3000);
		for(int i=1;i<=quantity.length;i++) {
			String quantityUI="";
			quantityUI=driver.findElement(By.xpath("//table[contains(@class,'cart-items')]/tbody/tr["+i+"]/td/input")).getAttribute("value").trim();
			result=result+quantityUI;
		}	
		return result;
	}
	
	public String getSubTotalOfProducts(int columnIndex,String[] subTotal) throws InterruptedException {
		String result = null;
		Thread.sleep(3000);
		for(int i=1;i<=subTotal.length;i++) {
			String quantityUI="";
			quantityUI=driver.findElement(By.xpath("//table[contains(@class,'cart-items')]/tbody/tr["+i+"]/td["+columnIndex+"]")).getText().trim();
			result=result+quantityUI;
		}	
		return result;
	}
	
	public String getDetailsOfProducts(int columnIndex,String[] productsSP) throws InterruptedException {
		String result = "";
		String quantityUI;
		Thread.sleep(3000);
		for(int i=1;i<=productsSP.length;i++) {
			quantityUI=driver.findElement(By.xpath("//table[contains(@class,'cart-items')]/tbody/tr["+i+"]/td["+columnIndex+"]")).getText().trim();
			result=result+quantityUI;
		}	
		return result;
	}
	
	public void verifyItemsIncart(String item_name){
		boolean found=false;
		String[] products = item_name.split(",");
		List<WebElement> itemNames = driver.findElements(By.xpath("//tr[@class='cart-item ng-scope']/td[2]"));
		for(int i=0;i<products.length;i++) {
			for (WebElement itemName : itemNames) {
				String item = itemName.getText().trim();
			if(item.contains(products[i])) {
				Assert.assertEquals(item, products[i]);
				System.out.println("name:" + products[i]);
				found=true;
				break;
			}
			if(found==false)
				throw new RuntimeException("Product not found"+item);
		}
		}
	}
	
	public String getTotalText() {
	     return	getText(totalPrice_locator);
	}
	
	public void verifyTotal(String item_name){
		double sum=0.0;
		List<WebElement> list_of_products_subtotal = driver.findElements(By.xpath("//tr[@class='cart-item ng-scope']/td[4]"));
		for (int i = 0; i < list_of_products_subtotal.size(); i++) { 
			String product_subtotal = list_of_products_subtotal.get(i).getText();
			String product_subTotal_replace = product_subtotal.replaceAll("[^0-9.]", "");
			double double_product_subTotal = Double.parseDouble(product_subTotal_replace);
			sum +=double_product_subTotal;	
		}
		System.out.println("sum:" +sum);
		String totalAmount= getTotalText().replaceAll("[^0-9.]", "");
		double amount=Double.parseDouble(totalAmount);
		System.out.println("amount on total:" + amount);
		if(sum==amount) {
			System.out.println("pass");
		}
	}
			
	public void verifySubTotal() {
		List<WebElement> list_of_products = driver.findElements(By.xpath("//tr[@class='cart-item ng-scope']/td[2]"));
		List<WebElement> list_of_products_price = driver
				.findElements(By.xpath("//tr[@class='cart-item ng-scope']/td[3]"));
		List<WebElement> list_of_products_quantity = driver.findElements(
				By.xpath("//input[contains(@class,'input-mini ng-pristine ng-valid ng-valid-number ng-valid-min')]"));
		List<WebElement> list_of_products_subtotal = driver
				.findElements(By.xpath("//tr[@class='cart-item ng-scope']/td[4]"));
		// Use of HashMap to store Products and Their prices(after conversion to Integer)
		String product_name = null;
		String product_price;
		String product_quantity;
		String product_subTotal;
		double double_product_price;
		double double_product_subTotal;
		double double_subtotal = 0;
		int int_product_quantity;
		HashMap<String, Double> map_final_products = new HashMap<String, Double>();
		for (int i = 0; i < list_of_products.size(); i++) {
			product_name = list_of_products.get(i).getText();// Iterate and fetch product name
			product_price = list_of_products_price.get(i).getText();// Iterate and fetch product price
			product_quantity = list_of_products_quantity.get(i).getAttribute("value");// Iterate and fetch product quantity														
			int_product_quantity = Integer.parseInt(product_quantity);
			product_subTotal = list_of_products_subtotal.get(i).getText();
			product_price = product_price.replaceAll("[^0-9.]", "");// Replace anything with space other than numbers
			product_subTotal = product_subTotal.replaceAll("[^0-9.]", "");
			double_product_price = Double.parseDouble(product_price);// Convert to Double
			double_product_subTotal = Double.parseDouble(product_subTotal);
			map_final_products.put(product_name, double_product_price);// Add product and price in HashMap
			System.out.println(product_name + double_product_price);
			double_subtotal = double_product_price * int_product_quantity;
			System.out.println("subtotal:  "  +  product_name + "&" + double_subtotal );
			if(!(double_product_subTotal==double_subtotal)) {
					 throw new RuntimeException("verification of subTotal failed :  "+double_product_subTotal+ "    "+double_subtotal);
			}
		}	
	}
		
}
