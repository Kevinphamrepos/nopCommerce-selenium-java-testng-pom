package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;
import pageUIs.FE_HomePageUI;

public class FE_HomePO extends AbstractPO {
	WebDriver driver;

	public FE_HomePO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

	public FE_RegisterPO clickOnRegisterLink() {
		waitForElementVisibleByXpath(FE_HomePageUI.REGISTER_LINK);
		clickOnElement(FE_HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public FE_LoginPO clickOnLoginLink() {
		waitForElementVisibleByXpath(FE_HomePageUI.LOGIN_LINK);
		clickOnElement(FE_HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}

	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisibleByXpath(FE_HomePageUI.MYACCOUNT_LINK);
		return isElementDisplayed(FE_HomePageUI.MYACCOUNT_LINK);
	}

	public boolean isLogoutLinkDisplayed() {
		waitForElementVisibleByXpath(FE_HomePageUI.LOGOUT_LINK);
		return isElementDisplayed(FE_HomePageUI.LOGOUT_LINK);
	}

	public boolean isRegisterLinkUndisplayed() {
		waitForElementInvisibleByXpath(FE_HomePageUI.REGISTER_LINK);
		return isElementDisplayed(FE_HomePageUI.REGISTER_LINK);
	}

	public boolean isLoginLinkUndisplayed() {
		waitForElementInvisibleByXpath(FE_HomePageUI.LOGIN_LINK);
		return isElementDisplayed(FE_HomePageUI.LOGIN_LINK);
	}

	
}
