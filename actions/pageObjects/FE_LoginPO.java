package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;
import pageUIs.FE_LoginPageUI;

public class FE_LoginPO extends AbstractPO {
	WebDriver driver;

	public FE_LoginPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

	public void inputToLoginEmailTextbox(String randomEmail) {
		waitForElementVisibleByXpath(FE_LoginPageUI.LOGIN_EMAIL_TEXTBOX);
		sendKeysToElement(FE_LoginPageUI.LOGIN_EMAIL_TEXTBOX, randomEmail);
	}

	public void inputToLoginPasswordTextbox(String passwordValue) {
		waitForElementVisibleByXpath(FE_LoginPageUI.LOGIN_PASSWORD_TEXTBOX);
		sendKeysToElement(FE_LoginPageUI.LOGIN_PASSWORD_TEXTBOX, passwordValue);
	}

	public FE_HomePO clickOnLoginButton() { // --> Sẽ vào Home Page
		waitForElementVisibleByXpath(FE_LoginPageUI.LOGIN_BUTTON);
		clickOnElement(FE_LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getHomePage(driver);
	}

	public boolean isLoginPageDisplayed() {
		waitForElementVisibleByXpath(FE_LoginPageUI.LOGIN_FORM);
		return isElementDisplayed(FE_LoginPageUI.LOGIN_FORM);
	}

	public String getLoginUnsuccessfulErrorMsgText() {
		waitForElementVisibleByXpath(FE_LoginPageUI.LOGIN_UNSUCCESSFUL_ERROR_MGS_TEXT);
		return getTextElement(FE_LoginPageUI.LOGIN_UNSUCCESSFUL_ERROR_MGS_TEXT);
	}

	public void refreshLoginPage() {
		driver.get(driver.getCurrentUrl());
	}
	
}
