package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPO;
import pageUIs.BE_AdministrationPageUI;

public class BE_AdministrationPO extends AbstractPO {
	WebDriver driver;

	public BE_AdministrationPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

	public boolean isDashboardPageTitleDisplayed() {
		waitForElementVisibleByXpath(BE_AdministrationPageUI.DASHBOARD_PAGE_TITLE_DISPLAYED);
		return isElementDisplayed(BE_AdministrationPageUI.DASHBOARD_PAGE_TITLE_DISPLAYED);
	}

	public void clickOnDynamicSidebarMenu(String sideMenuName) { 
		waitForDynamicElementVisibleByXpath(BE_AdministrationPageUI.DYNAMIC_SIDEBAR_MENU, sideMenuName);
		clickOnDynamicElement(BE_AdministrationPageUI.DYNAMIC_SIDEBAR_MENU, sideMenuName);
	}
	
	public void clickOnDynamicSidebarSubmenu(String subMenuName) { 
		waitForDynamicElementVisibleByXpath(BE_AdministrationPageUI.DYNAMIC_SIDEBAR_SUB_MENU, subMenuName);
		clickOnDynamicElement(BE_AdministrationPageUI.DYNAMIC_SIDEBAR_SUB_MENU, subMenuName);
	}
	
	public String getAdministrationSubPageHeaderText() {
		waitForElementVisibleByXpath(BE_AdministrationPageUI.ADMIN_SUB_PAGE_TITLE_TEXT);
		return getTextElement(BE_AdministrationPageUI.ADMIN_SUB_PAGE_TITLE_TEXT);
	}
	
	public boolean isDynamicAdministrationSubPageHeaderDisplayed(String pageHeaderText) {
		waitForElementVisibleByXpath(BE_AdministrationPageUI.ADMIN_SUB_PAGE_TITLE_TEXT);
		return getTextElement(BE_AdministrationPageUI.ADMIN_SUB_PAGE_TITLE_TEXT).contains(pageHeaderText);
	}
	
	public String getTextDataTableEmptyMsg() {
		waitForElementVisibleByXpath(BE_AdministrationPageUI.DATA_TABLE_EMPTY_MSG_TEXT);
		return getTextElement(BE_AdministrationPageUI.DATA_TABLE_EMPTY_MSG_TEXT);
	}
	
	public String getTextDynamicCustomerDetailsDataTableEmptyMsg(String tableID) {
		waitForDynamicElementVisibleByXpath(BE_AdministrationPageUI.DYNAMIC_CUSTOMER_DETAILS_TABLE_EMPTY_MSG_TEXT_BY_ID, tableID);
		return getTextDynamicElement(BE_AdministrationPageUI.DYNAMIC_CUSTOMER_DETAILS_TABLE_EMPTY_MSG_TEXT_BY_ID, tableID);
	}

	public void clickToOpenSearchSection() {
		if (isElementDisplayed(BE_AdministrationPageUI.ADMIN_SEARCH_SECTION_DISPLAYED) == false) {
			clickOnElement(BE_AdministrationPageUI.ADMIN_SEARCH_BAR);
		}
	}
	
	public void clickOnDynamicButtonByID(String buttonID) {
		waitForDynamicElementClickableByXpath(BE_AdministrationPageUI.DYNAMIC_BUTTON_BY_ID, buttonID);
		clickOnDynamicElement(BE_AdministrationPageUI.DYNAMIC_BUTTON_BY_ID, buttonID);
		sleepInSecond(3);
	}
	
	public int getTotalNumberSearchResultByDynamicColumnName(String columnName) {
		int colunmIndex = findDynamicElementsByXpath(BE_AdministrationPageUI.DYNAMIC_COLUMN_HEADER_INDEX, columnName).size() + 1;
		String dataColunmXpath = castRestParameterByIndex(BE_AdministrationPageUI.DYNAMIC_COLUNM_DATA_BY_COLUMN_INDEX, colunmIndex);
		return findElementsByXpath(dataColunmXpath).size();
	}
	
