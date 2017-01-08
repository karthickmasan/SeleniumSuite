package com.aeps.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aeps.selenium.framework.BasePage;

public class HomePage extends BasePage {

	@FindBy(xpath = "//td[@class='login-boxCenter']//td[@onclick='btnTd_Clk']/input")
	private WebElement loginButton;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public LoginPage clickLoginButton(){
		waitForElement(loginButton);
		clickOn(loginButton);
		return PageFactory.initElements(getWebDriver(), LoginPage.class);		
	}
	
	public boolean isLoginButton(){
		waitForElement(loginButton);
		return isElementPresent(loginButton);
	}

}
