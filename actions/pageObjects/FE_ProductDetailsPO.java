package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;
import pageUIs.FE_ProductDetailsPageUI;

public class FE_ProductDetailsPO extends AbstractPO {
	WebDriver driver;

	public FE_ProductDetailsPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

	public String getProductDetailsName() {
		waitForElementVisibleByXpath(FE_ProductDetailsPageUI.PRODUCT_DETAIL_NAME_TEXT);
		return getTextElement(FE_ProductDetailsPageUI.PRODUCT_DETAIL_NAME_TEXT);
	}
	
	public void clickOnDynamicProductReviewLink(String linkName) {
		waitForDynamicElementClickableByXpath(FE_ProductDetailsPageUI.DYNAMIC_PRODUCT_REVIEW_LINK, linkName);
		clickOnDynamicElement(FE_ProductDetailsPageUI.DYNAMIC_PRODUCT_REVIEW_LINK, linkName);
	}
	
	public void clickOnDynamicOverviewButton(String productName) { 
		waitForDynamicElementVisibleByXpath(FE_ProductDetailsPageUI.DYNAMIC_OVERVIEW_BUTTON, productName);
		clickOnDynamicElement(FE_ProductDetailsPageUI.DYNAMIC_OVERVIEW_BUTTON, productName);
	}
	
}
