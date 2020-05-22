package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;
import pageUIs.FE_RegisterPageUI;

public class FE_RegisterPO extends AbstractPO{
	WebDriver driver;

	public FE_RegisterPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

	public boolean isRegisterFormDisplayed() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.REGISTER_FORM);
		return isElementDisplayed(FE_RegisterPageUI.REGISTER_FORM);
	}
	
	public void clickOnMaleRadioButton() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.MALE_RADIOBUTTON);
		clickOnElement(FE_RegisterPageUI.MALE_RADIOBUTTON);
	}

	public void inputToFirstnameTextbox(String fristNameValue) {
		waitForElementVisibleByXpath(FE_RegisterPageUI.FIRSTNAME_TEXTBOX);
		sendKeysToElement(FE_RegisterPageUI.FIRSTNAME_TEXTBOX, fristNameValue);
	}

	public void inputToLastnameTextbox(String lastNameValue) {
		waitForElementVisibleByXpath(FE_RegisterPageUI.LASTNAME_TEXTBOX);
		sendKeysToElement(FE_RegisterPageUI.LASTNAME_TEXTBOX, lastNameValue);
	}

	public void selectDateOfBirthInDropdown(String expectedDate) {
		selectItemInHTMLDropdown(FE_RegisterPageUI.DOB_DROPDOWN, expectedDate);
	}

	public void selectMonthOfBirthInDropdown(String expectedMonth) {
		selectItemInHTMLDropdown(FE_RegisterPageUI.MOB_DROPDOWN, expectedMonth);
		
	}

	public void selectYearOfBirthInDropdown(String expectedYear) {
		selectItemInHTMLDropdown(FE_RegisterPageUI.YOB_DROPDOWN, expectedYear);
	}

	public void inputToEmailTextbox(String randomEmail) {
		waitForElementVisibleByXpath(FE_RegisterPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(FE_RegisterPageUI.EMAIL_TEXTBOX, randomEmail);
	}

	public void inputToCompanyTextbox(String companyValue) {
		waitForElementVisibleByXpath(FE_RegisterPageUI.COMPANY_TEXTBOX);
		sendKeysToElement(FE_RegisterPageUI.COMPANY_TEXTBOX, companyValue);
	}

	public void inputToPasswordTextbox(String passwordValue) {
		waitForElementVisibleByXpath(FE_RegisterPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(FE_RegisterPageUI.PASSWORD_TEXTBOX, passwordValue);

	}

	public void inputToConfirmPasswordTextbox(String confirmPasswordValue) {
		waitForElementVisibleByXpath(FE_RegisterPageUI.CONFIRMPASSWORD_TEXTBOX);
		sendKeysToElement(FE_RegisterPageUI.CONFIRMPASSWORD_TEXTBOX, confirmPasswordValue);
	}

	public void clickOnRegisterButton() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.REGISTER_BUTTON);
		clickOnElement(FE_RegisterPageUI.REGISTER_BUTTON);

	}

	public boolean isRegisterSuccessMessageDisplayed() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.REGISTER_SUCCESS_MESSAGE_DISPLAYED);
		return isElementDisplayed(FE_RegisterPageUI.REGISTER_SUCCESS_MESSAGE_DISPLAYED);
	}
	
	public String getRegisterSuccessMessageText() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.REGISTER_SUCCESS_MESSAGE_TEXT);
		return getTextElement(FE_RegisterPageUI.REGISTER_SUCCESS_MESSAGE_TEXT);
	}
	
	public boolean isRegisterExistedEmailErrorMsgDisplayed() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.REGISTER_EXISTED_EMAIL_MGS_DISPLAYED);
		return isElementDisplayed(FE_RegisterPageUI.REGISTER_EXISTED_EMAIL_MGS_DISPLAYED);
	}
	
	public String getRegisterExistedEmailErrorMsgText() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.REGISTER_EXISTED_EMAIL_MGS_TEXT);
		return getTextElement(FE_RegisterPageUI.REGISTER_EXISTED_EMAIL_MGS_TEXT);
	}
	
	public String getRegisterInvalidPasswordErrorMsgText_01() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.INVALID_PASSWORD_ERROR_MESSAGE_TEXT_1);
		return getTextElement(FE_RegisterPageUI.INVALID_PASSWORD_ERROR_MESSAGE_TEXT_1);
	}
	
	public String getRegisterInvalidPasswordErrorMsgText_02() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.INVALID_PASSWORD_ERROR_MESSAGE_TEXT_2);
		return getTextElement(FE_RegisterPageUI.INVALID_PASSWORD_ERROR_MESSAGE_TEXT_2);
	}
	
	public String getRegisterInvalidPasswordErrorMsgText() {
		return getRegisterInvalidPasswordErrorMsgText_01() + " " + getRegisterInvalidPasswordErrorMsgText_02();
	}
	
	public FE_HomePO clickOnLogoutLink() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.LOGOUT_LINK);
		clickOnElement(FE_RegisterPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getHomePage(driver);
	}
	
	public boolean isFirstnameErrorMessageDisplayed() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.FIRSTNAME_ERROR_MESSAGE_DISPLAYED);
		return isElementDisplayed(FE_RegisterPageUI.FIRSTNAME_ERROR_MESSAGE_DISPLAYED);
	}
	
	public String getFirstnameErrorText() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.FIRSTNAME_ERROR_MESSAGE_TEXT);
		return getTextElement(FE_RegisterPageUI.FIRSTNAME_ERROR_MESSAGE_TEXT);
	}
	
	public boolean isLastnameErrorMessageDisplayed() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.LASTNAMEE_ERROR_MESSAGE_DISPLAYED);
		return isElementDisplayed(FE_RegisterPageUI.LASTNAMEE_ERROR_MESSAGE_DISPLAYED);
	}
	
	public String getLastnameErrorMessageText() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.LASTNAME_ERROR_MESSAGE_TEXT);
		return getTextElement(FE_RegisterPageUI.LASTNAME_ERROR_MESSAGE_TEXT);
	}
	
	public boolean isEmailErrorMessageDisplayed() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.EMAIL_ERROR_MESSAGE_DISPLAYED);
		return isElementDisplayed(FE_RegisterPageUI.EMAIL_ERROR_MESSAGE_DISPLAYED);
	}
	
	public String getEmailErrorMessageText() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.EMAIL_ERROR_MESSAGE_TEXT);
		return getTextElement(FE_RegisterPageUI.EMAIL_ERROR_MESSAGE_TEXT);
	}
	
	public boolean isPasswordErrorMessageDisplayed() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.PASSWORD_ERROR_MESSAGE_DISPLAYED);
		return isElementDisplayed(FE_RegisterPageUI.PASSWORD_ERROR_MESSAGE_DISPLAYED);
	}
	
	public String getPasswordErrorMessageText() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.PASSWORD_ERROR_MESSAGE_TEXT);
		return getTextElement(FE_RegisterPageUI.PASSWORD_ERROR_MESSAGE_TEXT);
	}
	
	public boolean isConfirmPasswordErrorMessageDisplayed() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.CONFIRMPASSWORD_ERROR_MESSAGE_DISPLAYED);
		return isElementDisplayed(FE_RegisterPageUI.CONFIRMPASSWORD_ERROR_MESSAGE_DISPLAYED);
	}

	public String getConfirmPasswordErrorMessageText() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.CONFIRMPASSWORD_ERROR_MESSAGE_TEXT);
		return getTextElement(FE_RegisterPageUI.CONFIRMPASSWORD_ERROR_MESSAGE_TEXT);
	}

	public boolean isRegisterPageDisplayed() {
		waitForElementVisibleByXpath(FE_RegisterPageUI.REGISTER_FORM);
		return isElementDisplayed(FE_RegisterPageUI.REGISTER_FORM);
	}

	public void refreshRegisterPage() {
		driver.get(driver.getCurrentUrl());
	}

}
