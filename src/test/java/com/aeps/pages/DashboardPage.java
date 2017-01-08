package com.aeps.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aeps.constant.GlobalConstant.StringConstant;
import com.aeps.selenium.framework.BasePage;

public class DashboardPage extends BasePage {

	@FindBy(xpath = "//td[@class='welcome-text']")
	private WebElement welcomeField;

	@FindBy(xpath = "//td[@class='box']/img[@alt='user']")
	private WebElement userIcon;

	@FindBy(xpath = "//td[@class='inner-menu-normal']/a[text()='Logout']")
	private WebElement logOutButton;

	public DashboardPage(WebDriver driver) {
		super(driver);
	}

	public DashboardPage verifyWelComeMessage(String userName) {
		waitForElement(welcomeField);
		String welcomeMessage = welcomeField.getText();
		System.out.println(welcomeMessage);
		Assert.assertTrue(
				welcomeMessage.toLowerCase()
						.contains((StringConstant.Welcome.toString() + " " + userName).toLowerCase()),
				"Welcome screen not opened");
		Assert.assertTrue(isElementPresent(userIcon), "User icon not present on dashboard page");
		Assert.assertTrue(isElementPresent(logOutButton), "User icon not present on dashboard page");
		return this;
	}

	public LogOutPage logOut() {
		clickOn(logOutButton);
		return PageFactory.initElements(getWebDriver(), LogOutPage.class);
	}

}
