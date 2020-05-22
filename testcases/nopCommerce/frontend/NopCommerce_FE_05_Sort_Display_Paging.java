package nopCommerce.frontend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.PageGeneratorManager;
import pageObjects.FE_HomePO;
import pageObjects.FE_ProductCategoriesPO;

public class NopCommerce_FE_05_Sort_Display_Paging extends AbstractTest {
	private WebDriver driver;
	
	private FE_HomePO homePage;
	private FE_ProductCategoriesPO productCategoriesPage;
	
	String sortAscending, sortDescending;

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		
		sortAscending = "Ascending";
		sortDescending = "Descending";
		
		driver = getBrowserDriverAutoUrl(browserName, urlValue);
		homePage = PageGeneratorManager.getHomePage(driver);
		
		homePage.hoverOnDynamicHeaderMenu("Computers");
		homePage.clickOnDynamicHeaderMenu("Notebooks"); 
		productCategoriesPage = PageGeneratorManager.getProductCategoriesPage(driver);
		
	}
	
	@Test
	public void TC_01_SortNameAtoZ() {
		
		productCategoriesPage.selectItemInDynamicDropdown("products-orderby", "Name: A to Z");
		verifyTrue(productCategoriesPage.isProductNameSortedAsExpectation(sortAscending));
	}
	
	@Test
	public void TC_02_SortNameZtoA() {
		
		productCategoriesPage.refreshCurrentPage();
		
		productCategoriesPage.selectItemInDynamicDropdown("products-orderby", "Name: Z to A");
		verifyTrue(productCategoriesPage.isProductNameSortedAsExpectation(sortDescending));
	}
	
	@Test
	public void TC_03_SortPriceLowtoHigh() {
		
		productCategoriesPage.refreshCurrentPage();
		
		productCategoriesPage.selectItemInDynamicDropdown("products-orderby", "Price: Low to High");
		verifyTrue(productCategoriesPage.isProductPriceSortedAsExpectation(sortAscending));
	}
	
	@Test
	public void TC_04_SortPriceHightoLow() {
		
		productCategoriesPage.refreshCurrentPage();
		
		productCategoriesPage.selectItemInDynamicDropdown("products-orderby", "Price: High to Low");
		verifyTrue(productCategoriesPage.isProductPriceSortedAsExpectation(sortDescending));
	}
	
	@Test
	public void TC_05_Display3ProductPerPage() {
		
		productCategoriesPage.refreshCurrentPage();
		
		productCategoriesPage.selectItemInDynamicDropdown("products-orderby", "Position");
		
		productCategoriesPage.selectItemInDynamicDropdown("products-pagesize", "3");
		verifyTrue(productCategoriesPage.isNumberProductDisplayedAsViewNumber("3"));
		productCategoriesPage.isDynamicPageNavigationIconDisplayed("Next");
		
		productCategoriesPage.clickOnDynamicPageByNumber("2");
		productCategoriesPage.isDynamicPageNavigationIconDisplayed("Previous");
	}
	
	@Test
	public void TC_06_Display6ProductPerPage() {
		
		productCategoriesPage.refreshCurrentPage();
		
		productCategoriesPage.selectItemInDynamicDropdown("products-pagesize", "6");
		verifyTrue(productCategoriesPage.isNumberProductDisplayedAsViewNumber("6"));
		verifyFalse(productCategoriesPage.isPagingBarUnDisplayed());
	}
	
	@Test
	public void TC_07_Display9ProductPerPage() {
		
		productCategoriesPage.refreshCurrentPage();
		
		productCategoriesPage.selectItemInDynamicDropdown("products-orderby", "Position");
		
		productCategoriesPage.selectItemInDynamicDropdown("products-pagesize", "9");
		verifyTrue(productCategoriesPage.isNumberProductDisplayedAsViewNumber("9"));
		verifyFalse(productCategoriesPage.isPagingBarUnDisplayed());
	}
	
	@AfterClass(alwaysRun=true)
	public void afterClass() { 
		closeBrowserAndDriver(driver);
		
	}

}
