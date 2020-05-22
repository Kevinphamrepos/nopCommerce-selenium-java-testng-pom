package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class FE_SitemapPO extends AbstractPO {
	WebDriver driver;

	public FE_SitemapPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

}
