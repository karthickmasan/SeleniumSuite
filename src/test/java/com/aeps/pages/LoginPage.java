package com.aeps.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aeps.selenium.framework.BasePage;

public class LoginPage  extends BasePage {

	@FindBy(id = "txtName")
	private WebElement userInputObj;
	
	@FindBy(id = "txtPassword")
	private WebElement passwordInputObj;
	
	@FindBy(id = "btnLogin")
	private WebElement btnLogin;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public DashboardPage loginInApplication(String userName,String password) throws Exception{
		waitForElement(userInputObj);
		inputText(userInputObj,userName);
		inputText(passwordInputObj, password);
		clickOn(btnLogin);
		return PageFactory.initElements(getWebDriver(), DashboardPage.class);
	}


}
