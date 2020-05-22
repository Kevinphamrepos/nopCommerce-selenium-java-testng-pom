package nopCommerce.frontend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.PageGeneratorManager;
import pageObjects.FE_HomePO;
import pageObjects.FE_LoginPO;
import pageObjects.FE_SearchPO;

public class NopCommerce_FE_04_SearchFunctionality extends AbstractTest {
	private WebDriver driver;
	
	private FE_HomePO homePage;
	private FE_LoginPO loginPage;
	private FE_SearchPO searchPage;
	
	String registeredEmail, validPassword;
	String emptyData, notExistedData, relativeProductName, absoluteProductName;

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		
		registeredEmail = "Kevinpham.registerednopcom@gmail.com";
		validPassword = "Abcxyz123NEW";
		emptyData = "";
		notExistedData = "MacBook Pro 2050";
		relativeProductName = "Lenovo";
		absoluteProductName = "ThinkPad X1 Carbon";
		
		driver = getBrowserDriverAutoUrl(browserName, urlValue);
		homePage = PageGeneratorManager.getHomePage(driver);
		
		loginPage = (FE_LoginPO) homePage.openDynamicHeaderPage("Log in");
		verifyTrue(loginPage.isDynamicPageFormDisplayed("page login-page"));
		
		loginPage.inputToDynamicTextbox("Email", registeredEmail);
		loginPage.inputToDynamicTextbox("Password", validPassword);
		loginPage.clickOnDynamicButton("Log in");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isDynamicHeaderPageDisplayed("My account"));
		verifyTrue(homePage.isDynamicHeaderPageDisplayed("Log out"));
		
	}
	
	@Test
	public void TC_01_AddToWishlist() {

		homePage.openDynamicFooterPage("Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
		verifyTrue(searchPage.isDynamicPageFormDisplayed("page search-page"));
		
		searchPage.inputToDynamicTextbox("q", emptyData);
		searchPage.clickOnPageBodySearchButton();
		
		verifyEquals(searchPage.getSearchResultMessageText(), "Search term minimum length is 3 characters");
		
	}
	
	@Test
	public void TC_02_SearchWithNotExistedData() {
		
		searchPage.refreshCurrentPage();
		
		searchPage.inputToDynamicTextbox("q", notExistedData);
		searchPage.clickOnPageBodySearchButton();
		verifyEquals(searchPage.getSearchResultMessageText(), "No products were found that matched your criteria.");
		
	}
	
	@Test
	public void TC_03_SearchWithRelativeProductName() {
		
		searchPage.refreshCurrentPage();
		
		searchPage.inputToDynamicTextbox("q", relativeProductName);
		searchPage.clickOnPageBodySearchButton();
		verifyTrue(searchPage.isSearchResultProductTitleDisplayedOnPageAsExpectation(relativeProductName));
		
	}
	
	@Test
	public void TC_04_SearchWithAbsoluteProductName() {
		
		searchPage.refreshCurrentPage();
		
		searchPage.inputToDynamicTextbox("q", absoluteProductName);
		searchPage.clickOnPageBodySearchButton();
		verifyTrue(searchPage.isSearchResultProductTitleDisplayedOnPageAsExpectation(absoluteProductName));
		
	}
	
	@Test
	public void TC_05_AdvanceSearchWithParentCategories() {
		
		searchPage.refreshCurrentPage();
		
		searchPage.inputToDynamicTextbox("q", "Apple MacBook Pro");
		searchPage.clickToCheckDynamicCheckbox("adv");
		searchPage.selectItemInDynamicDropdown("cid", "Computers");
		searchPage.clickOnPageBodySearchButton();
		verifyEquals(searchPage.getSearchResultMessageText(), "No products were found that matched your criteria.");
		
	}
	
	@Test
	public void TC_06_AdvanceSearchWithSubCategories() {
		
		searchPage.refreshCurrentPage();
		
		searchPage.inputToDynamicTextbox("q", "Apple MacBook Pro");
		searchPage.clickToCheckDynamicCheckbox("adv");
		searchPage.selectItemInDynamicDropdown("cid", "Computers");
		searchPage.clickToCheckDynamicCheckbox("isc");
		searchPage.clickOnPageBodySearchButton();
		verifyTrue(searchPage.isSearchResultProductTitleDisplayedOnPageAsExpectation("Apple MacBook Pro"));
		
	}
	
	@Test
	public void TC_07_AdvanceSearchWithIncorrectManufacturer() {
		
		searchPage.refreshCurrentPage();
		
		searchPage.inputToDynamicTextbox("q", "Apple MacBook Pro");
		searchPage.clickToCheckDynamicCheckbox("adv");
		searchPage.selectItemInDynamicDropdown("cid", "Computers");
		searchPage.clickToCheckDynamicCheckbox("isc");
		searchPage.selectItemInDynamicDropdown("mid", "HP");
		searchPage.clickOnPageBodySearchButton();
		verifyEquals(searchPage.getSearchResultMessageText(), "No products were found that matched your criteria.");
		
	}
	
	@Test
	public void TC_08_AdvanceSearchWithCorrectManufacturer() {
		
		searchPage.refreshCurrentPage();
		
		searchPage.inputToDynamicTextbox("q", "Apple MacBook Pro");
		searchPage.clickToCheckDynamicCheckbox("adv");
		searchPage.selectItemInDynamicDropdown("cid", "Computers");
		searchPage.clickToCheckDynamicCheckbox("isc");
		searchPage.selectItemInDynamicDropdown("mid", "Apple");
		searchPage.clickOnPageBodySearchButton();
		verifyTrue(searchPage.isSearchResultProductTitleDisplayedOnPageAsExpectation("Apple MacBook Pro"));
		
	}
	
	@Test
	public void TC_09_AdvanceSearchWithPriceRange() {
		
		searchPage.refreshCurrentPage();
		
		searchPage.inputToDynamicTextbox("q", "Apple MacBook Pro");
		searchPage.clickToCheckDynamicCheckbox("adv");
		searchPage.selectItemInDynamicDropdown("cid", "Computers");
		searchPage.clickToCheckDynamicCheckbox("isc");
		searchPage.selectItemInDynamicDropdown("mid", "Apple");
		searchPage.inputToDynamicTextbox("pf", "1000");
		searchPage.inputToDynamicTextbox("pt", "2000");
		searchPage.clickOnPageBodySearchButton();
		verifyTrue(searchPage.isSearchResultProductTitleDisplayedOnPageAsExpectation("Apple MacBook Pro"));
		
	}
	
	@Test
	public void TC_10_AdvanceSearchWithLowerPriceRange() {
		
		searchPage.refreshCurrentPage();
		
		searchPage.inputToDynamicTextbox("q", "Apple MacBook Pro");
		searchPage.clickToCheckDynamicCheckbox("adv");
		searchPage.selectItemInDynamicDropdown("cid", "Computers");
		searchPage.clickToCheckDynamicCheckbox("isc");
		searchPage.selectItemInDynamicDropdown("mid", "Apple");
		searchPage.inputToDynamicTextbox("pf", "1000");
		searchPage.inputToDynamicTextbox("pt", "1700");
		searchPage.clickOnPageBodySearchButton();
		verifyEquals(searchPage.getSearchResultMessageText(), "No products were found that matched your criteria.");
		
	}
	
	@Test
	public void TC_11_AdvanceSearchWithOverPriceRange() {
		
		searchPage.refreshCurrentPage();
		
		searchPage.inputToDynamicTextbox("q", "Apple MacBook Pro");
		searchPage.clickToCheckDynamicCheckbox("adv");
		searchPage.selectItemInDynamicDropdown("cid", "Computers");
		searchPage.clickToCheckDynamicCheckbox("isc");
		searchPage.selectItemInDynamicDropdown("mid", "Apple");
		searchPage.inputToDynamicTextbox("pf", "1900");
		searchPage.inputToDynamicTextbox("pt", "5000");
		searchPage.clickOnPageBodySearchButton();
		verifyEquals(searchPage.getSearchResultMessageText(), "No products were found that matched your criteria.");
		
	}
	
	@AfterClass(alwaysRun=true)
	public void afterClass() { 
		closeBrowserAndDriver(driver);
		
	}

}
