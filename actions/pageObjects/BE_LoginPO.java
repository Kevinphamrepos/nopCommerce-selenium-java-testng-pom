package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class BE_LoginPO extends AbstractPO {
	WebDriver driver;

	public BE_LoginPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

}
