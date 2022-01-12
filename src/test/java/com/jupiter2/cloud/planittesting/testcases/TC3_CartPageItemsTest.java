package com.jupiter2.cloud.planittesting.testcases;

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
public class TC3_CartPageItemsTest extends TestBase{
	
	HomePage homePage;
	ShopPage shopPage;
	CartPage cartPage;
	
	@BeforeMethod
	public void setup() {
		homePage=new HomePage(driver);
		shopPage=new ShopPage(driver);
		cartPage=new CartPage(driver);
	}
	
	@DataProvider (name="AddTwoItemsToCart")
	public Object[][] itemsToCart() {		
		return new Object[][] {{"Funny Cow,Fluffy Bunny","2,1"}};
	} 
	
	@Test(dataProvider="AddTwoItemsToCart", description="Add items to cart and verify cart items")
	public void verifyCartItems(String productName,String count) throws InterruptedException {
			 
		homePage.clickOnShop();
		shopPage.buyItemstocart(productName, count);
		shopPage.clickOnCartIcon();
		cartPage.verifyQuantityAndItemsCart(productName,count);
		cartPage.verifyItemsIncart(productName);
		System.out.println(productName);
	}
}
