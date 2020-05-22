package nopCommerce.frontend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.PageGeneratorManager;
import pageObjects.FE_HomePO;
import pageObjects.FE_RegisterPO;

public class NopCommerce_FE_01_Register extends AbstractTest {
	private WebDriver driver;
	
	private FE_HomePO homePage;
	private FE_RegisterPO registerPage;

	String firstName, lastName, dateOfBirth, monthOfBirth, yearOfBirth, company, emptyData;  
	String validRandomEmail, validPassword;
	String invalidEmail, existedEmail, wrongEmail, unRegisteredEmail, wrongPassword, notMatchPassword, invalidPassword; 

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		log.info("Browser in using is: " + browserName);
		
		firstName = "Kevin";
		lastName = "Pham";
		company = "VIP Ltd. Co.";
		emptyData = "";
		dateOfBirth = "20";
		monthOfBirth = "01";
		yearOfBirth = "1989";
		
		validRandomEmail = "Kevinpham_" + randomNumber() + "@gmail.com";
		validPassword = "Abcxyz123";

		invalidEmail = "Kevinpham.invalid#123";
		existedEmail = "Kevinpham.com@gmail.com";
		wrongEmail = "Kevinpham.wrong@gmail.com";
		unRegisteredEmail = "Kevinpham.unregistered@gmail.com";
		wrongPassword = "Abcxyz123wrong";
		notMatchPassword = "AbcxyznotMatch";
		invalidPassword = "123";
		
		driver = getBrowserDriverAutoUrl(browserName, urlValue);
		homePage = PageGeneratorManager.getHomePage(driver);
	}
	
	@Test
	public void TC_01_RegisterWithEmptyData() {
		
		registerPage = (FE_RegisterPO) homePage.openDynamicHeaderPage("Register");
		verifyTrue(registerPage.isDynamicPageFormDisplayed("page registration-page"));
		
		registerPage.inputToDynamicTextbox("FirstName", emptyData);
		registerPage.inputToDynamicTextbox("LastName", emptyData);
		registerPage.inputToDynamicTextbox("Email", emptyData);
		registerPage.inputToDynamicTextbox("Password", emptyData);
		registerPage.inputToDynamicTextbox("ConfirmPassword", emptyData);
		registerPage.clickOnDynamicButton("Register");
		
		verifyTrue(registerPage.isDynamicErrorMessageDisplayed("FirstName", "First name is required."));
		verifyTrue(registerPage.isDynamicErrorMessageDisplayed("LastName", "Last name is required."));
		verifyTrue(registerPage.isDynamicErrorMessageDisplayed("Email", "Email is required."));
		verifyTrue(registerPage.isDynamicErrorMessageDisplayed("Password", "Password is required."));
		verifyTrue(registerPage.isDynamicErrorMessageDisplayed("ConfirmPassword", "Password is required."));
	}
	
	@Test
	public void TC_02_RegisterWithInvalidEmail() {
		
		registerPage.refreshRegisterPage();
		
		registerPage.inputToDynamicTextbox("FirstName", firstName);
		registerPage.inputToDynamicTextbox("LastName", lastName);
		registerPage.inputToDynamicTextbox("Email", invalidEmail);
		registerPage.inputToDynamicTextbox("Password", validPassword);
		registerPage.inputToDynamicTextbox("ConfirmPassword", validPassword);
		registerPage.clickOnDynamicButton("Register");
		
		verifyTrue(registerPage.isDynamicErrorMessageDisplayed("Email", "Wrong email"));
	}
	
	@Test
	public void TC_03_RegisterWithExistedEmail() {
		
		registerPage.refreshRegisterPage();
		
		registerPage.inputToDynamicTextbox("FirstName", firstName);
		registerPage.inputToDynamicTextbox("LastName", lastName);
		registerPage.inputToDynamicTextbox("Email", existedEmail);
		registerPage.inputToDynamicTextbox("Password", validPassword);
		registerPage.inputToDynamicTextbox("ConfirmPassword", validPassword);
		registerPage.clickOnDynamicButton("Register");
		
		verifyTrue(registerPage.isRegisterExistedEmailErrorMsgDisplayed());
		verifyEquals(registerPage.getRegisterExistedEmailErrorMsgText(), "The specified email already exists");
	}

	@Test
	public void TC_04_RegisterWithInvalidPassword() {
		
		registerPage.refreshRegisterPage();
		
		registerPage.inputToDynamicTextbox("FirstName", firstName);
		registerPage.inputToDynamicTextbox("LastName", lastName);
		registerPage.inputToDynamicTextbox("Email", validRandomEmail);
		registerPage.inputToDynamicTextbox("Password", invalidPassword);
		registerPage.inputToDynamicTextbox("ConfirmPassword", invalidPassword);
		registerPage.clickOnDynamicButton("Register");
		
		verifyEquals(registerPage.getRegisterInvalidPasswordErrorMsgText(), "Password must meet the following rules: must have at least 6 characters");

	}
	

	@Test
	public void TC_05_RegisterWithConfirmPasswordNotMatch() {
		
		registerPage.refreshRegisterPage();
		
		registerPage.inputToDynamicTextbox("FirstName", firstName);
		registerPage.inputToDynamicTextbox("LastName", lastName);
		registerPage.inputToDynamicTextbox("Email", validRandomEmail);
		registerPage.inputToDynamicTextbox("Password", validPassword);
		registerPage.inputToDynamicTextbox("ConfirmPassword", notMatchPassword);
		registerPage.clickOnDynamicButton("Register");
		
		verifyTrue(registerPage.isDynamicErrorMessageDisplayed("ConfirmPassword", "The password and confirmation password do not match."));
		
	}
	
	@Test
	public void TC_06_RegisterWithAllValidData() {

		registerPage.refreshRegisterPage();
		
		registerPage.inputToDynamicTextbox("FirstName", firstName);
		registerPage.inputToDynamicTextbox("LastName", lastName);
		registerPage.inputToDynamicTextbox("Email", validRandomEmail);
		registerPage.inputToDynamicTextbox("Password", validPassword);
		registerPage.inputToDynamicTextbox("ConfirmPassword", validPassword);
		registerPage.clickOnDynamicButton("Register");
		
		verifyTrue(registerPage.isRegisterSuccessMessageDisplayed());
		verifyEquals(registerPage.getRegisterSuccessMessageText(), "Your registration completed");
		
	}

	@AfterClass(alwaysRun=true)
	public void afterClass() { 
		closeBrowserAndDriver(driver);
		
	}

}
