package commons;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageObjects.FE_FacebookPO;
import pageObjects.FE_HomePO;
import pageObjects.FE_LoginPO;
import pageObjects.FE_MyAccountPO;
import pageObjects.FE_NewsPO;
import pageObjects.FE_OrderDetailsPO;
import pageObjects.FE_RegisterPO;
import pageObjects.FE_SearchPO;
import pageObjects.FE_ShippingAndReturnsPO;
import pageObjects.FE_ShoppingCartPO;
import pageObjects.FE_SitemapPO;
import pageObjects.FE_TwitterPO;
import pageObjects.FE_WishlistPO;
import pageObjects.PageGeneratorManager;
import pageUIs.AbstractPageUI;

public class AbstractPO {

	WebDriver driver;
	WebElement element;
	List<WebElement> elements;
	WebDriverWait explicitWait;
	Select select;
	Actions action;
	JavascriptExecutor javascriptExecutor;
	By byXpath;
	long shortTimeout = 5;
	long longTimeout = 30;

	public AbstractPO(WebDriver linkDriver) {
		driver = linkDriver;
		javascriptExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, longTimeout);
		action = new Actions(driver);
		
	}
		
	public void openUrl(String urlValue) {
		driver.get(urlValue);
	}
	
	public void openUrlInNewTabByJSE(String urlValue) {
		executeJavascript("window.open('" + urlValue + "','_blank');");
		switchToWindowByUrl(urlValue);
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getPageCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	public String getPageSource() {
		return driver.getPageSource();
	}
	
	public void backToPreviousPage() {
		driver.navigate().back();
	}
	
	public void forwardToNextPage() {
		driver.navigate().forward();
	}
	
	public void refreshCurrentPage() {
		driver.navigate().refresh();
	}
	
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}
	
	public void dismissAlert() {
		driver.switchTo().alert().dismiss();
	}
	
	public String getTextAlert() {
		return driver.switchTo().alert().getText();
	}
	
	public void sendKeysToAlert(String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public By byXpath(String elementXpath) {
		return byXpath = By.xpath(elementXpath);
	}
	
	public WebElement findElementByXpath(String elementXpath) {
		return driver.findElement(By.xpath(elementXpath));
	}
	
	public List<WebElement> findElementsByXpath(String elementsXpath) {
		return driver.findElements(By.xpath(elementsXpath));
	}
	
	public void clickOnElement(String elementXpath) {
		element = driver.findElement(By.xpath(elementXpath));
		element.click();
	}
	
	public void sendKeysToElement (String elementXpath, String value) {
		element = driver.findElement(By.xpath(elementXpath));
		element.clear();
		element.sendKeys(value);
	}
	
	public void selectItemInHTMLDropdown(String dropdownXpath, String expectedItem) {
		element = driver.findElement(By.xpath(dropdownXpath));
		select = new Select(element);
		select.selectByVisibleText(expectedItem);
	}
	
	public String getSelectedItemValue(String dropdownXpath) {
		element = driver.findElement(By.xpath(dropdownXpath));
		select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}
	
	public String getItemSelectedText(String dropdownXpath) {
		element = driver.findElement(By.xpath(dropdownXpath));
		return element.getText();
	}
	
	public String getItemSelectedTextByJS(String itemSelectedCss) {
		return (String) javascriptExecutor.executeScript("return document.querySelector('" + itemSelectedCss + "').text");
	}

	public void selectOneItemInCustomDropdown(String dropdownXpath, String allItemsXpath, String expectedItem) {
		element = driver.findElement(By.xpath(dropdownXpath));
		if (element.isDisplayed()) {
			element.click();
		} else {
			javascriptExecutor.executeScript("arguments[0].click();", element);
		}
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));
		elements = driver.findElements(By.xpath(allItemsXpath));
		for (WebElement itemElement : elements) {
			if (itemElement.getText().equals(expectedItem)) {
				if (itemElement.isDisplayed()) {
					itemElement.click();
				} else {
					javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", itemElement);
					javascriptExecutor.executeScript("arguments[0].click();", itemElement);
				}
				break;
			}
		}
	}
	
	public void deSelectOneItemInCustomDropdown(String dropdownXpath, String allItemsXpath, String expectedItem) {
		element = driver.findElement(By.xpath(dropdownXpath));
		if (element.isDisplayed()) {
			element.click();
		} else {
			javascriptExecutor.executeScript("arguments[0].click();", element);
		}
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));
		elements = driver.findElements(By.xpath(allItemsXpath));
		for (WebElement itemElement : elements) {
			if (itemElement.getText().equals(expectedItem)) {
				if (!itemElement.isDisplayed()) { 
					itemElement.click();
				} else {
					javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", itemElement);
					javascriptExecutor.executeScript("arguments[0].click();", itemElement);
				}
				break;
			}
		}
	}
	
	public void selectOneItemInDynamicCustomDropdownWithHiddenSelectTag(String selectTagID, String expectedItem) {
		String showDynamicSelectTagScript = "document.getElementById('%s').style.display=''";
		String hideDynamicSelectTagScript = "document.getElementById('%s').style.display='none'";
		String selectTagXpath = "//select[@id='%s']";
		javascriptExecutor.executeScript(castRestParameter(showDynamicSelectTagScript, selectTagID));
		selectItemInHTMLDropdown(castRestParameter(selectTagXpath, selectTagID), expectedItem);
		javascriptExecutor.executeScript(castRestParameter(hideDynamicSelectTagScript, selectTagID));
	}
	
	public void selectMultiItemInCustomDropdown(
			String dropdownXpath, 
			String allItemsXpath, 
			String[] expectedItems, 
			String itemSelectedXpath) {
		
		element = driver.findElement(By.xpath(dropdownXpath));
		if (element.isDisplayed()) {
			element.click();
		} else {
			javascriptExecutor.executeScript("arguments[0].click();", element);
		}

		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));

		elements = driver.findElements(By.xpath(allItemsXpath));
		for (WebElement itemElement : elements) {
			for (String multiItems : expectedItems) {
				if (itemElement.getText().equals(multiItems)) {
					if (itemElement.isDisplayed()) {
						itemElement.click();
					} else {
						javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", itemElement);
						javascriptExecutor.executeScript("arguments[0].click();", itemElement);
					}
					
					List<WebElement> multiItemSelectedElements = driver.findElements(By.xpath(itemSelectedXpath));
					if (expectedItems.length == multiItemSelectedElements.size()) {
						break;
					}
				}
			}
		}
	}
	
	public void typeItemIntoEditableDropdown(
			String dropdownXpath, 
			String typeboxXpath,
			String expectedItem) {
		driver.findElement(By.xpath(dropdownXpath)).click();
		driver.findElement(By.xpath(typeboxXpath)).clear();
		driver.findElement(By.xpath(typeboxXpath)).sendKeys(expectedItem);
		action.sendKeys(driver.findElement(By.xpath(typeboxXpath)), Keys.ENTER).perform();
	}
	
	public boolean itemSelectedDisplayedStatus(String itemSelectedXpath) {
		element = driver.findElement(By.xpath(itemSelectedXpath));
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean multiItemsSelectedDisplayedStatus(
			String dropdownXpath, 
			String itemSelectedXpath,
			String[] expectedItems,
			int totalitems) {
		List<WebElement> finalItemSelectedElements = driver.findElements(By.xpath(itemSelectedXpath));
		int toalItemsSelected = finalItemSelectedElements.size();
		ArrayList<String> itemsSelectedTextArray = new ArrayList<String>();
		for (WebElement itemSelectedElement : finalItemSelectedElements) {
			itemsSelectedTextArray.add(itemSelectedElement.getText());
		}
		String textDisplayedOnDropboxUI = driver.findElement(By.xpath(dropdownXpath)).getText();

		if (toalItemsSelected > 0 && toalItemsSelected <= 3) {
			for (String singleItem : expectedItems) {
				if (textDisplayedOnDropboxUI.contains(singleItem)) {
					return true;
				} else
					return false;
			}
		} else if (toalItemsSelected == totalitems) {
			if (textDisplayedOnDropboxUI.contains("All selected")) {
				return true;
			} else
				return false;
		} else {
			return textDisplayedOnDropboxUI.equals(toalItemsSelected + " of " + totalitems + " selected");
		}
		return false;
	}	
	
	public void verifyByDisplay(String itemSelectedXpath) {
		boolean itemSelectedDisplayedStatus = driver.findElement(By.xpath(itemSelectedXpath)).isDisplayed();
		Assert.assertTrue(itemSelectedDisplayedStatus);
	}

	public void verifyByText(String dropdownXpath, String expectedItem) {
		String itemSelectedText = driver.findElement(By.xpath(dropdownXpath)).getText();
		Assert.assertEquals(itemSelectedText, expectedItem);
	}

	public void verifyByJSText(String itemSelectedCss, String expectedItem) {
		String textGetBYJS = (String) javascriptExecutor.executeScript("return document.querySelector('" + itemSelectedCss + "').text");
		Assert.assertEquals(textGetBYJS, expectedItem);
	}
	
	public String getAtributeValue(String elementXpath, String attributeName) {
		element = driver.findElement(By.xpath(elementXpath));
		return element.getAttribute(attributeName);
	}
	
	public String getTextElement(String elementXpath) {
		element = driver.findElement(By.xpath(elementXpath));
		return element.getText();
	}
	
	public int countElementsSize(String elementsXpath) {
		elements = driver.findElements(By.xpath(elementsXpath));
		return elements.size();
	}
	
	public void checkACheckbox(String checkboxXpath) {
		element = driver.findElement(By.xpath(checkboxXpath));
		if (!element.isSelected()) {
			element.click();
		}
	}
		
	public void uncheckACheckbox(String checkboxXpath) {
		element = driver.findElement(By.xpath(checkboxXpath));
		if (element.isSelected()) { 
			element.click();
		}
	}
	
	public boolean isElementDisplayed(String elementXpath) {
		overrideImplicitTimeout(shortTimeout);
		try {
			element = driver.findElement(By.xpath(elementXpath));
			overrideImplicitTimeout(longTimeout);
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			overrideImplicitTimeout(longTimeout);
			return false;
		}
	}
	
	public boolean isElementSelected(String elementXpath) {
		element = driver.findElement(By.xpath(elementXpath));
		return element.isSelected();
	}	
	
	public boolean isElementEnabled(String elementXpath) {
		element = driver.findElement(By.xpath(elementXpath));
		return element.isEnabled();
	}	
	
	public void switchToWindowByID(String parentWindowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String windowID : allWindowIDs) {
			if (!windowID.equals(parentWindowID)) {
				driver.switchTo().window(windowID);
				break;
			}
		}
	}
	
	public void switchToWindowByTitle(String expectedTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String windowID : allWindowIDs) {
			driver.switchTo().window(windowID);
			String currentWindowTitle = driver.getTitle();
			if (currentWindowTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	public void switchToWindowByUrl(String expectedUrl) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String windowID : allWindowIDs) {
			driver.switchTo().window(windowID);
			String currentWindowUrl = driver.getCurrentUrl();
			if (currentWindowUrl.equals(expectedUrl)) {
				break;
			}
		}
	}

	public boolean closeAllWindowsWithoutParent(String parentWindowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		System.out.println("All Windows are running: " + allWindowIDs.size());
		for (String runwindowID : allWindowIDs) {
			if (!runwindowID.equals(parentWindowID)) {
				driver.switchTo().window(runwindowID);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindowID);
		if (driver.getWindowHandles().size() == 1) 
			return true;
		else
			return false;
	}	
	
	public void switchToFrame(String frameXpath) {
		WebElement frameElement = driver.findElement(By.xpath(frameXpath));
		driver.switchTo().frame(frameElement);
	}
	
	public void switchToParentPage() {
		driver.switchTo().defaultContent();
	}
	
	public void bypassPopup(String popupXpath, String closePopupXpath) {
		elements =  driver.findElements(By.xpath(popupXpath));
		if (elements.size() > 0) {
			explicitWait.until(ExpectedConditions.visibilityOf(elements.get(0)));
			driver.findElement(By.xpath(closePopupXpath)).click();
		}
	}
	
	public void doubleClickOnElement(String elementXpath) {
		element = driver.findElement(By.xpath(elementXpath));
		action.doubleClick(element).perform();
	}
	
	public void hoverMouseOnElement(String elementXpath) {
		element = driver.findElement(By.xpath(elementXpath));
		action.moveToElement(element).perform();
	}
	
	public void rightClickOnElement(String elementXpath) {
		element = driver.findElement(By.xpath(elementXpath));
		action.contextClick(element).perform();
	}
	
	public void dragAndDrop(String sourceXpath, String targetXpath) {
		WebElement sourceElement = driver.findElement(By.xpath(sourceXpath));
		WebElement targetElement = driver.findElement(By.xpath(targetXpath));
		action.dragAndDrop(sourceElement, targetElement).perform();
	}
	
	public void sendKeyboardToElement(String elementXpath, Keys key) {
		element = driver.findElement(By.xpath(elementXpath));
		action.sendKeys(element, key).perform();
	}
	
	public void dragAndDropInHTML5Offset(String sourceXpath, String targetXpath) throws AWTException {

		WebElement sourceElement = driver.findElement(By.xpath(sourceXpath));
		WebElement targetElement = driver.findElement(By.xpath(targetXpath));

		Robot robot = new Robot();
		robot.setAutoDelay(500);

		Dimension sourceSize = sourceElement.getSize();
		Dimension targetSize = targetElement.getSize();

		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = sourceElement.getLocation();
		Point targetLocation = targetElement.getLocation();

		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		robot.mouseMove(targetLocation.x, targetLocation.y);

		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}	
	
	public void uploadSingleFileBySendKeys(
			String addFileXpath,
			String startUploadXpath,
			String filePath) {
		driver.findElement(By.xpath(addFileXpath)).sendKeys(filePath);
		driver.findElement(By.xpath(startUploadXpath)).click();
	}
		
	public void uploadMultiFilesBySendKeys(
			String addFilesXpath,
			String startUploadXpath,
			String[] filesPath) {
		
		WebElement addFilesButton = driver.findElement(By.xpath(addFilesXpath));
		for (String filePath : filesPath) {
			addFilesButton.sendKeys(filePath);
			addFilesButton = driver.findElement(By.xpath(addFilesXpath));
		}
				
		List <WebElement> startButtonElements = driver.findElements(By.xpath(startUploadXpath));
		for (WebElement startElement : startButtonElements) {
			startElement.click();
		}
	}
	
	public void uploadFileByRobot(
			String addFileXpath,
			String startUploadXpath,
			String filePath) {

		driver.findElement(By.xpath(addFileXpath)).click();
		
		StringSelection selectfile = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selectfile, null);

		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		driver.findElement(By.xpath(startUploadXpath)).click();
	}
	
	public void uploadFilesByAutoIT(
			String addFilesXpath,
			String startUploadXpath,
			String filePath) {
		
		WebElement addFilesButton = driver.findElement(By.xpath(addFilesXpath));
		String projectDir = System.getProperty("user.dir");
		String autoITForChromePath = projectDir + "\\resources\\AutoITForChrome.exe";
		String autoITForFirefoxPath = projectDir + "\\resources\\AutoITForFirefox.exe";
		
		addFilesButton.click();
		
		if (driver.toString().contains("Firefox")) {
			try {
				Runtime.getRuntime().exec(new String[] {autoITForFirefoxPath, filePath});
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				Runtime.getRuntime().exec(new String[] {autoITForChromePath, filePath});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		driver.findElement(By.xpath(startUploadXpath)).click();
	}
	
	public Object executeJavascript(String javaSript) {
		return javascriptExecutor.executeScript(javaSript);
	}
	
	public void scrollToBottomPage() {
		javascriptExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void scrollToElement(String elementXpath) {
		element = driver.findElement(By.xpath(elementXpath));
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void highlightElement(String elementXpath) {
		element = driver.findElement(By.xpath(elementXpath));
		String originalStyle = element.getAttribute("style");
		javascriptExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 3px solid red; border-style: dashed;");
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		javascriptExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}
	
	public void removeElementAttribute(String elementXpath, String attributeName) {
		element = driver.findElement(By.xpath(elementXpath));
		javascriptExecutor.executeScript("arguments[0].removeAttribute('" + attributeName + "');", element);
	}
	
	public boolean checkAnyImageLoaded(String elementXpath) {
		element = driver.findElement(By.xpath(elementXpath));
		boolean status = (boolean) javascriptExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWith !=\"underfined\" && arguments[0].naturalWith > 0", element);
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean verifyTextInInnerText(String textExpected) {
		String textActual = (String) javascriptExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		System.out.println("Text actual = " + textActual);
		return textActual.equals(textExpected);
	}

	public void navigateToUrlByJSE(String url) {
		javascriptExecutor.executeScript("window.location = '" + url + "'");
	}

	public void clickOnElementByJSE(String elementXpath) {
		element = driver.findElement(By.xpath(elementXpath));
		javascriptExecutor.executeScript("arguments[0].click();", element);
	}

	public void sendKeysToElementByJSE(String elementXpath, String value) {
		element = driver.findElement(By.xpath(elementXpath));
		javascriptExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}
	
	public void waitForElementVisibleByXpath(String elementXpath) {
		byXpath = By.xpath(elementXpath);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
	}
	
	public void waitForAllElementVisibleByXpath(String elementsXpath) {
		byXpath = By.xpath(elementsXpath);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath));
	}
	
	public void waitForElementVisibleByElement(WebElement element) {
		explicitWait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementInvisibleByXpath(String elementXpath) {
		byXpath = By.xpath(elementXpath);
		overrideImplicitTimeout(shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath));
		overrideImplicitTimeout(longTimeout);
	}
	
	public void waitForElementPresentleByXpath(String elementXpath) {
		byXpath = By.xpath(elementXpath);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(byXpath));
	}
	
	public void waitForElementClickableByXpath(String elementXpath) {
		byXpath = By.xpath(elementXpath);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath));
	}
	
	public void waitForElementClickable(WebElement element) {
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForAlertPresentByXpath() {
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public int randomNumber() {
		Random number = new Random();
		return number.nextInt(10000);	
	}
	
	public void sleepInSecond(long numberInSecond) {
		try {
			Thread.sleep(numberInSecond * 1000);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}
	
	public String byPassAuthenAlert(String url, String username, String password) {
		String[] splitUrl = url.split("//");
		url = splitUrl[0] + "//" + username + ":" + password + "@" + splitUrl[1];
		return url;
	}

	public String readFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			reader.close();
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	public void overrideImplicitTimeout(long newTimeout) {
		driver.manage().timeouts().implicitlyWait(newTimeout, TimeUnit.SECONDS);
	}
	
	public FE_HomePO openHomePageByLogo() {
		waitForElementVisibleByXpath(AbstractPageUI.HEADER_HOME_LOGO_LINK);
		clickOnElement(AbstractPageUI.HEADER_HOME_LOGO_LINK);
		return PageGeneratorManager.getHomePage(driver);
	}
	
	public FE_HomePO openLogoutLink() {
		waitForElementVisibleByXpath(AbstractPageUI.HEADER_LOGOUT_LINK);
		clickOnElement(AbstractPageUI.HEADER_LOGOUT_LINK);
		return PageGeneratorManager.getHomePage(driver);
	}
	
	public FE_WishlistPO openHeaderWishlistLink() {
		waitForElementVisibleByXpath(AbstractPageUI.HEADER_WISHLIST_LINK);
		clickOnElement(AbstractPageUI.HEADER_WISHLIST_LINK);
		return PageGeneratorManager.getWishlistPage(driver);
	}
	
	public FE_WishlistPO openFooterWishlistLink() {
		waitForElementVisibleByXpath(AbstractPageUI.FOOTER_WISHLIST_LINK);
		clickOnElement(AbstractPageUI.FOOTER_WISHLIST_LINK);
		return PageGeneratorManager.getWishlistPage(driver);
	}
	
	public FE_ShoppingCartPO openHeaderShoppingCartLink() {
		waitForElementVisibleByXpath(AbstractPageUI.HEADER_SHOPPING_CART_LINK);
		clickOnElement(AbstractPageUI.HEADER_SHOPPING_CART_LINK);
		return PageGeneratorManager.getShoppingCartPage(driver);
	}
	
	public FE_ShoppingCartPO openFooterShoppingCartLink() {
		waitForElementVisibleByXpath(AbstractPageUI.FOOTER_SHOPPING_CART_LINK);
		clickOnElement(AbstractPageUI.FOOTER_SHOPPING_CART_LINK);
		return PageGeneratorManager.getShoppingCartPage(driver);
	}
	
	public FE_RegisterPO openRegisterLink() {
		waitForElementVisibleByXpath(AbstractPageUI.HEADER_REGISTER_LINK);
		clickOnElement(AbstractPageUI.HEADER_REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}
	
	public FE_LoginPO openLoginLink() {
		waitForElementVisibleByXpath(AbstractPageUI.HEADER_LOGIN_LINK);
		clickOnElement(AbstractPageUI.HEADER_LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}	
	
	public FE_MyAccountPO openHeaderMyAccountLink() {
		waitForElementVisibleByXpath(AbstractPageUI.HEADER_MYACCOUNT_LINK);
		clickOnElement(AbstractPageUI.HEADER_MYACCOUNT_LINK);
		return PageGeneratorManager.getMyAccountPage(driver);
	}
	
	public FE_SitemapPO openFooterSitemapLink() {
		waitForElementVisibleByXpath(AbstractPageUI.FOOTER_SITEMAP_LINK);
		clickOnElement(AbstractPageUI.FOOTER_SITEMAP_LINK);
		return PageGeneratorManager.getSitemapPage(driver);
	}
	
	public FE_ShippingAndReturnsPO openShippingAndReturnsLink() {
		waitForElementVisibleByXpath(AbstractPageUI.FOOTER_SHIPPING_AND_RETURNS_LINK);
		clickOnElement(AbstractPageUI.FOOTER_SHIPPING_AND_RETURNS_LINK);
		return PageGeneratorManager.getShippingAndReturnsPage(driver);
	}
	
	public FE_SearchPO openFooterSearchLink() {
		waitForElementVisibleByXpath(AbstractPageUI.FOOTER_SEARCH_LINK);
		clickOnElement(AbstractPageUI.FOOTER_SEARCH_LINK);
		return PageGeneratorManager.getSearchPage(driver);
	}
	
	public FE_NewsPO openFooterNewsLink() {
		waitForElementVisibleByXpath(AbstractPageUI.FOOTER_NEWS_LINK);
		clickOnElement(AbstractPageUI.FOOTER_NEWS_LINK);
		return PageGeneratorManager.getNewsPage(driver);
	}
	
	public FE_MyAccountPO openFooterMyAccountLink() {
		waitForElementVisibleByXpath(AbstractPageUI.FOOTER_MYACCOUNT_LINK);
		clickOnElement(AbstractPageUI.FOOTER_MYACCOUNT_LINK);
		return PageGeneratorManager.getMyAccountPage(driver);
	}
	
	public FE_OrderDetailsPO openFooterOrdersLink() {
		waitForElementVisibleByXpath(AbstractPageUI.FOOTER_ORDERS_LINK);
		clickOnElement(AbstractPageUI.FOOTER_ORDERS_LINK);
		return PageGeneratorManager.getOrdersPage(driver);
	}
	
	public FE_FacebookPO openFooterFacebookLink() {
		waitForElementVisibleByXpath(AbstractPageUI.FOOTER_FACEBOOK_LINK);
		clickOnElement(AbstractPageUI.FOOTER_FACEBOOK_LINK);
		return PageGeneratorManager.getFacebookPage(driver);
	}
	
	public FE_TwitterPO openFooterTwitterLink() {
		waitForElementVisibleByXpath(AbstractPageUI.FOOTER_TWITTER_LINK);
		clickOnElement(AbstractPageUI.FOOTER_TWITTER_LINK);
		return PageGeneratorManager.getTwitterPage(driver);
	}
	
	public String castRestParameter (String elementXpath, String... dynamicValues) {
		elementXpath = String.format(elementXpath, (Object[]) dynamicValues);
		return elementXpath;
	}
	
	public String castRestParameterByIndex (String elementXpath, int dynamicValues) {
		elementXpath = String.format(elementXpath, dynamicValues);
		return elementXpath;
	}
	
	public void clickOnDynamicElement(String elementXpath, String... dynamicValues) {
		elementXpath = castRestParameter(elementXpath, dynamicValues);
		element = driver.findElement(By.xpath(elementXpath));
		element.click();
	}
	
	public void clickOnDynamicElementByJSE(String elementXpath, String... dynamicValues) {
		elementXpath = castRestParameter(elementXpath, dynamicValues);
		clickOnElementByJSE(elementXpath);
	}
	
	public void sendKeysToDynamicElement(String elementXpath, String inputValue, String... dynamicValues ) {
		elementXpath = castRestParameter(elementXpath, dynamicValues);
		element = driver.findElement(By.xpath(elementXpath));
		element.clear();
		element.sendKeys(inputValue);
	}
	
	public String getTextDynamicElement(String elementXpath, String... dynamicValues) {
		elementXpath = castRestParameter(elementXpath, dynamicValues);
		element = driver.findElement(By.xpath(elementXpath));
		return element.getText();
	}
	
	public boolean isDynamicElementDisplayed(String elementXpath, String... dynamicValues) {
		elementXpath = castRestParameter(elementXpath, dynamicValues);
		overrideImplicitTimeout(shortTimeout);
		try {
			element = driver.findElement(By.xpath(elementXpath));
			overrideImplicitTimeout(longTimeout);
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			overrideImplicitTimeout(longTimeout);
			return false;
		}
	}
	
	public boolean isDynamicElementEnabled(String elementXpath, String... dynamicValues) {
		elementXpath = castRestParameter(elementXpath, dynamicValues);
		overrideImplicitTimeout(shortTimeout);
		try {
			element = driver.findElement(By.xpath(elementXpath));
			overrideImplicitTimeout(longTimeout);
			return element.isEnabled();
		} catch (NoSuchElementException e) {
			overrideImplicitTimeout(longTimeout);
			return false;
		}
	}
	
	public void waitForDynamicElementVisibleByXpath(String elementXpath, String... dynamicValues) {
		elementXpath = castRestParameter(elementXpath, dynamicValues);
		byXpath = By.xpath(elementXpath);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
	}
	
	public void waitForDynamicElementInvisibleByXpath(String elementXpath, String... dynamicValues) {
		elementXpath = castRestParameter(elementXpath, dynamicValues);
		byXpath = By.xpath(elementXpath);
		overrideImplicitTimeout(shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath));
		overrideImplicitTimeout(longTimeout);
	}
	
	public void waitForDynamicElementClickableByXpath(String elementXpath, String... dynamicValues) {
		elementXpath = castRestParameter(elementXpath, dynamicValues);
		byXpath = By.xpath(elementXpath);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath));
	}

	public List<WebElement> findDynamicElementsByXpath(String elementsXpath, String... dynamicValues) {
		elementsXpath = castRestParameter(elementsXpath, dynamicValues);
		return driver.findElements(By.xpath(elementsXpath));
	}
	
	public AbstractPO openMultiFooterPages(String linkText) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_FOOTER_LINK, linkText);
		clickOnDynamicElement(AbstractPageUI.DYNAMIC_FOOTER_LINK, linkText);
		switch (linkText) {
		case "Search":
			return PageGeneratorManager.getSearchPage(driver);
		case "Shipping & returns":
			return PageGeneratorManager.getShippingAndReturnsPage(driver);
		case "Sitemap":
			return PageGeneratorManager.getSitemapPage(driver);
		case "My account":
			return PageGeneratorManager.getMyAccountPage(driver);
		case "Orders":
			return PageGeneratorManager.getOrdersPage(driver);
		case "Wishlist":
			return PageGeneratorManager.getWishlistPage(driver);
		case "Facebook":
			return PageGeneratorManager.getFacebookPage(driver);
		default:
			return PageGeneratorManager.getHomePage(driver);
		}
	}

	public AbstractPO openDynamicHeaderPage(String pageLink) { 
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_HEADER_LINK_1, pageLink);
		clickOnDynamicElement(AbstractPageUI.DYNAMIC_HEADER_LINK_1, pageLink);
		switch (pageLink) {
		case "Register":
			return PageGeneratorManager.getRegisterPage(driver);
		case "Log in":
			return PageGeneratorManager.getLoginPage(driver);
		case "My account":
			return PageGeneratorManager.getMyAccountPage(driver);
		case "Log out":
			return PageGeneratorManager.getHomePage(driver);
		default:
			return PageGeneratorManager.getHomePage(driver);
		}
	}
		
	public AbstractPO openDynamicHeaderLink(String pageLink) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_HEADER_LINK_2, pageLink);
		clickOnDynamicElement(AbstractPageUI.DYNAMIC_HEADER_LINK_2, pageLink);
		switch (pageLink) {
		case "Wishlist":
			return PageGeneratorManager.getWishlistPage(driver);
		case "Shopping cart":
			return PageGeneratorManager.getShoppingCartPage(driver);
		default:
			return PageGeneratorManager.getHomePage(driver);
		}
	}

	public void openDynamicFooterPage(String pageLink) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_FOOTER_LINK, pageLink);
		clickOnDynamicElement(AbstractPageUI.DYNAMIC_FOOTER_LINK, pageLink);
	}
	
	public void selectItemInDynamicHTMLDropdown(String dropdownXpath, String expectedItem, String...dynamicValues) {
		dropdownXpath = castRestParameter(dropdownXpath, dynamicValues);
		element = driver.findElement(By.xpath(dropdownXpath));
		select = new Select(element);
		select.selectByVisibleText(expectedItem);
	}
	
	public void deSelectItemInDynamicHTMLDropdown(String dropdownXpath, String expectedItem, String...dynamicValues) {
		dropdownXpath = castRestParameter(dropdownXpath, dynamicValues);
		element = driver.findElement(By.xpath(dropdownXpath));
		select = new Select(element);
		select.deselectByVisibleText(expectedItem);
	}
	
	public void selectOptionByIndexInDynamicHTMLDropdown(String dropdownXpath, int optionIndex, String...dynamicValues) {
		dropdownXpath = castRestParameter(dropdownXpath, dynamicValues);
		element = driver.findElement(By.xpath(dropdownXpath));
		select = new Select(element);
		select.selectByIndex(optionIndex);
	}
	
	public void inputToDynamicTextbox(String textboxID, String inputValue) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_TEXTBOX, textboxID);
		sendKeysToDynamicElement(AbstractPageUI.DYNAMIC_TEXTBOX, inputValue, textboxID);
	}
	
	public void inputToDynamicTextarea(String textareaID, String inputValue) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_TEXTAREA, textareaID);
		sendKeysToDynamicElement(AbstractPageUI.DYNAMIC_TEXTAREA, inputValue, textareaID);
	}
	
	public String getTextFromDynamicTextboxOrTextarea(String textboxareaID) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA, textboxareaID);
		return getDynamicAtributeValue(AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA, "value", textboxareaID);
	}
	
	public boolean isDynamicTextboxOrTextareaValueAsExpectation(String textboxareaID, String expectedValue) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA, textboxareaID);
		return expectedValue.contains(getDynamicAtributeValue(AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA, "value", textboxareaID));
	}
	
	public String getDynamicTextboxAttributeValueLive(String attributeName, String... textboxID) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA, textboxID);
		return getDynamicAtributeValue(AbstractPageUI.DYNAMIC_TEXTBOX_TEXTAREA, attributeName, textboxID);
	}
	
	public void clickOnDynamicButton(String buttonValue) {
		waitForDynamicElementClickableByXpath(AbstractPageUI.DYNAMIC_BUTTON, buttonValue);
		clickOnDynamicElement(AbstractPageUI.DYNAMIC_BUTTON, buttonValue);
		sleepInSecond(2);
	}
	
	public void clickOnDynamicProductOverviewActionButton(String buttonValue) {
		waitForDynamicElementClickableByXpath(AbstractPageUI.PRODUCT_OVERVIEW_ACTION_BUTTON, buttonValue);
		clickOnDynamicElement(AbstractPageUI.PRODUCT_OVERVIEW_ACTION_BUTTON, buttonValue);
	}
	
	public void clickOnDynamicRadioButton(String radioButtonID) {
		waitForDynamicElementClickableByXpath(AbstractPageUI.DYNAMIC_RADIOBUTTON, radioButtonID);
		clickOnDynamicElement(AbstractPageUI.DYNAMIC_RADIOBUTTON, radioButtonID);
	}
	
	public void clickToCheckDynamicCheckbox(String checkboxID) {
		waitForDynamicElementClickableByXpath(AbstractPageUI.DYNAMIC_CHECKBOX, checkboxID);
		if (!isDynamicElementSelected(AbstractPageUI.DYNAMIC_CHECKBOX, checkboxID))
			clickOnDynamicElement(AbstractPageUI.DYNAMIC_CHECKBOX, checkboxID);
	}
	
	public void clickToUncheckDynamicCheckbox(String checkboxID) {
		waitForDynamicElementClickableByXpath(AbstractPageUI.DYNAMIC_CHECKBOX, checkboxID);
		if (isDynamicElementSelected(AbstractPageUI.DYNAMIC_CHECKBOX, checkboxID))
			clickOnDynamicElement(AbstractPageUI.DYNAMIC_CHECKBOX, checkboxID);
	}
	
	public void clickToCheckDynamicRadioButtonOrChecbkboxByLabel(String labelValue) {
		waitForDynamicElementClickableByXpath(AbstractPageUI.DYNAMIC_RADIO_BUTTON_CHECKBOX_BY_LABEL, labelValue);
		if (!isDynamicElementSelected(AbstractPageUI.DYNAMIC_RADIO_BUTTON_CHECKBOX_BY_LABEL, labelValue))
		clickOnDynamicElement(AbstractPageUI.DYNAMIC_RADIO_BUTTON_CHECKBOX_BY_LABEL, labelValue);
	}
	
	public void clickToUncheckDynamicCheckboxByLabel(String labelValue) {
		waitForDynamicElementClickableByXpath(AbstractPageUI.DYNAMIC_RADIO_BUTTON_CHECKBOX_BY_LABEL, labelValue);
		if (isDynamicElementSelected(AbstractPageUI.DYNAMIC_RADIO_BUTTON_CHECKBOX_BY_LABEL, labelValue))
			clickOnDynamicElement(AbstractPageUI.DYNAMIC_RADIO_BUTTON_CHECKBOX_BY_LABEL, labelValue);
		}
	
	public void selectItemInDynamicDropdown(String dropdownName, String expectedItem) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_DROPDOWN_LIST_BY_NAME, dropdownName);
		selectItemInDynamicHTMLDropdown(AbstractPageUI.DYNAMIC_DROPDOWN_LIST_BY_NAME, expectedItem, dropdownName);
	}
	
	public void selectItemInDynamicDropdownByID(String dropdownID, String expectedItem) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_DROPDOWN_LIST_BY_ID, dropdownID);
		selectItemInDynamicHTMLDropdown(AbstractPageUI.DYNAMIC_DROPDOWN_LIST_BY_ID, expectedItem, dropdownID);
		sleepInSecond(1);
	}
	
	public void selectExistedAddressInDynamicDropdownByID(String dropdownID, int optionIndex) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_DROPDOWN_LIST_BY_ID, dropdownID);
		selectOptionByIndexInDynamicHTMLDropdown(AbstractPageUI.DYNAMIC_DROPDOWN_LIST_BY_ID, optionIndex, dropdownID);
		sleepInSecond(1);
	}
	
	public boolean isSelectedItemInDynamicDropdownDisplayed(String dropdownName, String selectedItem) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_DROPDOWN_ITEM_SELECTED_BY_NAME, dropdownName, selectedItem);
		return isDynamicElementDisplayed(AbstractPageUI.DYNAMIC_DROPDOWN_ITEM_SELECTED_BY_NAME, dropdownName, selectedItem);
	}
	
	public boolean isSelectedItemInDynamicDropdownDisplayedByID(String dropdownID, String selectedItem) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_DROPDOWN_ITEM_SELECTED_BY_ID, dropdownID, selectedItem);
		return isDynamicElementSelected(AbstractPageUI.DYNAMIC_DROPDOWN_ITEM_SELECTED_BY_ID, dropdownID, selectedItem);
	}
	
	public boolean isDynamicErrorMessageDisplayed(String fieldName, String errorMsg) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_REQUIRED_FIELD_ERROR_MGS, fieldName, errorMsg);
		return isDynamicElementDisplayed(AbstractPageUI.DYNAMIC_REQUIRED_FIELD_ERROR_MGS, fieldName, errorMsg);
	}
	
	public boolean isDynamicPageFormDisplayed(String pageName) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_PAGE_FORM, pageName);
		return isDynamicElementDisplayed(AbstractPageUI.DYNAMIC_PAGE_FORM, pageName);
	}
	
	public String getDynamicRequiredFieldErrorMgs(String fieldName) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_REQUIRED_FIELD_ERROR_MGS_TEXT, fieldName);
		return getTextDynamicElement(AbstractPageUI.DYNAMIC_REQUIRED_FIELD_ERROR_MGS_TEXT, fieldName);
	}
	
	public String getDynamicTextboxAttributeValue(String attributeName, String... textboxID) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_TEXTBOX, textboxID);
		return getDynamicAtributeValue(AbstractPageUI.DYNAMIC_TEXTBOX, attributeName, textboxID);
	}
	
	public boolean isRadioButtonSelected(String radioButtonID) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_RADIOBUTTON, radioButtonID);
		return isDynamicElementSelected(AbstractPageUI.DYNAMIC_RADIOBUTTON, radioButtonID);
	}
	
	public boolean isCheckboxSelected(String checkboxID) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_CHECKBOX, checkboxID);
		return isDynamicElementSelected(AbstractPageUI.DYNAMIC_CHECKBOX, checkboxID);
	}
	
	public boolean isDynamicElementSelected(String dynamicXpath, String...dynamicValues) {
		String elementXpath = castRestParameter(dynamicXpath, dynamicValues);
		waitForElementVisibleByXpath(elementXpath);
		element = driver.findElement(By.xpath(elementXpath));
		return element.isSelected();
	}
	
	public boolean isDynamicHeaderPageDisplayed(String pageLink) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_HEADER_LINK_1, pageLink);
		return isDynamicElementDisplayed(AbstractPageUI.DYNAMIC_HEADER_LINK_1, pageLink);
	}

	public void clickOnDynamicAddToCartButton(String productName) {
		waitForDynamicElementClickableByXpath(AbstractPageUI.DYNAMIC_ADDTOCART_BUTTON, productName);
		clickOnDynamicElement(AbstractPageUI.DYNAMIC_ADDTOCART_BUTTON, productName);
	}
	
	public String getResultMessageText() {
		waitForElementVisibleByXpath(AbstractPageUI.RESULT_MESSAGE_TEXT);
		return getTextDynamicElement(AbstractPageUI.RESULT_MESSAGE_TEXT);
	}
	
	public void hoverOnDynamicHeaderMenu(String menuText) { 
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_HEADER_MENU, menuText);
		hoverOnDynamicElement(AbstractPageUI.DYNAMIC_HEADER_MENU, menuText);
	}
	
	public void hoverOnHeaderTopCartLink() { 
		waitForElementVisibleByXpath(AbstractPageUI.HEADER_TOP_CART_LINK);
		hoverMouseOnElement(AbstractPageUI.HEADER_TOP_CART_LINK);
	}
	
	public void clickOnDynamicHeaderMenu(String menuText) { 
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_HEADER_MENU, menuText);
		clickOnDynamicElement(AbstractPageUI.DYNAMIC_HEADER_MENU, menuText);
	}
	
	public void clickOnDynamicHeaderSubMenu(String menuText) { 
		if (driver.toString().contains("Firefox")) {
			clickOnDynamicElementByJSE(AbstractPageUI.DYNAMIC_HEADER_MENU, menuText);
		} else {
			waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_HEADER_MENU, menuText);
			clickOnDynamicElement(AbstractPageUI.DYNAMIC_HEADER_MENU, menuText);
		}
	}
	
	public void clickOnDynamicHeaderMenuByJSE(String menuText) { 
		clickOnDynamicElementByJSE(AbstractPageUI.DYNAMIC_HEADER_MENU, menuText);
	}
	
	public void clickandHoldOnDynamicHeaderMainMenu(String menuText) { 
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_HEADER_MENU, menuText);
		clickAndHoldOnDynamicElement(AbstractPageUI.DYNAMIC_HEADER_MENU, menuText);
	}
	
	public void clickOnDynamicProductName(String productName) { 
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_PRODUCT_BY_NAME, productName);
		clickOnDynamicElement(AbstractPageUI.DYNAMIC_PRODUCT_BY_NAME, productName);
	}
	
	public boolean isDynamicProductNameDisplayedOnPage(String productName) { 
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_PRODUCT_BY_NAME, productName);
		return isDynamicElementDisplayed(AbstractPageUI.DYNAMIC_PRODUCT_BY_NAME, productName);
	}
	
	public boolean isFlyoutCartDisplayed() { 
		waitForElementVisibleByXpath(AbstractPageUI.FLYOUT_TOP_CART_DISPLAYED);
		return isElementDisplayed(AbstractPageUI.FLYOUT_TOP_CART_DISPLAYED);
	}
	
	public String getTextActionSuccessMessage() {
		waitForElementVisibleByXpath(AbstractPageUI.ACTION_SUCCESS_MSG);
		return getTextElement(AbstractPageUI.ACTION_SUCCESS_MSG);
	}
	
	public int getTotalNumberProductTitleDisplayedOnPage() {
		waitForAllElementVisibleByXpath(AbstractPageUI.ALL_PRODUCT_TITLE_DISPLAYED_ON_PAGE);
		return findElementsByXpath(AbstractPageUI.ALL_PRODUCT_TITLE_DISPLAYED_ON_PAGE).size();
	}
	
	public String getTextMiniShoppingCartQuantity() {
		waitForAllElementVisibleByXpath(AbstractPageUI.HEADER_MINI_SHOPPING_CART_QUANTITY_TEXT);
		return getTextElement(AbstractPageUI.HEADER_MINI_SHOPPING_CART_QUANTITY_TEXT);
	}
	
	public String getTextProductDetailPrice() {
		sleepInSecond(3);
		waitForAllElementVisibleByXpath(AbstractPageUI.PRODUCT_DETAIL_PRICE);
		return getTextElement(AbstractPageUI.PRODUCT_DETAIL_PRICE);
	}
	public String getTextProducAttributes() {
		waitForAllElementVisibleByXpath(AbstractPageUI.PRODUCT_ATTRIBUTE_ALL_TEXT);
		return getTextElement(AbstractPageUI.PRODUCT_ATTRIBUTE_ALL_TEXT);
	}

	public void clickOnNotificationBarCloseButton() { 
		waitForElementClickableByXpath(AbstractPageUI.NOTIFICATION_BAR_CLOSE_BUTTON);
		clickOnElement(AbstractPageUI.NOTIFICATION_BAR_CLOSE_BUTTON);
		sleepInSecond(2);
	}
	
	public void clickOnEditProdructAttributesLink() { 
		waitForElementClickableByXpath(AbstractPageUI.PRODUCT_ATTRIBUTE_EDIT_LINK);
		clickOnElement(AbstractPageUI.PRODUCT_ATTRIBUTE_EDIT_LINK);
	}
	
	public boolean isMiniShoppingCartProductDetailDisplayed() { 
		waitForElementVisibleByXpath(AbstractPageUI.HEADER_MINI_SHOPPING_CART_PRODUCT_ATTRIBUTE);
		return isElementDisplayed(AbstractPageUI.HEADER_MINI_SHOPPING_CART_PRODUCT_ATTRIBUTE);
	}
	
	public String getTextDynamicMiniShoppingCartByLabel(String labelName) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_HEADER_MINI_SHOPPING_CART_LABEL, labelName);
		return getTextDynamicElement(AbstractPageUI.DYNAMIC_HEADER_MINI_SHOPPING_CART_LABEL, labelName);
	}
	
	public boolean isDynamicMiniShoppingCartProductNameDisplayed(String productName) {
		boolean result = false;
		List<WebElement> elementList = driver.findElements(By.xpath(AbstractPageUI.HEADER_MINI_SHOPPING_CART_PRODUCT_NAME_ALL));
		for (WebElement element : elementList) {
			String displayedValue = element.getText();
			if (displayedValue.contains(productName)) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	public void removeAllProduct() {
		if (isElementDisplayed(AbstractPageUI.PAGE_BODY_NO_DATA_TEXT) == false) {
			checkAllDynamicCheckboxInWishlistByColumn("remove-from-cart");
			clickOnUpdateButton();
		}
	}
	
	public void clickOnUpdateButton() {
		waitForDynamicElementClickableByXpath(AbstractPageUI.UPDATE_BUTTON);
		clickOnDynamicElement(AbstractPageUI.UPDATE_BUTTON);
	}
	
	public boolean isProductNameDisplayedInTable(String productName) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_TABLE_PRODUCT_NAME, productName);
		return isDynamicElementDisplayed(AbstractPageUI.DYNAMIC_TABLE_PRODUCT_NAME, productName);
	}
	
	public boolean isProductNameUnDisplayedInTable(String productName) {
		waitForDynamicElementInvisibleByXpath(AbstractPageUI.DYNAMIC_TABLE_PRODUCT_NAME, productName);
		return isDynamicElementDisplayed(AbstractPageUI.DYNAMIC_TABLE_PRODUCT_NAME, productName);
		}
	
	public boolean isAllProductNameDisplayedInTable(String productName) {
		boolean result = false;
		List<WebElement> elementList = driver.findElements(By.xpath(AbstractPageUI.TABLE_COLUMN_PRODUCT_NAME_ALL));
		for (WebElement element : elementList) {
			String displayedValue = element.getText();
			if (displayedValue.contains(productName)) {
				result = true;
				break;
			}
		}
		return result;
	}
		
	public String getTextPageBodyMessage() {
		waitForElementVisibleByXpath(AbstractPageUI.PAGE_BODY_NO_DATA_TEXT);
		return getTextElement(AbstractPageUI.PAGE_BODY_NO_DATA_TEXT);
	}
	
	public boolean isDynamicPageBodyMessageDisplayed(String message) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.WISHLIST_SHOPPING_CART_PAGE_BODY_MSG_DISPLAYED, message);
		return isDynamicElementDisplayed(AbstractPageUI.WISHLIST_SHOPPING_CART_PAGE_BODY_MSG_DISPLAYED, message);
	}
	
	public void checkAllDynamicCheckboxInWishlistByColumn(String columnClassValue) {
		String colunmXpath = castRestParameter(AbstractPageUI.DYNAMIC_TABLE_COLUMN_BY_CLASS_VALUE_ALL, columnClassValue);
		List<WebElement> checkboxList = driver.findElements(By.xpath(colunmXpath));
		for (WebElement checkbox : checkboxList) {
			waitForElementClickable(checkbox);
			if (!checkbox.isSelected()) {
				checkbox.click();
			}
		}
	}
	
	public void clickToCheckDynamicCheckboxByNameAndColumn(String productName, String columnClassValue) {
		String checkboxXpath = castRestParameter(AbstractPageUI.DYNAMIC_TABLE_COLUMN_BY_NAME, productName, columnClassValue);
		waitForElementClickableByXpath(checkboxXpath);
		if (!isElementSelected(checkboxXpath)) {
			clickOnElement(checkboxXpath);
		}
	}
		
	public boolean isAllCheckboxInColumnAreChecked(String columnClassValue) {
		boolean result = true; 
		String colunmXpath = castRestParameter(AbstractPageUI.DYNAMIC_TABLE_COLUMN_BY_CLASS_VALUE_ALL, columnClassValue);
		List<WebElement> checkboxList = driver.findElements(By.xpath(colunmXpath));
		for (WebElement checkbox : checkboxList) {
			if (!checkbox.isSelected()) {
				result =  false;
				break;
			}
		}
		return result;	
	}
	
	public boolean isAllCheckboxInColumnAreUnchecked(String columnClassValue) {
		boolean result = true;
		String colunmXpath = castRestParameter(AbstractPageUI.DYNAMIC_TABLE_COLUMN_BY_CLASS_VALUE_ALL, columnClassValue);
		List<WebElement> checkboxList = driver.findElements(By.xpath(colunmXpath));
		for (WebElement checkbox : checkboxList) {
			if (checkbox.isSelected()) {
				result =  false;
				break;
			}
		}
		return result;	
	}
	
	public void addDynamicProductToComparelistByName(String productName, String buttonValue) {
		waitForDynamicElementClickableByXpath(AbstractPageUI.DYNAMIC_PRODUCT_ACTION_BY_NAME, productName, buttonValue);
		clickOnDynamicElement(AbstractPageUI.DYNAMIC_PRODUCT_ACTION_BY_NAME, productName, buttonValue);
	}
	
	public void inputToDynamicQuantityTextboxByProductname(String productName, String inputValue) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_PRODUCT_QUANTITY_TEXTBOX_BY_NAME, productName);
		sendKeysToDynamicElement(AbstractPageUI.DYNAMIC_PRODUCT_QUANTITY_TEXTBOX_BY_NAME, inputValue, productName);
	}
	
	public String getTextDynamicShoppingCartProductTotalPrice(String productName) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_PRODUCT_TOTAL_PRICE_BY_NAME, productName);
		return getTextDynamicElement(AbstractPageUI.DYNAMIC_PRODUCT_TOTAL_PRICE_BY_NAME, productName);
	}
	
	public String getTextDynamicAttributeByProductname(String productName, String columnClassValue) {
		waitForDynamicElementVisibleByXpath(AbstractPageUI.DYNAMIC_TABLE_COLUMN_TEXT_BY_NAME, productName, columnClassValue);
		return getTextDynamicElement(AbstractPageUI.DYNAMIC_TABLE_COLUMN_TEXT_BY_NAME, productName, columnClassValue);
	}
	
	public String getTextPageTitle() {
		waitForElementVisibleByXpath(AbstractPageUI.PAGE_TITLE_TEXT);
		return getTextElement(AbstractPageUI.PAGE_TITLE_TEXT);
	}
	
	public void removeDynamicElementAttribute(String elementXpath, String attributeName, String... dynamicValues) {
		elementXpath = castRestParameter(elementXpath, dynamicValues);
		element = driver.findElement(By.xpath(elementXpath));
		javascriptExecutor.executeScript("arguments[0].removeAttribute('" + attributeName + "');", element);
	}
	
	public String getDynamicAtributeValue(String elementXpath, String attributeName, String... dynamicValues) {
		elementXpath = castRestParameter(elementXpath, dynamicValues);
		element = driver.findElement(By.xpath(elementXpath));
		return element.getAttribute(attributeName);
	}

	public void getArrayListTextOfElements(WebDriver driver, String listElementXpath) {
		ArrayList<String> arrayListText = new ArrayList<String>();
		List<WebElement> elementList = driver.findElements(By.xpath(listElementXpath));
		for (WebElement element : elementList) {
			arrayListText.add(element.getText());
		}
	}

	public int getSizeListElements(String listElementsXpath) {
		List<WebElement> elementList = driver.findElements(By.xpath(listElementsXpath)) ;
		return elementList.size();
	}
	
	public void waitForListElementsVisibleByXpath(String elementsXpath) {
		byXpath = byXpath(elementsXpath);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath));
	}

	public void hoverOnDynamicElement(String elementXpath, String...dynamicValues) {
		elementXpath = castRestParameter(elementXpath, dynamicValues);
		element = driver.findElement(By.xpath(elementXpath));
		action.moveToElement(element).perform();
	}
	
	public void clickAndHoldOnDynamicElement(String elementXpath, String...dynamicValues) {
		elementXpath = castRestParameter(elementXpath, dynamicValues);
		element = driver.findElement(By.xpath(elementXpath));
		action.moveToElement(element).clickAndHold().build().perform();
	}
	
	public void goToPageByPageNumber(int pageNumber) {
		WebElement pageTextbox = driver.findElement(By.xpath("//input[@name='page']"));
		waitForElementVisibleByElement(pageTextbox);
		pageTextbox.clear();
		pageTextbox.sendKeys(Integer.toString(pageNumber));
		pageTextbox.sendKeys(Keys.ENTER);
	}
	
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();
		for (int i = 0; i < dir_contents.length; i++) {
			if (StringUtils.containsIgnoreCase(dir_contents[i].getName(), fileName))
				return flag = true;
		}
		return flag;
	}
	
	public boolean isSortedStringAscending(WebDriver driver, String locator) {
		ArrayList<String> arrayList = new ArrayList<String>();
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		ArrayList<String> sortingArray = new ArrayList<String>();
		for (String child : arrayList) {
			sortingArray.add(child);
		}
		Collections.sort(sortingArray);
		return sortingArray.equals(arrayList);
	}

	public boolean isSortedStringDescending(WebDriver driver, String locator) {
		ArrayList<String> arrayList = new ArrayList<String>();
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		ArrayList<String> sortingArray = new ArrayList<String>();
		for (String child : arrayList) {
			sortingArray.add(child);
		}
		Collections.sort(sortingArray);
		Collections.reverse(sortingArray);
		return sortingArray.equals(arrayList);
	}

	public boolean isSortedFloatAscending(WebDriver driver, String locator) {
		ArrayList<Float> arrayList = new ArrayList<Float>();
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
		}
		ArrayList<Float> sortingArray = new ArrayList<Float>();
		for (Float child : arrayList) {
			sortingArray.add(child);
		}
		Collections.sort(sortingArray);
		return sortingArray.equals(arrayList);
	}

	public boolean isSortedStringAscendingJDK8(WebDriver driver, String locator) {
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		List<String> items = elementList.stream().map(n -> n.getText()).collect(Collectors.toList());
		List<String> sortedList = items;
		Collections.sort(sortedList);
		return items.equals(sortedList);
	}
	
	public boolean isSortedStringDescendingJDK8(WebDriver driver, String locator) {
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		List<String> itemList = elementList.stream().map(n -> n.getText()).collect(Collectors.toList());
		List<String> sortingList = itemList;
		Collections.sort(sortingList);
		Collections.reverse(sortingList);
		return sortingList.equals(itemList);
	}
}
