package com.aeps.pages.ebay;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aeps.selenium.framework.BasePage;

public class ProductSearchPage extends BasePage {
	
	@FindBy(xpath="//div[@class='_3nSxeA']/select[@class='a_eCSK']")
	private WebElement maxPriceRangeDropDownField;
	
	@FindBy(xpath="//div[@class='_2kFyHg _1L9Eko']/label/div[@class='_1p7h2j']")
	private WebElement assuredCheckBox;
	
	@FindBy(xpath="//button[@class='_2AkmmA _3Plo8Q _19RW-r']")
	private WebElement addProduct;
	

	public ProductSearchPage(WebDriver driver){
		super(driver);
	}
	
	//select price max range
	public void selectPriceMaxRange(String maxPriceRange){
		waitForElement(maxPriceRangeDropDownField);
		selectDropDownByValue(maxPriceRangeDropDownField, maxPriceRange);
	}

	//click on assured checkbox
	public void checkAssuredCheckBox(){
		waitForElement(assuredCheckBox);
		clickOn(assuredCheckBox);
	}
	
	public ProductSearchPage selectProduct(String product) throws InterruptedException{
		String locator = "//div[text()='"+product+"']";
		Thread.sleep(3000);
		waitForElement(locator);
		clickOn(driver.findElement(By.xpath(locator)));	
		return this;
	}

	public CartPage addProduct(){
		waitForElement(addProduct);
		clickOn(addProduct);
		return PageFactory.initElements(driver, CartPage.class);
	}
	
	
}
