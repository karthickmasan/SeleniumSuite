package com.aeps.pages.ebay;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.aeps.selenium.framework.BasePage;

public class MyAccountPage extends BasePage {
	
	@FindBy(xpath="//h1[@class='fk-font-verybig']")
	private WebElement headerPersonalInfo;
	
	public MyAccountPage(WebDriver driver){
		super(driver);
	}
	
	public void verifyPersonnelInfo(String mess){
		waitForElement(headerPersonalInfo);
		Assert.assertTrue(headerPersonalInfo.getText().contains(mess) , "Account page not opened");
		
	}
}
