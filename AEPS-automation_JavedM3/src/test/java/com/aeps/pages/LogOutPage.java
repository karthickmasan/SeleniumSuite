package com.aeps.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aeps.selenium.framework.BasePage;

public class LogOutPage extends BasePage {

	@FindBy(xpath = "//*[@id='Label1']")
	private WebElement logOutMessage;
	
	@FindBy(id = "Cntnutologin")
	private WebElement cntnutoLoginButton;
	
	@FindBy(id = "btnLogin")
	private WebElement btnLogin;
	
	public LogOutPage(WebDriver driver) {
		super(driver);
	}
	
	public LogOutPage verifyLogOutMsg(String msg) throws Exception{
		waitForElement(logOutMessage);
		String message = logOutMessage.getText();
		Assert.assertTrue(message.contains(msg), "Logout message not correct");
		return this;
	}
	
	public HomePage clickCntnutoLoginButton(){
		waitForElement(cntnutoLoginButton);
		clickOn(cntnutoLoginButton);
		return PageFactory.initElements(getWebDriver(), HomePage.class);
	}
}
