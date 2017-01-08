package com.aeps.testscript;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aeps.constant.GlobalConstant;
import com.aeps.pages.ebay.CartPage;
import com.aeps.pages.ebay.DashBoard;
import com.aeps.pages.ebay.HomePage;
import com.aeps.pages.ebay.MyAccountPage;
import com.aeps.pages.ebay.ProductSearchPage;
import com.aeps.selenium.framework.BaseTest;
import com.aeps.selenium.framework.Configuration;

public class AddToCart extends BaseTest {

	private HomePage homePage;
	
	@BeforeClass
	public void beforeClass() {
		getWebDriver().navigate().to("http://Flipkart.com");
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
	}

	@Test(description = "Desc: Add and remove product in cart")
	public void VerifyLoginSuccess() throws Exception {
		
		reportLog("Open flipkart application");
		getWebDriver().navigate().to(Configuration.readConfig("FlipKartUrl"));

		String userName = Configuration.readConfig("FlipKart_UserName");
		String password = Configuration.readConfig("FlipKart_Password");
		
		// 1 login into application
		reportLog("login into application");
		DashBoard  dashBoard = homePage.loginEbay(userName, password);	
		
		//2. Seach item as "Samsung 4g mobiles"
		Thread.sleep(8000);
		reportLog("Seach item as \"Samsung 4g mobiles\"");
		ProductSearchPage productSearchPage = dashBoard.searchProduct(Configuration.readConfig("SearchProduct"));
		
		//3. In search result displayed				
		//A. Filters - Price - reduce to Rs.25,000
		reportLog("Filters - Price - reduce to Rs.25,000");
		productSearchPage.selectPriceMaxRange(Configuration.readConfig("PriceRange"));
		 
		//B. Check-in FAssured
		Thread.sleep(4000);
		reportLog("Check-in FAssured");
		productSearchPage.checkAssuredCheckBox();
		
		//4. Product Selection[SAMSUNG Galaxy On8 (Gold, 16 GB)]
		reportLog(" Product Selection[SAMSUNG Galaxy On8 (Gold, 16 GB)]");
		productSearchPage.selectProduct(Configuration.readConfig("Product"));
		 
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
		
		//8. go in to account
		reportLog("go in to account and verify account page");
		MyAccountPage myAccountPage = dashBoard.selectMyAccount();
		myAccountPage.verifyPersonnelInfo(GlobalConstant.StringConstant.PersonnelInfo.toString());

	}

}

