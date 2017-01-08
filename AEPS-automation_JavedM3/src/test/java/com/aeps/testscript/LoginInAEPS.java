package com.aeps.testscript;


import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aeps.constant.GlobalConstant.StringConstant;
import com.aeps.pages.HomePage;
import com.aeps.selenium.framework.BaseTest;
import com.aeps.selenium.framework.Configuration;

public class LoginInAEPS extends BaseTest {
			
	@Test(description = "Desc: Login into application")
	public void VerifyLoginSuccess() throws Exception {
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
		
		//click on login button
		reportLog("switch to login application");
		loginPage = homePage.clickLoginButton();
		
		//switch to login application
		reportLog("switch to login application");
		switchChildWindow();
		
		//login into application
		reportLog("login into application");
		dashboardPage = loginPage.loginInApplication( Configuration.readConfig("UserName"),Configuration.readConfig("Password"));
		
		//verfiy user login successfully
		reportLog("verifyWelComeMessage");
		dashboardPage.verifyWelComeMessage(Configuration.readConfig("UserFirstName"));
		
		//logout from application
		reportLog("logout from application");
		logOutPage = dashboardPage.logOut();
		
		//verify logout successfully and click continue login button
		reportLog("verify logout successfully and click continue login button");
		logOutPage.verifyLogOutMsg(StringConstant.LogoutMessage.toString().toString());		
		homePage = logOutPage.clickCntnutoLoginButton();
		
		//verfy homepage
		reportLog("verfy homepage");
		Assert.assertTrue(homePage.isLoginButton(), "Home page not displayed");
		closeBrowser();		
	}	
	
}
