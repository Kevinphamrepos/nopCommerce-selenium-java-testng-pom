package pageObjects;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPO;
import pageUIs.FE_SearchPageUI;

public class FE_SearchPO extends AbstractPO {
	WebDriver driver;

	public FE_SearchPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}
	
	public void clickOnPageBodySearchButton() {
		waitForElementClickableByXpath(FE_SearchPageUI.PAGE_BODY_SEARCH_BUTTON);
		clickOnElement(FE_SearchPageUI.PAGE_BODY_SEARCH_BUTTON);
	}

	public String getSearchResultMessageText() {
		waitForElementVisibleByXpath(FE_SearchPageUI.SEARCH_RESULT_MSG_TEXT);
		return getTextElement(FE_SearchPageUI.SEARCH_RESULT_MSG_TEXT);
	}
	
	public boolean isSearchResultProductTitleDisplayedOnPageAsExpectation(String searchValue) {
		boolean result = true; 
		List<WebElement> elementList = driver.findElements(By.xpath(FE_SearchPageUI.SEARCH_RESULT_PRODUCT_TITLE));
		for (WebElement element : elementList) {
			String displayedProductTitle = element.getText();
			if (!StringUtils.containsIgnoreCase(displayedProductTitle, searchValue)) {
				System.out.println("Break at Value = " + displayedProductTitle);
				result =  false; 
				break;
			}
		}
		return result;	
	}
}
