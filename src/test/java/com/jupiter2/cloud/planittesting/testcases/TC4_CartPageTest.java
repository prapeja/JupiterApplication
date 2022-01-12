package com.jupiter2.cloud.planittesting.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jupiter2.cloud.planittesting.base.TestBase;
import com.jupiter2.cloud.planittesting.pages.CartPage;
import com.jupiter2.cloud.planittesting.pages.HomePage;
import com.jupiter2.cloud.planittesting.pages.ShopPage;
/**
 * 
 * @author Prapeja
 *
 */

public class TC4_CartPageTest extends TestBase{
	
	HomePage homePage;
	ShopPage shopPage;
	CartPage cartPage;
	
	@BeforeMethod
	public void setup() {
		homePage=new HomePage(driver);
		shopPage=new ShopPage(driver);
		cartPage=new CartPage(driver);
	}
	
	@DataProvider (name="AddThreeItemsToCart")
	public Object[][] itemsToCart() {		
		return new Object[][] {{"Stuffed Frog,Fluffy Bunny,Valentine Bear","2,5,3"}};
	} 
	
	@Test(dataProvider="AddThreeItemsToCart", description="Add items to cart and verify price, subtotal and total of items")
	public void verifyPriceAndSubTotal(String item, String quantity) throws InterruptedException {
		
		homePage.clickOnShop();
		String itemPrice=ShopPage.getpriceOfproducts(item);
		shopPage.buyItemstocart(item, quantity);
		shopPage.getTotalCount();
		Assert.assertEquals(shopPage.getTotalCount(), "10");
		shopPage.clickOnCartIcon(); 
		cartPage.verifyCurrentPageURL();
		cartPage.verifyItemsIncart(item);
		cartPage.verifyPriceOfEachItem(item,itemPrice);
		cartPage.verifySubTotal();
		cartPage.verifyTotal(item);
	}
}
					
					
						
				
		
	


