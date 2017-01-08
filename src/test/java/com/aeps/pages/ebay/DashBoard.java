package com.aeps.pages.ebay;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aeps.selenium.framework.BasePage;

public class DashBoard extends BasePage {
	
	@FindBy(xpath="//input[@name='q']")
	private WebElement searchField;
	
	@FindBy(xpath="//button/*[@class='_2BhAHa']")
	private WebElement searchButton;
	
	@FindBy(xpath="//a[@href='/viewcart']")
	private WebElement cartMenu;
	
	@FindBy(xpath="//a[text()='My Account']")
	private WebElement myAccountMenu;

	public DashBoard(WebDriver driver){
		super(driver);
	}
	
	//search product
	public ProductSearchPage searchProduct(String name){
		waitForElement(searchField);
		inputText(searchField, name);
		clickOn(searchButton);
		return PageFactory.initElements(driver, ProductSearchPage.class);
	}
	
	public CartPage clickCartMenu(){
		waitForElement(cartMenu);
		clickOn(cartMenu);
		return PageFactory.initElements(driver, CartPage.class);
	}
	
	public MyAccountPage selectMyAccount(){
		waitForElement(myAccountMenu);
		clickOn(myAccountMenu);
		return PageFactory.initElements(driver, MyAccountPage.class);
		
	}
	

	
}
