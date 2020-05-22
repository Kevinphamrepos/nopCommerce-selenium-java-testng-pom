package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;
import pageUIs.FE_CheckoutPageUI;

public class FE_CheckoutPO extends AbstractPO {
	WebDriver driver;

	public FE_CheckoutPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}
	
	public void clickOnDynamicCheckoutContinueButton(String partName) {
		waitForDynamicElementClickableByXpath(FE_CheckoutPageUI.DYNAMIC_CHECKOUT_CONTINUE_BUTTON, partName);
		clickOnDynamicElement(FE_CheckoutPageUI.DYNAMIC_CHECKOUT_CONTINUE_BUTTON, partName);
	}
	
	public void clickToCheckDynamicCheckoutRadioButtonOrCheckbox(String checkboxID) {
		waitForDynamicElementClickableByXpath(FE_CheckoutPageUI.DYNAMIC_CHECKOUT_RADIOBUTTON_CHECKBOX, checkboxID);
		if (!isDynamicElementSelected(FE_CheckoutPageUI.DYNAMIC_CHECKOUT_RADIOBUTTON_CHECKBOX, checkboxID))
			clickOnDynamicElement(FE_CheckoutPageUI.DYNAMIC_CHECKOUT_RADIOBUTTON_CHECKBOX, checkboxID);
	}

	public boolean isSectionPaymentInfoDisplayed() {
		waitForElementVisibleByXpath(FE_CheckoutPageUI.CHECKOUT_SECTION_PAYMENT_INFO);
		return isElementDisplayed(FE_CheckoutPageUI.CHECKOUT_SECTION_PAYMENT_INFO);
	}
	
	public String getTextDynamicCheckoutInfoList(String partName) {
		waitForDynamicElementVisibleByXpath(FE_CheckoutPageUI.DYNAMIC_CHECKOUT_INFO_LIST, partName);
		return getTextDynamicElement(FE_CheckoutPageUI.DYNAMIC_CHECKOUT_INFO_LIST, partName);
	}
	
	public String getTextCartOptions() {
		waitForElementVisibleByXpath(FE_CheckoutPageUI.CHECKOUT_CART_OPTIONS);
		return getTextElement(FE_CheckoutPageUI.CHECKOUT_CART_OPTIONS);
	}
	
	public String getTextCartFooterTotalInfo() {
		waitForElementVisibleByXpath(FE_CheckoutPageUI.CART_FOOTER_TOTAL_INFO);
		return getTextElement(FE_CheckoutPageUI.CART_FOOTER_TOTAL_INFO);
	}
	
	public String getTextOrderSuccessfullyMsg() {
		waitForElementVisibleByXpath(FE_CheckoutPageUI.ORDER_SUCCESSFULLY_MSG);
		return getTextElement(FE_CheckoutPageUI.ORDER_SUCCESSFULLY_MSG);
	}
	
	public boolean isOrderNumberDisplay() {
		waitForElementVisibleByXpath(FE_CheckoutPageUI.ORDER_NUMBER_DISPLAYED);
		return isElementDisplayed(FE_CheckoutPageUI.ORDER_NUMBER_DISPLAYED);
	}
	
	public String getTextOrderNumber() {
		waitForElementVisibleByXpath(FE_CheckoutPageUI.ORDER_NUMBER_DISPLAYED);
		return getTextElement(FE_CheckoutPageUI.ORDER_NUMBER_DISPLAYED).replaceAll("[\\D]", "");
	}
	
}
