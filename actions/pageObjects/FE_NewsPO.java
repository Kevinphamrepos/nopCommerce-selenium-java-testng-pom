package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class FE_NewsPO extends AbstractPO {
	WebDriver driver;

	public FE_NewsPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

}
