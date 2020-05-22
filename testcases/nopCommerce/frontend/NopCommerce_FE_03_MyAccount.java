package nopCommerce.frontend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.FE_HomePO;
import pageObjects.FE_LoginPO;
import pageObjects.FE_MyAccountPO;
import pageObjects.FE_ProductDetailsPO;
import pageObjects.FE_ProductReviewPO;
import pageObjects.FE_RegisterPO;
import pageObjects.PageGeneratorManager;

public class NopCommerce_FE_03_MyAccount extends AbstractTest {
	private WebDriver driver;
	
	private FE_HomePO homePage;
	private FE_RegisterPO registerPage;
	private FE_MyAccountPO myAccountPage;
	private FE_LoginPO loginPage;
	private FE_ProductDetailsPO productDetailsPage;
	private FE_ProductReviewPO productReviewPage;

	String firstName, lastName, validRandomEmail, validPassword;
	String gender, dateOfBirth, monthOfBirth, yearOfBirth, company;
	String address1, address2, city, state, country, zipCode, phoneNumber, faxNumber;  
	String newPassword;  

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		log.info("Browser in using is: " + browserName);
		
		firstName = "Kevin";
		lastName = "Pham";
		validRandomEmail = "Kevinpham_" + randomNumber() + "@gmail.com";
		validPassword = "Abcxyz123";
		
		gender = "Male";
		dateOfBirth = "20";
		monthOfBirth = "January";
		yearOfBirth = "1989";
		company = "VIP Ltd. Co.";
		
		address1 = "123 ABC";
		address2 = "789 XYZ";
		city = "New York City";
		state = "New York";
		country = "United States";
		zipCode = "543432";
		phoneNumber = "0123456789"; 
		faxNumber = "9876543210"; 
		
		newPassword = "Abcxyz123NEW";
		
		driver = getBrowserDriverAutoUrl(browserName, urlValue);
		homePage = PageGeneratorManager.getHomePage(driver);
		
		registerPage = (FE_RegisterPO) homePage.openDynamicHeaderPage("Register");
		verifyTrue(registerPage.isDynamicPageFormDisplayed("page registration-page"));

		registerPage.inputToDynamicTextbox("FirstName", firstName);
		registerPage.inputToDynamicTextbox("LastName", lastName);
		registerPage.inputToDynamicTextbox("Email", validRandomEmail);
		registerPage.inputToDynamicTextbox("Password", validPassword);
		registerPage.inputToDynamicTextbox("ConfirmPassword", validPassword);
		registerPage.clickOnDynamicButton("Register");
		
		verifyTrue(registerPage.isRegisterSuccessMessageDisplayed());
		verifyEquals(registerPage.getRegisterSuccessMessageText(), "Your registration completed");
		
