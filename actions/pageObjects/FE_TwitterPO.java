package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class FE_TwitterPO extends AbstractPO {
	WebDriver driver;

	public FE_TwitterPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

}
