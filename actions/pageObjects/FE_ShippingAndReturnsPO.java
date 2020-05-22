package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class FE_ShippingAndReturnsPO extends AbstractPO {
	WebDriver driver;

	public FE_ShippingAndReturnsPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

}