		myAccountPage = (FE_MyAccountPO) registerPage.openDynamicHeaderPage("My account");
		verifyTrue(myAccountPage.isDynamicPageFormDisplayed("master-wrapper-content"));
	}
	
	@Test
	public void TC_01_UpdateCustomerInfo() {
		
		myAccountPage.clickOnDynamicMyAccountNavigationLink("Customer info");
		verifyEquals(myAccountPage.getMyAccountPageTitle(), "My account - Customer info");
		
		myAccountPage.clickOnDynamicRadioButton("gender-male");
		myAccountPage.inputToDynamicTextbox("FirstName", firstName);
		myAccountPage.inputToDynamicTextbox("LastName", lastName);
		myAccountPage.selectItemInDynamicDropdown("DateOfBirthDay", dateOfBirth);
		myAccountPage.selectItemInDynamicDropdown("DateOfBirthMonth", monthOfBirth);
		myAccountPage.selectItemInDynamicDropdown("DateOfBirthYear", yearOfBirth);
		myAccountPage.inputToDynamicTextbox("Email", validRandomEmail);
		myAccountPage.inputToDynamicTextbox("Company", company);
		myAccountPage.clickOnDynamicButton("Save");
		
		verifyTrue(myAccountPage.isRadioButtonSelected("gender-male"));
		verifyEquals(myAccountPage.getDynamicTextboxAttributeValueLive("value", "FirstName"), firstName);
		verifyEquals(myAccountPage.getDynamicTextboxAttributeValueLive("value", "LastName"), lastName);
		verifyTrue(myAccountPage.isSelectedItemInDynamicDropdownDisplayed("DateOfBirthDay", dateOfBirth));
		verifyTrue(myAccountPage.isSelectedItemInDynamicDropdownDisplayed("DateOfBirthMonth", monthOfBirth));
		verifyTrue(myAccountPage.isSelectedItemInDynamicDropdownDisplayed("DateOfBirthYear", yearOfBirth));
		verifyEquals(myAccountPage.getDynamicTextboxAttributeValueLive("value", "Email"), validRandomEmail);
		verifyEquals(myAccountPage.getDynamicTextboxAttributeValueLive("value", "Company"), company);
		
	}
	
	@Test
	public void TC_02_UpdateCustomerAddresses() {
		
		myAccountPage.clickOnDynamicMyAccountNavigationLink("Addresses");
		verifyEquals(myAccountPage.getMyAccountPageTitle(), "My account - Addresses");
		
		myAccountPage.clickOnDynamicButton("Add new");
		myAccountPage.inputToDynamicTextbox("Address_FirstName", firstName);
		myAccountPage.inputToDynamicTextbox("Address_LastName", lastName);
		myAccountPage.inputToDynamicTextbox("Address_Email", validRandomEmail);
		myAccountPage.inputToDynamicTextbox("Address_Company", company);
		myAccountPage.selectItemInDynamicDropdown("Address.CountryId", country);
		myAccountPage.selectItemInDynamicDropdown("Address.StateProvinceId", state);
		myAccountPage.inputToDynamicTextbox("Address_City", city);
		myAccountPage.inputToDynamicTextbox("Address_Address1", address1);
		myAccountPage.inputToDynamicTextbox("Address_Address2", address2);
		myAccountPage.inputToDynamicTextbox("Address_ZipPostalCode", zipCode);
		myAccountPage.inputToDynamicTextbox("Address_PhoneNumber", phoneNumber);
		myAccountPage.inputToDynamicTextbox("Address_FaxNumber", faxNumber);
		myAccountPage.clickOnDynamicButton("Save");
		
		verifyTrue(myAccountPage.isAccountInfoSectionDisplayed());
		verifyEquals(myAccountPage.getTextDynamicAccountInfo("name"), firstName + " " + lastName);
		verifyEquals(myAccountPage.getTextDynamicAccountInfo("email"), "Email: " + validRandomEmail);
		verifyEquals(myAccountPage.getTextDynamicAccountInfo("phone"), "Phone number: " + phoneNumber);
		verifyEquals(myAccountPage.getTextDynamicAccountInfo("fax"), "Fax number: " + faxNumber);
		verifyEquals(myAccountPage.getTextDynamicAccountInfo("address1"), address1);
		verifyEquals(myAccountPage.getTextDynamicAccountInfo("city-state-zip"), city + ", " + state + ", " + zipCode);
		verifyEquals(myAccountPage.getTextDynamicAccountInfo("country"), country);

	}
	
	@Test
	public void TC_03_ChangePassword() {
		
		myAccountPage.clickOnDynamicMyAccountNavigationLink("Change password");
		verifyEquals(myAccountPage.getMyAccountPageTitle(), "My account - Change password");
		
		myAccountPage.inputToDynamicTextbox("OldPassword", validPassword);
		myAccountPage.inputToDynamicTextbox("NewPassword", newPassword);
		myAccountPage.inputToDynamicTextbox("ConfirmNewPassword", newPassword);
		
		myAccountPage.clickOnDynamicButton("Change password");
		verifyEquals(myAccountPage.getResultMessageText(), "Password was changed");
		
		homePage = (FE_HomePO) myAccountPage.openDynamicHeaderPage("Log out");
		verifyTrue(homePage.isDynamicHeaderPageDisplayed("Log in"));
		
		loginPage = (FE_LoginPO) homePage.openDynamicHeaderPage("Log in");
		verifyTrue(loginPage.isDynamicPageFormDisplayed("page login-page"));

		loginPage.inputToDynamicTextbox("Email", validRandomEmail);
		loginPage.inputToDynamicTextbox("Password", validPassword);
		loginPage.clickOnDynamicButton("Log in");
		verifyEquals(loginPage.getLoginUnsuccessfulErrorMsgText(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");
		
		loginPage.refreshLoginPage();
		loginPage.inputToDynamicTextbox("Email", validRandomEmail);
		loginPage.inputToDynamicTextbox("Password", newPassword);
		loginPage.clickOnDynamicButton("Log in");
		
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isDynamicHeaderPageDisplayed("My account"));
		verifyTrue(homePage.isDynamicHeaderPageDisplayed("Log out"));
	}
	
	
	@Test
	public void TC_04_ProductReviews() {
		
		homePage.clickOnDynamicAddToCartButton("Apple MacBook Pro 13-inch");
		productDetailsPage = PageGeneratorManager.getProductDetailsPage(driver);
		verifyTrue(productDetailsPage.isDynamicPageFormDisplayed("page product-details-page"));
		verifyEquals(productDetailsPage.getProductDetailsName(), "Apple MacBook Pro 13-inch");
		
		productDetailsPage.clickOnDynamicProductReviewLink("Add your review");
		productReviewPage = PageGeneratorManager.getProductReviewPage(driver);
		verifyTrue(productReviewPage.isDynamicPageFormDisplayed("page product-reviews-page"));
		verifyEquals(productReviewPage.getProductDetailsName(), "Apple MacBook Pro 13-inch");
		
		productReviewPage.inputToDynamicTextbox("AddProductReview_Title", "Review for MacBook");
		productReviewPage.inputToDynamicTextarea("AddProductReview_ReviewText", "This is good MacBook from Apple");
		productReviewPage.clickOnDynamicRadioButton("addproductrating_4");
		productReviewPage.clickOnDynamicButton("Submit review");
		verifyEquals(productReviewPage.getResultMessageText(), "Product review is successfully added.");
		
		myAccountPage = (FE_MyAccountPO) productReviewPage.openDynamicHeaderPage("My account");
		verifyTrue(myAccountPage.isDynamicPageFormDisplayed("master-wrapper-content"));
		
		myAccountPage.clickOnDynamicMyAccountNavigationLink("My product reviews");
		verifyEquals(myAccountPage.getMyAccountPageTitle(), "My account - My product reviews");
		verifyTrue(myAccountPage.isDynamicProductReviewedNameDisplayed("Apple MacBook Pro 13-inch"));
	
	}
	
	@AfterClass(alwaysRun=true)
	public void afterClass() { 
		closeBrowserAndDriver(driver);
		
	}

}
