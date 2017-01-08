package com.aeps.testscript;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aeps.datamodel.ProductData;
import com.aeps.dataprovider.DataProviders;
import com.aeps.pages.ebay.CartPage;
import com.aeps.pages.ebay.DashBoard;
import com.aeps.pages.ebay.HomePage;
import com.aeps.pages.ebay.ProductSearchPage;
import com.aeps.selenium.framework.BaseTest;
import com.aeps.selenium.framework.Configuration;

public class AddToCartDataProvider extends BaseTest {

	private HomePage homePage;
	private DashBoard  dashBoard;
	
	@BeforeClass
	public void beforeClass() throws Exception {
		getWebDriver().navigate().to("http://Flipkart.com");
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		
		String userName = Configuration.readConfig("FlipKart_UserName");
		String password = Configuration.readConfig("FlipKart_Password");
		
		// 1 login into application
		reportLog("login into application");
		dashBoard = homePage.loginEbay(userName, password);	
	}

	@Test(dataProvider= "ProductData", dataProviderClass=DataProviders.class, description = "Desc: Add and remove product in cart using data provider")
	public void VerifyLoginSuccess(ProductData productData) throws Exception {
		
		reportLog("Open flipkart application");
		getWebDriver().navigate().to(Configuration.readConfig("FlipKartUrl"));
	
		//2. Seach item as "Samsung 4g mobiles"
		Thread.sleep(5000);
		reportLog("Seach item as "+productData.getSearchName());
		ProductSearchPage productSearchPage = dashBoard.searchProduct(productData.getSearchName());
		
		//4. Product Selection[SAMSUNG Galaxy On8 (Gold, 16 GB)]
		reportLog(" Product Selection["+productData.getProductName()+"]");
		productSearchPage.selectProduct(productData.getProductName());
		 
		//A. Share in Email handling		 
		//B. Add to Cart do
		reportLog("click on Add product to Cart");
		productSearchPage.addProduct();
		
		//5. Navigate to Cart
		reportLog("Navigate to Cart page");
		CartPage cart = dashBoard.clickCartMenu();
		
		//7. Click Remove Item / cart
		reportLog("Click Remove Item / cart to remove all item and click continue shopping");
		cart.removeProducts();
		dashBoard = cart.ClickOnContinueShoppingButton();		
	}

}

