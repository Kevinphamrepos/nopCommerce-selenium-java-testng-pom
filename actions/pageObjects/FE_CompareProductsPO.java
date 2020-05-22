package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;
import pageUIs.FE_CompareProductsPageUI;

public class FE_CompareProductsPO extends AbstractPO {
	WebDriver driver;

	public FE_CompareProductsPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

	public boolean isDynamicProductRemoveIconByNameDisplayed(String productName) {
		waitForDynamicElementVisibleByXpath(FE_CompareProductsPageUI.DYNAMIC_TABLE_REMOVE_ICON_BY_NAME, productName);
		return isDynamicElementDisplayed(FE_CompareProductsPageUI.DYNAMIC_TABLE_REMOVE_ICON_BY_NAME, productName);
	}

	public boolean isDynamicProductNameDisplayed(String productName) {
		waitForDynamicElementVisibleByXpath(FE_CompareProductsPageUI.DYNAMIC_PRODUCT_NAME, productName);
		return isDynamicElementDisplayed(FE_CompareProductsPageUI.DYNAMIC_PRODUCT_NAME, productName);
	}

	public boolean isDynamicRowLabelDisplayed(String label) {
		waitForDynamicElementVisibleByXpath(FE_CompareProductsPageUI.DYNAMIC_TABLE_ROW_LABEL, label);
		return isDynamicElementDisplayed(FE_CompareProductsPageUI.DYNAMIC_TABLE_ROW_LABEL, label);
	}

	public boolean isDynamicProductPriceByNameDisplayed(String productName) {
		int productNameColumnIndex = findDynamicElementsByXpath(FE_CompareProductsPageUI.DYNAMIC_PRODUCT_NAME, productName).size() + 1;
		String productNameColunmXpath = String.format(FE_CompareProductsPageUI.DYNAMIC_TABLE_PRODUCT_PRICE_BY_NAME, productName, productNameColumnIndex);
		waitForElementVisibleByXpath(productNameColunmXpath);
		return isElementDisplayed(productNameColunmXpath);
	}
	
	public String getDynamicProductPriceByName(String productName) { 
		int productNameColumnIndex = findDynamicElementsByXpath(FE_CompareProductsPageUI.DYNAMIC_PRODUCT_NAME, productName).size() + 1;
		String productNameColunmXpath = String.format(FE_CompareProductsPageUI.DYNAMIC_TABLE_PRODUCT_PRICE_BY_NAME, productName, productNameColumnIndex);
		waitForElementVisibleByXpath(productNameColunmXpath);
		return getTextElement(productNameColunmXpath);
	}
	
	public void clickOnClearListButton() {
		waitForElementClickableByXpath(FE_CompareProductsPageUI.COMPARE_PRODUCT_CLEAR_BUTTON);
		clickOnElement(FE_CompareProductsPageUI.COMPARE_PRODUCT_CLEAR_BUTTON);
		
	}

}
