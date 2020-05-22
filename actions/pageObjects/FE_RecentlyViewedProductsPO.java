package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class FE_RecentlyViewedProductsPO extends AbstractPO {
	WebDriver driver;

	public FE_RecentlyViewedProductsPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}


	
}
