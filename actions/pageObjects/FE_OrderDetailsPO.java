package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;
import pageUIs.FE_OrdersPageUI;

public class FE_OrderDetailsPO extends AbstractPO {
	WebDriver driver;

	public FE_OrderDetailsPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

	public String getTextDetailsOrderNumber() {
		waitForElementVisibleByXpath(FE_OrdersPageUI.ORDER_NUMBER_TEXT);
		return getTextElement(FE_OrdersPageUI.ORDER_NUMBER_TEXT);
	}
	
	public String getTextDynamicOrderDetails(String details) {
		waitForDynamicElementVisibleByXpath(FE_OrdersPageUI.DYNAMIC_ORDER_DETAILS_TEXT, details);
		return getTextDynamicElement(FE_OrdersPageUI.DYNAMIC_ORDER_DETAILS_TEXT, details);
	}
	
	public String getTextSectionTotalInfo() {
		waitForElementVisibleByXpath(FE_OrdersPageUI.SECTION_TOTAL_INFO);
		return getTextElement(FE_OrdersPageUI.SECTION_TOTAL_INFO);
	}
	
	public String getTextSectionOptions() {
		waitForElementVisibleByXpath(FE_OrdersPageUI.SECTION_OPTIONS_TEXT);
		return getTextElement(FE_OrdersPageUI.SECTION_OPTIONS_TEXT);
	}
	
}
