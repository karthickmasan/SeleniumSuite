package com.aeps.testscript;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aeps.constant.GlobalConstant;
import com.aeps.pages.ebay.HomePage;
import com.aeps.selenium.framework.BaseTest;

public class InValidLoginVallidation extends BaseTest {

	private HomePage homePage;

	@BeforeClass
	public void beforeClass() {
		getWebDriver().navigate().to("http://Flipkart.com");
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
	}
	

	@Test(description = "Desc: verify login validation")
	public void VerifyLoginValidation() throws Exception {

		// click on login link
		reportLog(" click on login link");
		homePage.clickOnSignInLink();

		// click on sign in button
		reportLog(" click on sign in button");
		homePage.clickSignOn();

		reportLog("verify validation message "+GlobalConstant.StringConstant.UserValidationMsg.toString());
		Assert.assertTrue(
				homePage.getValidationMsg().contains(GlobalConstant.StringConstant.UserValidationMsg.toString()));

		// enter invalid user name
		reportLog("enter invalid user name");
		homePage.enterUserName("WrongMail@gmail.com");

		// click on sign in button
		reportLog("click on sign in button");
		homePage.clickSignOn();

		// verify validation message
		reportLog("verify validation message "+GlobalConstant.StringConstant.PasswordValidation.toString());
		System.out.println(homePage.getValidationMsg());
		Assert.assertTrue(
				homePage.getValidationMsg().contains(GlobalConstant.StringConstant.PasswordValidation.toString()));

		// enter invalid user name
		reportLog("enter invalid user name");
		homePage.enterUserName("WrongMail@gmail.com");

		reportLog("enter wrong password");
		homePage.enterPassword("11111111");

		// click on sign in button
		reportLog("click on sign in button");
		homePage.clickSignOn();

		// verify validation message
		reportLog("verify validation message "+GlobalConstant.StringConstant.UserValiation2.toString());
		Assert.assertTrue(
				homePage.getWrongCredentialMsg().contains(GlobalConstant.StringConstant.UserValiation2.toString()));

	}
}
