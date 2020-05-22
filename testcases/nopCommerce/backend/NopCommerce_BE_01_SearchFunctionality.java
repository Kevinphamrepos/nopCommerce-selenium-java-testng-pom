package nopCommerce.backend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.PageGeneratorManager;
import pageObjects.BE_AdministrationPO;
import pageObjects.BE_LoginPO;

public class NopCommerce_BE_01_SearchFunctionality extends AbstractTest {
	private WebDriver driver;
	
	private BE_LoginPO loginBEPage;
	private BE_AdministrationPO administrationPage;

	String email, password;
	String productName, expectedCategory;

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		
		email = "admin@yourstore.com";
		password = "admin";
		
		productName = "Lenovo IdeaCentre 600 All-in-One PC";

		driver = getBrowserDriverAutoUrl(browserName, urlValue);
		loginBEPage = PageGeneratorManager.getLoginBEPage(driver);
		verifyTrue(loginBEPage.isDynamicPageFormDisplayed("page login-page"));
		
		loginBEPage.inputToDynamicTextbox("Email", "admin@yourstore.com");
		loginBEPage.inputToDynamicTextbox("Password", "admin");
		loginBEPage.clickOnDynamicButton("Log in");
		administrationPage = PageGeneratorManager.getAdministrationPage(driver);
		verifyTrue(administrationPage.isDashboardPageTitleDisplayed());
		
		administrationPage.clickOnDynamicSidebarMenu("Catalog");
		administrationPage.clickOnDynamicSidebarSubmenu("Products");
		verifyTrue(administrationPage.isDynamicAdministrationSubPageHeaderDisplayed("Products"));
		
	}
	
	@Test
	public void TC_01_SearchByProductName() {
		
		administrationPage.clickToOpenSearchSection();
		administrationPage.inputToDynamicTextbox("SearchProductName", productName);
		administrationPage.clickOnDynamicButtonByID("search-products");
		
		verifyEquals(administrationPage.getTotalNumberSearchResultByDynamicColumnName("Product name"), 1);
		verifyTrue(administrationPage.isSearchResultProductTitleDisplayedOnPageAsExpectation(productName));
		
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue("Lenovo IdeaCentre 600 All-in-One PC", "SKU"), "LE_IC_600");
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue("Lenovo IdeaCentre 600 All-in-One PC", "Price"), "500");
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue("Lenovo IdeaCentre 600 All-in-One PC", "Stock quantity"), "10000");
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue("Lenovo IdeaCentre 600 All-in-One PC", "Product type"), "Simple");
		
		verifyTrue(administrationPage.isDynamicPublishedIconDisplayedByProductName("Lenovo IdeaCentre 600 All-in-One PC"));
		
	}
	
	@Test
	public void TC_02_SearchByProductNameAndParentCategory() {
		
		administrationPage.refreshCurrentPage();
		administrationPage.clickToOpenSearchSection();
		administrationPage.inputToDynamicTextbox("SearchProductName", productName);
		administrationPage.selectItemInDynamicDropdownByID("SearchCategoryId", "Computers");
		administrationPage.clickOnDynamicButtonByID("search-products");
		
		verifyEquals(administrationPage.getTextDataTableEmptyMsg(), "No data available in table");
		
	}
	
	@Test
	public void TC_03_SearchByProductParentNameAndCheckedSubCategory() {
		
		administrationPage.refreshCurrentPage();
		administrationPage.clickToOpenSearchSection();
		administrationPage.inputToDynamicTextbox("SearchProductName", productName);
		administrationPage.selectItemInDynamicDropdownByID("SearchCategoryId", "Computers");
		administrationPage.clickToCheckDynamicCheckbox("SearchIncludeSubCategories");
		administrationPage.clickOnDynamicButtonByID("search-products");
		verifyEquals(administrationPage.getTotalNumberSearchResultByDynamicColumnName("Product name"), 1);
		verifyTrue(administrationPage.isSearchResultProductTitleDisplayedOnPageAsExpectation(productName));
		
	}
	
	@Test
	public void TC_04_SearchByProductNameAndSubCategory() {
		
		administrationPage.refreshCurrentPage();
		administrationPage.clickToOpenSearchSection();
		administrationPage.inputToDynamicTextbox("SearchProductName", productName);
		administrationPage.selectItemInDynamicDropdownByID("SearchCategoryId", "Computers >> Desktops");
		administrationPage.clickToUncheckDynamicCheckbox("SearchIncludeSubCategories");
		administrationPage.clickOnDynamicButtonByID("search-products");
		verifyEquals(administrationPage.getTotalNumberSearchResultByDynamicColumnName("Product name"), 1);
		verifyTrue(administrationPage.isSearchResultProductTitleDisplayedOnPageAsExpectation(productName));
		
	}
	
	@Test
	public void TC_05_SearchByProductNameAndManufacture() {
		
		administrationPage.refreshCurrentPage();
		administrationPage.clickToOpenSearchSection();
		administrationPage.inputToDynamicTextbox("SearchProductName", productName);
		administrationPage.selectItemInDynamicDropdownByID("SearchCategoryId", "All");
		administrationPage.clickToUncheckDynamicCheckbox("SearchIncludeSubCategories");
		administrationPage.selectItemInDynamicDropdownByID("SearchManufacturerId", "Apple");
		administrationPage.clickOnDynamicButtonByID("search-products");
		
		verifyEquals(administrationPage.getTextDataTableEmptyMsg(), "No data available in table");
		
	}
	
	@Test
	public void TC_06_SearchByProductSKU() {
		
		administrationPage.refreshCurrentPage();
		administrationPage.clickToOpenSearchSection();
		administrationPage.inputToDynamicTextbox("GoDirectlyToSku", "LE_IC_600");
		administrationPage.clickOnDynamicButtonByID("go-to-product-by-sku");
		
		verifyTrue(administrationPage.isDynamicAdministrationSubPageHeaderDisplayed(productName));
		
	}
	

	@AfterClass(alwaysRun=true)
	public void afterClass() { 
		closeBrowserAndDriver(driver);
		
	}

}
