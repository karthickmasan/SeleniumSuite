package com.aeps.pages.ebay;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aeps.selenium.framework.BasePage;

public class HomePage extends BasePage {
	
	@FindBy(xpath="//a[@href='/account/login?ret=/']")
	private WebElement sinInLink;
	
	@FindBy(xpath="//input[contains(@class,'_2zrpKA') and @type='text']")
	private WebElement nameField;
	
	@FindBy(xpath="//input[@type='password']")
	private WebElement passwordField;
	
	@FindBy(xpath="//button/span/span[text()='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath="//*[@class='ZAtlA-']/span/span")
	private WebElement validationMsg;
	
	@FindBy(xpath="//div[@class='JAUzCh']")
	private WebElement WrongCredentialMsg;
	
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public DashBoard loginEbay(String userName, String password){
		waitForElement(sinInLink);
		clickOn(sinInLink);
		enterUserName(userName);
		enterPassword( password);
		clickSignOn();	
		waitForPageLoaded();
		return PageFactory.initElements(driver,  DashBoard.class);
	}
	
	public HomePage enterUserName(String userName){				
		waitForElement(nameField);
		inputText(nameField, userName);
		return this;
	}
	
	public void clickOnSignInLink(){
		waitForElement(sinInLink);
		clickOn(sinInLink);
	}
	
	public HomePage enterPassword(String password){
		waitForElement(passwordField);
		inputText(passwordField, password);		
		return this;
	}
	
	public HomePage clickSignOn(){
		waitForElement(loginButton);
		clickOn(loginButton);	
		return this;
	}
	
	public String getValidationMsg(){
		waitForElement(validationMsg);
		return validationMsg.getText();		
		
	}
	public String getWrongCredentialMsg(){
		waitForElement(WrongCredentialMsg);
		return WrongCredentialMsg.getText();
	}
	
}
