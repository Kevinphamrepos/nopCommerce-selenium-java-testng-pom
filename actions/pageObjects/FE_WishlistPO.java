package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;
import pageUIs.FE_WishlistPageUI;

public class FE_WishlistPO extends AbstractPO {
	WebDriver driver;

	public FE_WishlistPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}
	
	public void clickOnWishlistShareLink() { 
		waitForElementClickableByXpath(FE_WishlistPageUI.WISHLIST_SHARE_LINK);
		clickOnElement(FE_WishlistPageUI.WISHLIST_SHARE_LINK);
	}
	
	public String getWishlistPageTitleText() {
		waitForElementVisibleByXpath(FE_WishlistPageUI.WISHLIST_PAGE_TITLE_TEXT);
		return getTextElement(FE_WishlistPageUI.WISHLIST_PAGE_TITLE_TEXT);
	}
	
}
