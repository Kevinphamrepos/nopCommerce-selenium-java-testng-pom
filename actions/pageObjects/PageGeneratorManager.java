package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	
	public static FE_HomePO getHomePage(WebDriver driver) {
		return new FE_HomePO(driver);
	}
	
	public static FE_WishlistPO getWishlistPage(WebDriver driver) {
		return new FE_WishlistPO(driver);
	}
	
	public static FE_ShoppingCartPO getShoppingCartPage(WebDriver driver) {
		return new FE_ShoppingCartPO(driver);
	}
	
	public static FE_RegisterPO getRegisterPage(WebDriver driver) {
		return new FE_RegisterPO(driver);
	}
	
	public static FE_LoginPO getLoginPage(WebDriver driver) {
		return new FE_LoginPO(driver);
	}
	
	public static FE_SitemapPO getSitemapPage(WebDriver driver) {
		return new FE_SitemapPO(driver);
	}
	
	public static FE_ShippingAndReturnsPO getShippingAndReturnsPage(WebDriver driver) {
		return new FE_ShippingAndReturnsPO(driver);
	}
	
	public static FE_SearchPO getSearchPage(WebDriver driver) {
		return new FE_SearchPO(driver);
	}
	
	public static FE_NewsPO getNewsPage(WebDriver driver) {
		return new FE_NewsPO(driver);
	}
	
	public static FE_MyAccountPO getMyAccountPage(WebDriver driver) {
		return new FE_MyAccountPO(driver);
	}
	
	public static FE_OrderDetailsPO getOrdersPage(WebDriver driver) {
		return new FE_OrderDetailsPO(driver);
	}
	
	public static FE_FacebookPO getFacebookPage(WebDriver driver) {
		return new FE_FacebookPO(driver);
	}
	
	public static FE_TwitterPO getTwitterPage(WebDriver driver) {
		return new FE_TwitterPO(driver);
	}

	public static FE_ProductDetailsPO getProductDetailsPage(WebDriver driver) {
		return new FE_ProductDetailsPO(driver);
	}

	public static FE_ProductReviewPO getProductReviewPage(WebDriver driver) {
		return new FE_ProductReviewPO(driver);
	}

	public static FE_ProductCategoriesPO getProductCategoriesPage(WebDriver driver) {
		return new FE_ProductCategoriesPO(driver);
	}

	public static FE_CompareProductsPO getCompareProductsPage(WebDriver driver) {
		return new FE_CompareProductsPO(driver);
	}

	public static FE_RecentlyViewedProductsPO getRecentlyViewedProductsPage(WebDriver driver) {
		return new FE_RecentlyViewedProductsPO(driver);
	}

	public static FE_CheckoutPO getCheckoutPage(WebDriver driver) {
		return new FE_CheckoutPO(driver);
	}

	public static FE_OrderDetailsPO getOrderDetailsPage(WebDriver driver) {
		return new FE_OrderDetailsPO(driver);
	}

	public static BE_LoginPO getLoginBEPage(WebDriver driver) {
		return new BE_LoginPO(driver);
	}

	public static BE_AdministrationPO getAdministrationPage(WebDriver driver) {
		return new BE_AdministrationPO(driver);
	}

	public static BE_EditPoductDetailsPO getEditPoductDetailsPage(WebDriver driver) {
		return new BE_EditPoductDetailsPO(driver);
	}

}
