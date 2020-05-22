package nopCommerce.frontend;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import commons.AbstractTest;
import pageObjects.PageGeneratorManager;
import pageObjects.FE_HomePO;
import pageObjects.FE_LoginPO;
import reportScreenshot.ExtentTestManager;

public class NopCommerce_FE_02_Login extends AbstractTest {
	private WebDriver driver;
	
	private FE_HomePO homePage;
	private FE_LoginPO loginPage;

	String registeredEmail, validPassword;
	String invalidEmail, unRegisteredEmail, wrongPassword, emptyData; 

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		
		registeredEmail = "Kevinpham.registerednopcom@gmail.com";
		validPassword = "Abcxyz123NEW";
		invalidEmail = "Kevinpham.invalid#123";
		unRegisteredEmail = "Kevinpham.unregistered@gmail.com";
		wrongPassword = "Abcxyz123wrong";
		emptyData = "";
		
		driver = getBrowserDriverAutoUrl(browserName, urlValue);
		homePage = PageGeneratorManager.getHomePage(driver);
		
	}
	
	@Test
	public void TC_01_LoginWithEmptyData(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_01_LoginWithEmptyData");
		ExtentTestManager.getTest().log(LogStatus.INFO, "TC_01_LoginWithEmptyData");
		log.info("TC_01_LoginWithEmptyData");	
		
		loginPage = (FE_LoginPO) homePage.openDynamicHeaderPage("Log in");
		verifyTrue(loginPage.isDynamicPageFormDisplayed("page login-page")); //
		
		loginPage.inputToDynamicTextbox("Email", emptyData);
		loginPage.inputToDynamicTextbox("Password", emptyData);
		loginPage.clickOnDynamicButton("Log in");
		
		verifyTrue(loginPage.isDynamicErrorMessageDisplayed("Email", "Please enter your email"));
		
	}
	
	@Test
	public void TC_02_LoginWithInvalidEmail(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_02_LoginWithInvalidEmail");
		ExtentTestManager.getTest().log(LogStatus.INFO, "TC_02_LoginWithInvalidEmail");
		log.info("TC_02_LoginWithInvalidEmail");	
		
		loginPage.refreshLoginPage();
		
		loginPage.inputToDynamicTextbox("Email", invalidEmail);
		loginPage.inputToDynamicTextbox("Password", validPassword);
		loginPage.clickOnDynamicButton("Log in");
		
		verifyTrue(loginPage.isDynamicErrorMessageDisplayed("Email", "Wrong email"));
		
	}
	
	@Test
	public void TC_03_LoginrWithUnregistedEmail(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_03_LoginrWithUnregistedEmail");
		ExtentTestManager.getTest().log(LogStatus.INFO, "TC_03_LoginrWithUnregistedEmail");
		log.info("TC_03_LoginrWithUnregistedEmail");	
		
		loginPage.refreshLoginPage();
		
		loginPage.inputToDynamicTextbox("Email", unRegisteredEmail);
		loginPage.inputToDynamicTextbox("Password", validPassword);
		loginPage.clickOnDynamicButton("Log in");
		
		verifyEquals(loginPage.getLoginUnsuccessfulErrorMsgText(), "Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");
		
	}

	@Test
	public void TC_04_LoginWithRegisteredEmailAndEmptyPassword(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_04_LoginWithRegisteredEmailAndEmptyPassword");
		ExtentTestManager.getTest().log(LogStatus.INFO, "TC_04_LoginWithRegisteredEmailAndEmptyPassword");
		log.info("TC_04_LoginWithRegisteredEmailAndEmptyPassword");	
		
		loginPage.refreshLoginPage();
		
		loginPage.inputToDynamicTextbox("Email", registeredEmail);
		loginPage.inputToDynamicTextbox("Password", emptyData);
		loginPage.clickOnDynamicButton("Log in");
		
		verifyEquals(loginPage.getLoginUnsuccessfulErrorMsgText(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");
		
	}
	
	@Test
	public void TC_05_LoginWithRegisteredEmailAndWrongPassword(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_05_LoginWithRegisteredEmailAndWrongPassword");
		ExtentTestManager.getTest().log(LogStatus.INFO, "TC_05_LoginWithRegisteredEmailAndWrongPassword");
		log.info("TC_05_LoginWithRegisteredEmailAndWrongPassword");	
		
		loginPage.refreshLoginPage();
		
		loginPage.inputToDynamicTextbox("Email", registeredEmail);
		loginPage.inputToDynamicTextbox("Password", wrongPassword);
		loginPage.clickOnDynamicButton("Log in");

		verifyEquals(loginPage.getLoginUnsuccessfulErrorMsgText(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");
		
	}
	
	@Test
	public void TC_06_LoginWithRegisteredEmailAndValidPassword(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_06_LoginWithRegisteredEmailAndValidPassword");
		ExtentTestManager.getTest().log(LogStatus.INFO, "TC_06_LoginWithRegisteredEmailAndValidPassword");
		log.info("TC_06_LoginWithRegisteredEmailAndValidPassword");	
		
		loginPage.refreshLoginPage();
		
		loginPage.inputToDynamicTextbox("Email", registeredEmail);
		loginPage.inputToDynamicTextbox("Password", validPassword);
		loginPage.clickOnDynamicButton("Log in");
		homePage = PageGeneratorManager.getHomePage(driver);
		
		verifyTrue(homePage.isDynamicHeaderPageDisplayed("My account"));
		verifyTrue(homePage.isDynamicHeaderPageDisplayed("Log out"));
		
	}

	@AfterClass(alwaysRun=true)
	public void afterClass() { 
		closeBrowserAndDriver(driver);
		
	}

}
