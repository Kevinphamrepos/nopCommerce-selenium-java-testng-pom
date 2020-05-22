package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;
import pageUIs.BE_EditPoductDetailsPageUI;

public class BE_EditPoductDetailsPO extends AbstractPO {
	WebDriver driver;

	public BE_EditPoductDetailsPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

	public String getAdministrationPageHeaderText() {
		waitForElementVisibleByXpath(BE_EditPoductDetailsPageUI.EDIT_PRODUCT_DETAILS_PAGE_TITLE_TEXT);
		return getTextElement(BE_EditPoductDetailsPageUI.EDIT_PRODUCT_DETAILS_PAGE_TITLE_TEXT);
	}
	
}
