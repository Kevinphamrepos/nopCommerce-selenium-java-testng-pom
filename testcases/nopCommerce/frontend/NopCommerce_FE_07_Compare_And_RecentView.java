package nopCommerce.frontend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.PageGeneratorManager;
import pageObjects.FE_CompareProductsPO;
import pageObjects.FE_HomePO;
import pageObjects.FE_ProductCategoriesPO;
import pageObjects.FE_ProductDetailsPO;
import pageObjects.FE_RecentlyViewedProductsPO;

public class NopCommerce_FE_07_Compare_And_RecentView extends AbstractTest {
	private WebDriver driver;
	
	private FE_HomePO homePage;
	private FE_ProductDetailsPO productDetailsPage;
	private FE_ProductCategoriesPO productCategoriesPage;
	private FE_CompareProductsPO compareProductsPage;
	private FE_RecentlyViewedProductsPO recentlyViewedProductsPage;
	
	String registeredEmail, validPassword;
	String firstName, lastName;

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		
		driver = getBrowserDriverAutoUrl(browserName, urlValue);
		homePage = PageGeneratorManager.getHomePage(driver);
		
	}
	
	@Test
	public void TC_01_AddProductToCompare() {
		
		homePage.hoverOnDynamicHeaderMenu("Computers");
		homePage.clickOnDynamicHeaderMenu("Notebooks");
		productCategoriesPage = PageGeneratorManager.getProductCategoriesPage(driver);

		productCategoriesPage.addDynamicProductToComparelistByName("Apple MacBook Pro 13-inch", "Add to compare list");
		verifyEquals(productCategoriesPage.getTextActionSuccessMessage(), "The product has been added to your product comparison");
		productCategoriesPage.clickOnNotificationBarCloseButton();
		
		productCategoriesPage.addDynamicProductToComparelistByName("Lenovo Thinkpad X1 Carbon Laptop", "Add to compare list");
		verifyEquals(productCategoriesPage.getTextActionSuccessMessage(), "The product has been added to your product comparison");
		productCategoriesPage.clickOnNotificationBarCloseButton();
		
		productCategoriesPage.openDynamicFooterPage("Compare products list");
		compareProductsPage = PageGeneratorManager.getCompareProductsPage(driver);
		verifyTrue(compareProductsPage.isDynamicPageFormDisplayed("page compare-products-page"));
		
		verifyTrue(compareProductsPage.isDynamicProductRemoveIconByNameDisplayed("Lenovo Thinkpad X1 Carbon Laptop"));
		verifyTrue(compareProductsPage.isDynamicProductRemoveIconByNameDisplayed("Apple MacBook Pro 13-inch"));
		
		verifyTrue(compareProductsPage.isDynamicProductNameDisplayed("Lenovo Thinkpad X1 Carbon Laptop"));
		verifyTrue(compareProductsPage.isDynamicProductNameDisplayed("Apple MacBook Pro 13-inch"));
		
		verifyTrue(compareProductsPage.isDynamicRowLabelDisplayed("Name"));
		verifyTrue(compareProductsPage.isDynamicRowLabelDisplayed("Price"));
		
		verifyTrue(compareProductsPage.isDynamicProductPriceByNameDisplayed("Lenovo Thinkpad X1 Carbon Laptop"));
		verifyTrue(compareProductsPage.isDynamicProductPriceByNameDisplayed("Apple MacBook Pro 13-inch"));
		
		compareProductsPage.clickOnClearListButton();
		verifyEquals(compareProductsPage.getTextPageBodyMessage(), "You have no items to compare.");
		verifyFalse(compareProductsPage.isProductNameUnDisplayedInTable("Lenovo Thinkpad X1 Carbon Laptop"));
		verifyFalse(compareProductsPage.isProductNameUnDisplayedInTable("Apple MacBook Pro 13-inch"));
		
		compareProductsPage.openHomePageByLogo();
		
	}
	
	@Test
	public void TC_02_RecentlyViewedProducts() {

		// 1
		homePage.hoverOnDynamicHeaderMenu("Computers"); 
		homePage.clickOnDynamicHeaderSubMenu("Desktops"); 
		productCategoriesPage = PageGeneratorManager.getProductCategoriesPage(driver);
		productCategoriesPage.clickOnDynamicProductName("Lenovo IdeaCentre 600 All-in-One PC"); 
		productDetailsPage = PageGeneratorManager.getProductDetailsPage(driver);
		verifyTrue(productDetailsPage.isDynamicPageFormDisplayed("page product-details-page"));
		verifyEquals(productDetailsPage.getProductDetailsName(), "Lenovo IdeaCentre 600 All-in-One PC");
		
		// 2
		productDetailsPage.hoverOnDynamicHeaderMenu("Electronics");
		productDetailsPage.clickOnDynamicHeaderSubMenu("Camera & photo");
		productCategoriesPage = PageGeneratorManager.getProductCategoriesPage(driver);
		productCategoriesPage.clickOnDynamicProductName("Nikon D5500 DSLR"); 
		productDetailsPage = PageGeneratorManager.getProductDetailsPage(driver);
		verifyTrue(productDetailsPage.isDynamicPageFormDisplayed("page product-details-page"));
		verifyEquals(productDetailsPage.getProductDetailsName(), "Nikon D5500 DSLR"); 
		
		// 3
		productDetailsPage.clickOnDynamicHeaderMenu("Books");
		productCategoriesPage = PageGeneratorManager.getProductCategoriesPage(driver);
		productCategoriesPage.clickOnDynamicProductName("First Prize Pies"); 
		productDetailsPage = PageGeneratorManager.getProductDetailsPage(driver);
		verifyTrue(productDetailsPage.isDynamicPageFormDisplayed("page product-details-page"));
		verifyEquals(productDetailsPage.getProductDetailsName(), "First Prize Pies"); 
		
		// 4
		productDetailsPage.clickOnDynamicHeaderMenu("Jewelry");
		productCategoriesPage = PageGeneratorManager.getProductCategoriesPage(driver);
		productCategoriesPage.clickOnDynamicProductName("Vintage Style Engagement Ring"); 
		productDetailsPage = PageGeneratorManager.getProductDetailsPage(driver);
		verifyTrue(productDetailsPage.isDynamicPageFormDisplayed("page product-details-page"));
		verifyEquals(productDetailsPage.getProductDetailsName(), "Vintage Style Engagement Ring"); 
		
		// 5
		productDetailsPage.hoverOnDynamicHeaderMenu("Apparel"); 
		productDetailsPage.clickOnDynamicHeaderSubMenu("Accessories");
		productCategoriesPage = PageGeneratorManager.getProductCategoriesPage(driver);
		productCategoriesPage.clickOnDynamicProductName("Ray Ban Aviator Sunglasses"); 
		productDetailsPage = PageGeneratorManager.getProductDetailsPage(driver);
		verifyTrue(productDetailsPage.isDynamicPageFormDisplayed("page product-details-page"));
		verifyEquals(productDetailsPage.getProductDetailsName(), "Ray Ban Aviator Sunglasses"); 

		productDetailsPage.openDynamicFooterPage("Recently viewed products");
		recentlyViewedProductsPage = PageGeneratorManager.getRecentlyViewedProductsPage(driver);
		verifyTrue(recentlyViewedProductsPage.isDynamicPageFormDisplayed("page recently-viewed-products-page"));

		verifyEquals(recentlyViewedProductsPage.getTotalNumberProductTitleDisplayedOnPage(), 3); 
		verifyTrue(recentlyViewedProductsPage.isDynamicProductNameDisplayedOnPage("First Prize Pies"));
		verifyTrue(recentlyViewedProductsPage.isDynamicProductNameDisplayedOnPage("Vintage Style Engagement Ring"));
		verifyTrue(recentlyViewedProductsPage.isDynamicProductNameDisplayedOnPage("Ray Ban Aviator Sunglasses"));

	}
	
	@AfterClass(alwaysRun=true)
	public void afterClass() { 
		closeBrowserAndDriver(driver);
		
	}

}
