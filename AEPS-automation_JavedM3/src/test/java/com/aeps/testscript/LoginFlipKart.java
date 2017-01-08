package com.aeps.testscript;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aeps.pages.ebay.HomePage;
import com.aeps.selenium.framework.BaseTest;

public class LoginFlipKart extends BaseTest {

	private HomePage homePage;
	
	@BeforeClass
	public void beforeClass() {
		getWebDriver().navigate().to("http://Flipkart.com");
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
	}

	@Test(description = "Desc: Login with valid login")
	public void VerifyLoginSuccess() throws Exception {
		getWebDriver().navigate().to("http://Flipkart.com");

		//login into application
		homePage.loginEbay("karthickmasan@gmail.com", "Chennai@123");

	}

}
