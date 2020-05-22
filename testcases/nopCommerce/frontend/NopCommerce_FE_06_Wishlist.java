package nopCommerce.frontend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.PageGeneratorManager;
import pageObjects.FE_HomePO;
import pageObjects.FE_LoginPO;
import pageObjects.FE_ProductDetailsPO;
import pageObjects.FE_ShoppingCartPO;
import pageObjects.FE_WishlistPO;

public class NopCommerce_FE_06_Wishlist extends AbstractTest {
	private WebDriver driver;
	
	private FE_HomePO homePage;
	private FE_LoginPO loginPage;
	private FE_ProductDetailsPO productDetailsPage;
	private FE_WishlistPO wishlistPage;
	private FE_ShoppingCartPO shoppingCartpage;
	
	
	String registeredEmail, validPassword;
	String firstName, lastName;

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		
		registeredEmail = "Kevinpham.registerednopcom@gmail.com";
		validPassword = "Abcxyz123NEW";
		firstName = "Kevin"; 
		lastName = "Pham";
		
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
	
	@BeforeMethod
	public void PreCondition_AddProductToWishlist() { 
		
		homePage.clickOnDynamicProductName("Apple MacBook Pro 13-inch");
		productDetailsPage = PageGeneratorManager.getProductDetailsPage(driver);
		verifyTrue(productDetailsPage.isDynamicPageFormDisplayed("page product-details-page"));
		verifyEquals(productDetailsPage.getProductDetailsName(), "Apple MacBook Pro 13-inch");

		productDetailsPage.clickOnDynamicOverviewButton("Add to wishlist");
		verifyEquals(productDetailsPage.getTextActionSuccessMessage(), "The product has been added to your wishlist");
		productDetailsPage.clickOnNotificationBarCloseButton();
		
		productDetailsPage.openDynamicHeaderLink("Wishlist");
		wishlistPage = PageGeneratorManager.getWishlistPage(driver);
		verifyTrue(wishlistPage.isDynamicPageFormDisplayed("page wishlist-page"));
		verifyTrue(wishlistPage.isProductNameDisplayedInTable("Apple MacBook Pro 13-inch")); 
		
	}
	
	@AfterMethod 
	public void PostCondition_ClearWishlist() { 
		
		wishlistPage.openHomePageByLogo();
		homePage.openDynamicHeaderLink("Wishlist");
		wishlistPage = PageGeneratorManager.getWishlistPage(driver);
		
		wishlistPage.removeAllProduct();
		verifyEquals(wishlistPage.getTextPageBodyMessage(), "The wishlist is empty!");
		
		wishlistPage.openHomePageByLogo();
	}
	
	@Test
	public void TC_01_ShareWishList() {
		
		wishlistPage.clickOnWishlistShareLink(); // Bug - Link is NOT Displayed
		verifyEquals(wishlistPage.getWishlistPageTitleText(), "Wishlist of " + firstName + " " + lastName);
		
	}
		
	@Test
	public void TC_02_AddProductToCartFromWishlist() {
		
		wishlistPage.clickToCheckDynamicCheckboxByNameAndColumn("Apple MacBook Pro 13-inch", "add-to-cart");
		wishlistPage.clickOnDynamicButton("Add to cart");
		shoppingCartpage = PageGeneratorManager.getShoppingCartPage(driver);
		verifyTrue(shoppingCartpage.isDynamicPageFormDisplayed("page shopping-cart-page"));
		verifyTrue(shoppingCartpage.isProductNameDisplayedInTable("Apple MacBook Pro 13-inch"));
		
		shoppingCartpage.openDynamicHeaderLink("Wishlist");
		wishlistPage = PageGeneratorManager.getWishlistPage(driver);
		verifyTrue(wishlistPage.isDynamicPageFormDisplayed("page wishlist-page"));
		verifyEquals(wishlistPage.getTextPageBodyMessage(), "The wishlist is empty!");

	}
	
	@Test
	public void TC_03_RemoveProductFromWishList() {
		
		wishlistPage.clickToCheckDynamicCheckboxByNameAndColumn("Apple MacBook Pro 13-inch", "remove-from-cart");
		wishlistPage.clickOnDynamicButton("Update wishlist");
		verifyEquals(wishlistPage.getTextPageBodyMessage(), "The wishlist is empty!");
	}

	@AfterClass(alwaysRun=true)
	public void afterClass() { 
		closeBrowserAndDriver(driver);
		
	}

}
