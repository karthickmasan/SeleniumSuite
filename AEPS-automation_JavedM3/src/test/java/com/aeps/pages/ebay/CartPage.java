package com.aeps.pages.ebay;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aeps.selenium.framework.BasePage;

public class CartPage extends BasePage {
	
	@FindBy(xpath="//a[@title='Remove Item']")
	private List<WebElement> removeItemLink;
	
	@FindBy(xpath="//button[text()='Continue Shopping']")
	private WebElement continueShoppingButton;
	
	public CartPage(WebDriver driver){
		super(driver);
	}
	
	
	public CartPage removeProducts(){
		waitForElement(removeItemLink.get(0));
		for(WebElement element :removeItemLink ){
			clickOn(element);
		}
		return this;
	}
	
	public DashBoard ClickOnContinueShoppingButton(){
		waitForElement(continueShoppingButton);
		clickOn(continueShoppingButton);
		return PageFactory.initElements(driver, DashBoard.class);
	}
}
