package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class FE_FacebookPO extends AbstractPO {
	WebDriver driver;

	public FE_FacebookPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

}