	public String getTextDynamicCellValueByReferValue(String referValue, String columnName) {
		String stringIndex = Integer.toString(findDynamicElementsByXpath(BE_AdministrationPageUI.DYNAMIC_COLUMN_HEADER_INDEX, columnName).size() + 1);
		waitForDynamicElementVisibleByXpath(BE_AdministrationPageUI.DYNAMIC_TABLE_COLUMN_TEXT_BY_PRODUCT_NAME, referValue, stringIndex);
		return getTextDynamicElement(BE_AdministrationPageUI.DYNAMIC_TABLE_COLUMN_TEXT_BY_PRODUCT_NAME, referValue, stringIndex);
	}
	
	public boolean isDynamicPublishedIconDisplayedByProductName(String productName) {
		waitForDynamicElementVisibleByXpath(BE_AdministrationPageUI.DYNAMIC_PUBLISHED_ICON_BY_PRODUCT_NAME, productName);
		return isDynamicElementDisplayed(BE_AdministrationPageUI.DYNAMIC_PUBLISHED_ICON_BY_PRODUCT_NAME, productName);
	}
	
	public void clickOnDynamicIconByReferValue(String referValue, String iconName) {
		waitForDynamicElementVisibleByXpath(BE_AdministrationPageUI.DYNAMIC_ICON_BY_REFER_VALUE, referValue, iconName);
		clickOnDynamicElement(BE_AdministrationPageUI.DYNAMIC_ICON_BY_REFER_VALUE, referValue, iconName);
	}
	
	public boolean isSearchResultProductTitleDisplayedOnPageAsExpectation(String searchValue) {
		boolean result = false; 
		int colunmIndex = findDynamicElementsByXpath(BE_AdministrationPageUI.DYNAMIC_COLUMN_HEADER_INDEX, "Product name").size() + 1;
		String dataColunmXpath = castRestParameterByIndex(BE_AdministrationPageUI.DYNAMIC_COLUNM_DATA_BY_COLUMN_INDEX, colunmIndex);
		List<WebElement> elementList = driver.findElements(By.xpath(dataColunmXpath));
		for (WebElement element : elementList) {
			String displayedProductTitle = element.getText();
			if (displayedProductTitle.equals(searchValue)) {
				System.out.println("Break at Value = " + displayedProductTitle);
				result =  true; // Khi thỏa condition if thì gán result thành false
				break;
			}
		}
		return result;	
	}
	
	public boolean isDynamicValueDisplayedOnPageAsExpectation(String columnName, String dynamicValue) {
		boolean result = false;
		int colunmIndex = findDynamicElementsByXpath(BE_AdministrationPageUI.DYNAMIC_COLUMN_HEADER_INDEX, columnName).size() + 1;
		String dataColunmXpath = castRestParameterByIndex(BE_AdministrationPageUI.DYNAMIC_COLUNM_DATA_BY_COLUMN_INDEX, colunmIndex);
		List<WebElement> elementList = driver.findElements(By.xpath(dataColunmXpath));
		for (WebElement element : elementList) {
			String displayedValue = element.getText();
			if (displayedValue.equals(dynamicValue)) {
				System.out.println("Break at Value = " + displayedValue);
				result =  true; 
				break;
			}
		}
		return result;	
	}
	
	public void clickOnBlueAddnewButton() { 
		waitForDynamicElementClickableByXpath(BE_AdministrationPageUI.BLUE_ADD_NEW_BUTTON);
		clickOnDynamicElement(BE_AdministrationPageUI.BLUE_ADD_NEW_BUTTON);
	}
	
	public void clickOnDynamicBlueButton(String buttonText) { 
		waitForDynamicElementClickableByXpath(BE_AdministrationPageUI.DYNAMIC_RIGHT_BLUE_BUTTON, buttonText);
		clickOnDynamicElement(BE_AdministrationPageUI.DYNAMIC_RIGHT_BLUE_BUTTON, buttonText);
	}
	
