package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class FE_ShoppingCartPO extends AbstractPO {
	WebDriver driver;

	public FE_ShoppingCartPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

}
