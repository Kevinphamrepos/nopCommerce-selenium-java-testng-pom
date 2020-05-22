package pageObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPO;
import pageUIs.FE_ProductCategoriesPageUI;

public class FE_ProductCategoriesPO extends AbstractPO {
	WebDriver driver;

	public FE_ProductCategoriesPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

	public boolean isProductNameSortedAsExpectation(String sortType) {
		ArrayList<String> originalarrayList = new ArrayList<String>();
		List<WebElement> elementList = driver.findElements(By.xpath(FE_ProductCategoriesPageUI.SORT_RESULT_PRODUCT_TITLE));
		for (WebElement element : elementList) {
			originalarrayList.add(element.getText());
		}
		ArrayList<String> sortingArray = new ArrayList<String>();
		for (String child : originalarrayList) {
			sortingArray.add(child);
		}
		Collections.sort(sortingArray);
		if (sortType.equalsIgnoreCase("Ascending")) {
			System.out.println("Collection is Sorted Ascending");
		} else if (sortType.equalsIgnoreCase("Descending")) {
			Collections.reverse(sortingArray);
			System.out.println("Collection is Sorted Descending");
		} else {
			System.out.println("Please Provide Corrective SortType");
		}
		return sortingArray.equals(originalarrayList); // For Ignore Case
	}
	
	public boolean isProductPriceSortedAsExpectation(String sortType) {
		ArrayList<String> originalarrayList = new ArrayList<String>();
		ArrayList<Float> arrayListFloat = new ArrayList<Float>(); // For Float Type
		List<WebElement> elementList = driver.findElements(By.xpath(FE_ProductCategoriesPageUI.SORT_RESULT_PRICE));
		for (WebElement element : elementList) {
			originalarrayList.add(element.getText());
			arrayListFloat.add(Float.parseFloat(element.getText().replaceAll("[$,]", "").trim())); // For Float Type
		}
		ArrayList<Float> sortingArray = new ArrayList<Float>();
		for (Float child : arrayListFloat) {
			sortingArray.add(child);
		}
		
		Collections.sort(sortingArray);

		if (sortType.equalsIgnoreCase("Ascending")) {
			System.out.println("Collection is Sorted Ascending");
		} else if (sortType.equalsIgnoreCase("Descending")) {
			Collections.reverse(sortingArray);
			System.out.println("Collection is Sorted Descending");
		} else {
			System.out.println("Please Provide Corrective SortType");
		}
		
		return sortingArray.equals(arrayListFloat); 
	}
		
	public boolean isNumberProductDisplayedAsViewNumber(String viewNumber) {
		waitForListElementsVisibleByXpath(FE_ProductCategoriesPageUI.SORT_RESULT_PRODUCT_TITLE);
		int displayedProduct = findElementsByXpath(FE_ProductCategoriesPageUI.SORT_RESULT_PRODUCT_TITLE).size();
		int viewInNumber = NumberUtils.toInt(viewNumber);
		return (displayedProduct <= viewInNumber);
	}

	public boolean isDynamicPageNavigationIconDisplayed(String iconName) {
		waitForDynamicElementVisibleByXpath(FE_ProductCategoriesPageUI.DYNAMIC_PAGING_ICON, iconName);
		return isDynamicElementDisplayed(FE_ProductCategoriesPageUI.DYNAMIC_PAGING_ICON, iconName);
	}
	
	public void clickOnDynamicPageByNumber(String pageNumber) {
		waitForDynamicElementVisibleByXpath(FE_ProductCategoriesPageUI.DYNAMIC_PAGING_ICON, pageNumber);
		clickOnDynamicElement(FE_ProductCategoriesPageUI.DYNAMIC_PAGING_ICON, pageNumber);
	}

	public boolean isPagingBarUnDisplayed() {
		waitForElementInvisibleByXpath(FE_ProductCategoriesPageUI.PAGING_BAR);
		return isElementDisplayed(FE_ProductCategoriesPageUI.PAGING_BAR);
	}
	
}