	public void clickForOpenDynamicSection(String sectionID) {
		if (isDynamicElementDisplayed(BE_AdministrationPageUI.DYNAMIC_SECTION_BY_ID_COLLAPSED_CONTAINER, sectionID) == false) {
			clickOnDynamicElement(BE_AdministrationPageUI.DYNAMIC_SECTION_BY_ID_HEADING_PANEL, sectionID);
		}
	}
	
	public void clickOnAddressesLink() { 
		waitForElementClickableByXpath(BE_AdministrationPageUI.ADDRESSES_LINK);
		clickOnElement(BE_AdministrationPageUI.ADDRESSES_LINK);
	}
	
	public void clickOnAddnewaddressButton() { 
		waitForElementClickableByXpath(BE_AdministrationPageUI.ADD_NEW_ADDRESS_BUTTON);
		clickOnElement(BE_AdministrationPageUI.ADD_NEW_ADDRESS_BUTTON);
	}
	
	public void clickOnDynamicSaveButton(String buttonName) {
		waitForDynamicElementClickableByXpath(BE_AdministrationPageUI.DYNAMIC_SAVE_BUTTON, buttonName);
		clickOnDynamicElement(BE_AdministrationPageUI.DYNAMIC_SAVE_BUTTON, buttonName);
		sleepInSecond(2);
	}
	
	public void selectItemInCustomMultiDropdown(String expectedItem) {
		String dropdownXpath = BE_AdministrationPageUI.CUSTOMER_ROLES_MULTI_DROPDOWN_XPATH;
		String allItemsXpath = BE_AdministrationPageUI.CUSTOMER_ROLES_ALL_ITEMS_XPATH;
		selectOneItemInCustomDropdown(dropdownXpath, allItemsXpath, expectedItem);
	}	
	
	public void deSelectDynamicItemInCustomMultiDropdown(String optionName) {
		waitForDynamicElementClickableByXpath(BE_AdministrationPageUI.DYNAMIC_SELECTED_ROLE_ITEM_DELETE_ICON, optionName);
		clickOnDynamicElement(BE_AdministrationPageUI.DYNAMIC_SELECTED_ROLE_ITEM_DELETE_ICON, optionName);
	}	
	
	public String getTextAlertMessage() {
		waitForElementVisibleByXpath(BE_AdministrationPageUI.ALERT_MESSAGE_TEXT);
		return getTextElement(BE_AdministrationPageUI.ALERT_MESSAGE_TEXT);
	}
	
	public boolean isAlertMessageDisplayed(String alertText) {
		waitForElementVisibleByXpath(BE_AdministrationPageUI.ALERT_MESSAGE_TEXT);
		return getTextElement(BE_AdministrationPageUI.ALERT_MESSAGE_TEXT).contains(alertText);
	}
	

	public void clickToCloseAlertMessage() { 
		waitForElementClickableByXpath(BE_AdministrationPageUI.ALERT_MESSAGE_CLOSE_BUTTON);
		clickOnElement(BE_AdministrationPageUI.ALERT_MESSAGE_CLOSE_BUTTON);
	}

	public boolean isCustomerRolesIsSelectedAsExpectation(String optionName) {
		waitForDynamicElementVisibleByXpath(BE_AdministrationPageUI.DYNAMIC_SELECTED_ROLE, optionName);
		return isDynamicElementDisplayed(BE_AdministrationPageUI.DYNAMIC_SELECTED_ROLE, optionName);
	}
	
	public void clickOnDynamicBackLink(String linkText) { 
		waitForDynamicElementClickableByXpath(BE_AdministrationPageUI.DYNAMIC_BACK_LINK, linkText);
		clickOnDynamicElement(BE_AdministrationPageUI.DYNAMIC_BACK_LINK, linkText);
	}


	
}
