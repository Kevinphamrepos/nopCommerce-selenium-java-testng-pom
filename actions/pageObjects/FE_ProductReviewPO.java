package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;
import pageUIs.FE_ProductReviewPageUI;

public class FE_ProductReviewPO extends AbstractPO {
	WebDriver driver;

	public FE_ProductReviewPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

	public String getProductDetailsName() {
		waitForElementVisibleByXpath(FE_ProductReviewPageUI.PRODUCT_REVIEW_TITLE_NAME_TEXT);
		return getTextElement(FE_ProductReviewPageUI.PRODUCT_REVIEW_TITLE_NAME_TEXT);
	}
	
}
