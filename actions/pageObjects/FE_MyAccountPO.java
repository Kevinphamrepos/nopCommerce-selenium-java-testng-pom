package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPO;
import pageUIs.AbstractPageUI;
import pageUIs.FE_MyAccountPageUI;

public class FE_MyAccountPO extends AbstractPO {
	WebDriver driver;

	public FE_MyAccountPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

	public String getMyAccountPageTitle() {
		waitForElementVisibleByXpath(FE_MyAccountPageUI.MY_ACCOUNT_PAGE_TITLE);
		return getTextElement(FE_MyAccountPageUI.MY_ACCOUNT_PAGE_TITLE);
	}

	public void clickOnDynamicMyAccountNavigationLink(String navigationLink) {
		waitForDynamicElementClickableByXpath(FE_MyAccountPageUI.DYNAMIC_ACCOUNT_NAVIGATION, navigationLink);
		clickOnDynamicElement(FE_MyAccountPageUI.DYNAMIC_ACCOUNT_NAVIGATION, navigationLink);
	}
	
	public void clickOnDynamicOrderDetailsLink(String orderNumber) {
		waitForDynamicElementClickableByXpath(FE_MyAccountPageUI.DYNAMIC_ORDER_DETAILS_LINK, orderNumber);
		clickOnDynamicElement(FE_MyAccountPageUI.DYNAMIC_ORDER_DETAILS_LINK, orderNumber);
	}
	
	public boolean isAccountInfoSectionDisplayed() {
		waitForElementVisibleByXpath(FE_MyAccountPageUI.ACCOUNT_INFO_SECTION);
		return isElementDisplayed(FE_MyAccountPageUI.ACCOUNT_INFO_SECTION);
	}
	
	public String getTextDynamicAccountInfo(String classValue) {
		waitForDynamicElementVisibleByXpath(FE_MyAccountPageUI.DYNAMIC_ACCOUNT_INFO, classValue);
		return getTextDynamicElement(FE_MyAccountPageUI.DYNAMIC_ACCOUNT_INFO, classValue);
	}
	
	public boolean isDynamicProductReviewedNameDisplayed(String productName) {
		waitForDynamicElementVisibleByXpath(FE_MyAccountPageUI.DYNAMIC_PRODUCT_REVIEW_NAME, productName);
		return isDynamicElementDisplayed(FE_MyAccountPageUI.DYNAMIC_PRODUCT_REVIEW_NAME, productName);
	}
	
	public boolean isDynamicOrdersDisplayed(String orderNumber) {
		waitForDynamicElementVisibleByXpath(FE_MyAccountPageUI.DYNAMIC_ORDER_NUMBER_DISPLAYED, orderNumber);
		return isDynamicElementDisplayed(FE_MyAccountPageUI.DYNAMIC_ORDER_NUMBER_DISPLAYED, orderNumber);
	}
	
	public void deleteAllAddressInfo() {
		if (isElementDisplayed(AbstractPageUI.PAGE_BODY_NO_DATA_TEXT) == false) {
			List<WebElement> elementList = driver.findElements(By.xpath(FE_MyAccountPageUI.ACCOUNT_INFO_DELETE_ALL));
			System.out.println(elementList.size());
			for (int i=1; i<= elementList.size(); i++) {
				WebElement element = driver.findElement(By.xpath(FE_MyAccountPageUI.ACCOUNT_INFO_DELETE_ALL));
				element.click();
				acceptAlert();
			}
		}
	}
	
}
