package nopCommerce.frontend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.FE_CheckoutPO;
import pageObjects.FE_HomePO;
import pageObjects.FE_MyAccountPO;
import pageObjects.FE_OrderDetailsPO;
import pageObjects.FE_ProductCategoriesPO;
import pageObjects.FE_ProductDetailsPO;
import pageObjects.FE_RegisterPO;
import pageObjects.FE_ShoppingCartPO;
import pageObjects.PageGeneratorManager;

public class NopCommerce_FE_08_Orders extends AbstractTest {
	private WebDriver driver;
	
	private FE_HomePO homePage;
	private FE_RegisterPO registerPage;
	private FE_ProductDetailsPO productDetailsPage;
	private FE_ProductCategoriesPO productCategoriesPage;
	private FE_ShoppingCartPO shoppingCartpage;
	private FE_CheckoutPO checkoutPage;
	private FE_MyAccountPO myAccountPage;
	private FE_OrderDetailsPO orderDetailsPage;
	
	String firstName, lastName, registeredEmail, validRandomEmail, validPassword;
	String processorType, ramType, hddType, osName, softwareOption_1, softwareOption_2, softwareOption_3;
	String editProcessorType, editRamType, editHddType, editOsName;
	
	String firstName_B1, lastName_B1, email_B1, company_B1, address_B1, city_B1, state_B1, country_B1, zipCode_B1, phoneNumber_B1, faxNumber_B1;
	String firstName_S1, lastName_S1, email_S1, company_S1, address_S1, city_S1, state_S1, country_S1, zipCode_S1, phoneNumber_S1, faxNumber_S1;
	String firstName_B2, lastName_B2, email_B2, company_B2, address_B2, city_B2, state_B2, country_B2, zipCode_B2, phoneNumber_B2, faxNumber_B2;
	String firstName_S2, lastName_S2, email_S2, company_S2, address_S2, city_S2, state_S2, country_S2, zipCode_S2, phoneNumber_S2, faxNumber_S2;
	String paymentMethod_1, paymentMethod_2, shippingMethod_1, shippingMethod_2;
	
	String productName, productSKU, productPrice, productQty, productTotal;
	String subTotalPay, shippingPay, taxPay, totalPay;
	
	String orderNumber, zoneID;

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		
		// Create Data for Register
		firstName = "Kevin";
		lastName = "Pham";
		validRandomEmail = "Kevinpham_" + randomNumber() + "@gmail.com";
		validPassword = "Abcxyz123";

		// Create Data for Product Info
		processorType = "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]";
		ramType = "8GB [+$60.00]";
		hddType = "400 GB [+$100.00]";
		osName = "Vista Premium [+$60.00]";
		softwareOption_1 = "Microsoft Office [+$50.00]";
		softwareOption_2 = "Acrobat Reader [+$10.00]";
		softwareOption_3 = "Total Commander [+$5.00]";
		editProcessorType = "2.2 GHz Intel Pentium Dual-Core E2200";
		editRamType = "4GB [+$20.00]";
		editHddType = "320 GB";
		editOsName = "Vista Home [+$50.00]";
		
		// Create Data for Customer_01
		firstName_B1 = "Zachery";
		lastName_B1 = "Grima";
		email_B1 = "zgrima6@google.co.uk";
		company_B1 = "Google Inc.";
		address_B1 = "27 Rieder Parkway";
		city_B1 = "San Francisco";
		state_B1 = "California";
		country_B1 = "United States";
		zipCode_B1 = "94154";
		phoneNumber_B1 = "415 318 4836";
		faxNumber_B1 = "(412) 3091236";
		
		// Create Data for Customer_02
		firstName_S1 = "Dona";
		lastName_S1 = "Chamney";
		email_S1 = "dchamney0@feedburner.com";
		company_S1 = "Gabtype";
		address_S1 = "5 Gina Trail";
		city_S1 = "Whitwell";
		state_S1 = "Other";
		//state_S1 = "Other (Non US)";
		country_S1 = "United Kingdom";
		zipCode_S1 = "DL10";
		phoneNumber_S1 = "808 614 1749";
		faxNumber_S1 = "(956) 841291";

		// Create Data for Customer_03
		firstName_B2 = "Luisa";
		lastName_B2 = "Southam";
		email_B2 = "lsoutham1j@godaddy.com";
		company_B2 = "Centidel";
		address_B2 = "12405 Miller Trail";
		city_B2 = "Baltimore";
		state_B2 = "Maryland";
		country_B2 = "United States";
		zipCode_B2 = "21239";
		phoneNumber_B2 = "410 946 7355";
		faxNumber_B2 = "(503) 9425362";
		
		// Create Data for Customer_04
		firstName_S2 = "Maritsa";
		lastName_S2 = "Simonett";
		email_S2 = "msimonettb@deliciousdays.com";
		company_S2 = "Zoomlounge";
		address_S2 = "12 Stone Corner Point";
		city_S2 = "Pentre";
		state_S2 = "Other";
		country_S2 = "United Kingdom";
		zipCode_S2 = "SY4";
		phoneNumber_S2 = "150 440 3313";
		faxNumber_S2 = "(296) 3309249";

		paymentMethod_1 = "Check / Money Order";
		paymentMethod_2 = "Credit Card";
		shippingMethod_1 = "Ground";
		shippingMethod_2 = "Next Day Air";
		
		zoneID = "America/Chicago";

		driver = getBrowserDriverAutoUrl(browserName, urlValue);
		homePage = PageGeneratorManager.getHomePage(driver);
		
		// Register a New Account
		registerPage = (FE_RegisterPO) homePage.openDynamicHeaderPage("Register");
		verifyTrue(registerPage.isDynamicPageFormDisplayed("page registration-page"));
		registerPage.inputToDynamicTextbox("FirstName", firstName);
		registerPage.inputToDynamicTextbox("LastName", lastName);
		registerPage.inputToDynamicTextbox("Email", validRandomEmail);
		System.out.println(validRandomEmail);
		registerPage.inputToDynamicTextbox("Password", validPassword);
		registerPage.inputToDynamicTextbox("ConfirmPassword", validPassword);
		registerPage.clickOnDynamicButton("Register");
		verifyTrue(registerPage.isRegisterSuccessMessageDisplayed());
		verifyEquals(registerPage.getRegisterSuccessMessageText(), "Your registration completed");

	}
	
	@Test
	public void TC_01_AddProductToCart() {
		registerPage.hoverOnDynamicHeaderMenu("Computers");
		registerPage.clickOnDynamicHeaderMenu("Desktops");
		
		productCategoriesPage = PageGeneratorManager.getProductCategoriesPage(driver);
		
		productCategoriesPage.clickOnDynamicProductName("Build your own computer");
		productDetailsPage = PageGeneratorManager.getProductDetailsPage(driver);
		verifyTrue(productDetailsPage.isDynamicPageFormDisplayed("page product-details-page"));
		verifyEquals(productDetailsPage.getProductDetailsName(), "Build your own computer");
		
		productDetailsPage.selectItemInDynamicDropdown("product_attribute_1", processorType);
		productDetailsPage.selectItemInDynamicDropdown("product_attribute_2", ramType);
		productDetailsPage.clickToCheckDynamicRadioButtonOrChecbkboxByLabel(hddType);
		productDetailsPage.clickToCheckDynamicRadioButtonOrChecbkboxByLabel(osName);
		productDetailsPage.clickToCheckDynamicRadioButtonOrChecbkboxByLabel(softwareOption_1);
		productDetailsPage.clickToCheckDynamicRadioButtonOrChecbkboxByLabel(softwareOption_2);
		productDetailsPage.clickToCheckDynamicRadioButtonOrChecbkboxByLabel(softwareOption_3);
		productDetailsPage.clickOnDynamicProductOverviewActionButton("Add to cart");
		
		verifyEquals(productDetailsPage.getTextActionSuccessMessage(), "The product has been added to your shopping cart");
		productDetailsPage.clickOnNotificationBarCloseButton();
		
		verifyEquals(productDetailsPage.getTextMiniShoppingCartQuantity(), "(1)");
		productDetailsPage.hoverOnHeaderTopCartLink();
		
		verifyTrue(productDetailsPage.isFlyoutCartDisplayed());
		verifyTrue(productDetailsPage.isDynamicMiniShoppingCartProductNameDisplayed("Build your own computer"));
		verifyTrue(productDetailsPage.isMiniShoppingCartProductDetailDisplayed());
		verifyEquals(productCategoriesPage.getTextDynamicMiniShoppingCartByLabel("count"), "There are 1 item(s) in your cart.");
		verifyEquals(productCategoriesPage.getTextDynamicMiniShoppingCartByLabel("totals"), "Sub-Total: $1,500.00");
		
		System.out.println(productDetailsPage.getTextProducAttributes());
						
		verifyEquals(productCategoriesPage.getTextProducAttributes(), 
				"Processor: " + processorType + "\n" + 
				"RAM: " + ramType + "\n" + 
				"HDD: " + hddType + "\n" + 
				"OS: " + osName + "\n" + 
				"Software: " + softwareOption_1 + "\n" + 
				"Software: " + softwareOption_2 + "\n" + 
				"Software: " + softwareOption_3);
						
	}
	
	@Test
	public void TC_02_EditProductInShoppingCart() {
		
		productCategoriesPage.openDynamicHeaderLink("Shopping cart");
		shoppingCartpage = PageGeneratorManager.getShoppingCartPage(driver);
		verifyTrue(shoppingCartpage.isDynamicPageFormDisplayed("page shopping-cart-page"));
		
		shoppingCartpage.clickOnEditProdructAttributesLink(); 
		verifyTrue(productDetailsPage.isDynamicPageFormDisplayed("page product-details-page"));
		verifyEquals(productDetailsPage.getProductDetailsName(), "Build your own computer");
		
		productDetailsPage.selectItemInDynamicDropdown("product_attribute_1", editProcessorType);
		productDetailsPage.selectItemInDynamicDropdown("product_attribute_2", editRamType);
		productDetailsPage.clickToCheckDynamicRadioButtonOrChecbkboxByLabel(editHddType);
		productDetailsPage.clickToCheckDynamicRadioButtonOrChecbkboxByLabel(editOsName);
		productDetailsPage.clickToUncheckDynamicCheckboxByLabel(softwareOption_2);
		productDetailsPage.clickToUncheckDynamicCheckboxByLabel(softwareOption_3);
		
		productDetailsPage.inputToDynamicTextbox("product_enteredQuantity_1", "2");
		//productDetailsPage.inputToDynamicTextbox("addtocart_1_EnteredQuantity", "2");
		verifyEquals(productCategoriesPage.getTextProductDetailPrice(), "$1,320.00");
		productCategoriesPage.clickOnDynamicButton("Update");
		
		verifyEquals(productDetailsPage.getTextActionSuccessMessage(), "The product has been added to your shopping cart");
		productDetailsPage.clickOnNotificationBarCloseButton();
				
		verifyEquals(productDetailsPage.getTextMiniShoppingCartQuantity(), "(2)");
		productDetailsPage.hoverOnHeaderTopCartLink();
		verifyEquals(productCategoriesPage.getTextDynamicMiniShoppingCartByLabel("totals"), "Sub-Total: $2,640.00");
		
		System.out.println(productDetailsPage.getTextProducAttributes());
					
		verifyEquals(productCategoriesPage.getTextProducAttributes(), 
				"Processor: " + editProcessorType + "\n" + 
				"RAM: " + editRamType + "\n" + 
				"HDD: " + editHddType + "\n" + 
				"OS: " + editOsName + "\n" + 
				"Software: " + softwareOption_1);
	}
	
	@Test
	public void TC_03_RemoveFromCart() {
	
		homePage.openDynamicHeaderLink("Shopping cart");
		shoppingCartpage = PageGeneratorManager.getShoppingCartPage(driver);
		verifyTrue(shoppingCartpage.isDynamicPageFormDisplayed("page shopping-cart-page"));
		shoppingCartpage.removeAllProduct();
		verifyEquals(shoppingCartpage.getTextPageBodyMessage(), "Your Shopping Cart is empty!");
		
	}
	
	@Test
	public void TC_04_UpdateShoppingCart() {

		shoppingCartpage.hoverOnDynamicHeaderMenu("Computers");
		shoppingCartpage.clickOnDynamicHeaderMenu("Desktops");
		productCategoriesPage = PageGeneratorManager.getProductCategoriesPage(driver);
		productCategoriesPage.clickOnDynamicProductName("Lenovo IdeaCentre 600 All-in-One PC"); 
		productDetailsPage = PageGeneratorManager.getProductDetailsPage(driver);
		verifyTrue(productDetailsPage.isDynamicPageFormDisplayed("page product-details-page"));
		verifyEquals(productDetailsPage.getProductDetailsName(), "Lenovo IdeaCentre 600 All-in-One PC");
		productDetailsPage.clickOnDynamicProductOverviewActionButton("Add to cart");
		verifyEquals(productDetailsPage.getTextActionSuccessMessage(), "The product has been added to your shopping cart");
		productDetailsPage.clickOnNotificationBarCloseButton();
		
		productDetailsPage.openDynamicHeaderLink("Shopping cart");
		shoppingCartpage = PageGeneratorManager.getShoppingCartPage(driver);
		verifyTrue(shoppingCartpage.isDynamicPageFormDisplayed("page shopping-cart-page"));

		shoppingCartpage.inputToDynamicQuantityTextboxByProductname("Lenovo IdeaCentre 600 All-in-One PC", "5");
		shoppingCartpage.clickOnUpdateButton();
		verifyEquals(shoppingCartpage.getTextDynamicShoppingCartProductTotalPrice("Lenovo IdeaCentre 600 All-in-One PC"), "$2,500.00");
		
	}
	
	@Test 
	public void TC_05_CheckoutProductOrder() {

		homePage.openDynamicHeaderLink("Shopping cart");
		shoppingCartpage = PageGeneratorManager.getShoppingCartPage(driver);
		
		shoppingCartpage.selectItemInDynamicDropdownByID("checkout_attribute_1", "Yes [+$10.00]");
		shoppingCartpage.clickToCheckDynamicCheckbox("termsofservice");
		shoppingCartpage.clickOnDynamicButton("checkout");
		checkoutPage = PageGeneratorManager.getCheckoutPage(driver);
		verifyTrue(checkoutPage.isDynamicPageFormDisplayed("page checkout-page"));
		
		checkoutPage.clickToUncheckDynamicCheckbox("ShipToSameAddress");
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_FirstName", firstName_B1);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_LastName", lastName_B1);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_Email", email_B1);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_Company", company_B1);
		checkoutPage.selectItemInDynamicDropdownByID("BillingNewAddress_CountryId", country_B1);
		checkoutPage.selectItemInDynamicDropdownByID("BillingNewAddress_StateProvinceId", state_B1);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_City", city_B1);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_Address1", address_B1);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_ZipPostalCode", zipCode_B1);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_PhoneNumber", phoneNumber_B1);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_FaxNumber", faxNumber_B1);
		checkoutPage.clickOnDynamicCheckoutContinueButton("billing-buttons-container");
		
		checkoutPage.selectItemInDynamicDropdownByID("shipping-address-select", "New Address");
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_FirstName", firstName_S1);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_LastName", lastName_S1);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_Email", email_S1);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_Company", company_S1);
		checkoutPage.selectItemInDynamicDropdownByID("ShippingNewAddress_CountryId", country_S1);
		checkoutPage.selectItemInDynamicDropdownByID("ShippingNewAddress_StateProvinceId", state_S1);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_City", city_S1);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_Address1", address_S1);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_ZipPostalCode", zipCode_S1);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_PhoneNumber", phoneNumber_S1);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_FaxNumber", faxNumber_S1);
		checkoutPage.clickOnDynamicCheckoutContinueButton("shipping-buttons-container");
		
		checkoutPage.clickToCheckDynamicCheckoutRadioButtonOrCheckbox(shippingMethod_1);
		checkoutPage.clickOnDynamicCheckoutContinueButton("shipping-method-buttons-container");
		
		checkoutPage.clickToCheckDynamicCheckoutRadioButtonOrCheckbox(paymentMethod_1);
		checkoutPage.clickOnDynamicCheckoutContinueButton("payment-method-buttons-container");
		
		verifyTrue(checkoutPage.isSectionPaymentInfoDisplayed());
		checkoutPage.clickOnDynamicCheckoutContinueButton("payment-info-buttons-container");
		
		verifyEquals(checkoutPage.getTextDynamicCheckoutInfoList("billing-info"), 
						firstName_B1 + " " + lastName_B1 + "\n" + 
						"Email: " + email_B1 + "\n" +  
						"Phone: " + phoneNumber_B1 + "\n" + 
						"Fax: " + faxNumber_B1 + "\n" + 
						company_B1 + "\n" + 
						address_B1 + "\n" + 
						city_B1 + "," + state_B1 + "," + zipCode_B1 + "\n" + 
						country_B1);
		
		verifyEquals(checkoutPage.getTextDynamicCheckoutInfoList("shipping-info"), 
						firstName_S1 + " " + lastName_S1 + "\n" + 
						"Email: " + email_S1 + "\n" +  
						"Phone: " + phoneNumber_S1 + "\n" + 
						"Fax: " + faxNumber_S1 + "\n" + 
						company_S1 + "\n" + 
						address_S1 + "\n" + 
						city_S1 + "," + zipCode_S1 + "\n" + 
						country_S1);
		
		verifyEquals(checkoutPage.getTextDynamicCheckoutInfoList("payment-method-info"), "Payment Method: " + paymentMethod_1);
		
		verifyEquals(checkoutPage.getTextDynamicCheckoutInfoList("shipping-method-info"), "Shipping Method: " + shippingMethod_1);
		
		verifyEquals(checkoutPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "sku"), "LE_IC_600");
		verifyEquals(checkoutPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "unit-price"), "$500.00");
		verifyEquals(checkoutPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "quantity"), "5");
		verifyEquals(checkoutPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "subtotal"), "$2,500.00");
		
		verifyEquals(checkoutPage.getTextCartOptions(), "Gift wrapping: Yes [+$10.00]");
		
		verifyEquals(checkoutPage.getTextCartFooterTotalInfo(), 
				"Sub-Total: $2,510.00" + "\n" + 
				"Shipping: (Ground) $0.00" + "\n" +  
				"Tax: $0.00" + "\n" + 
				"Total: $2,510.00" + "\n" + 
				"You will earn: 251 points");
		
		checkoutPage.clickOnDynamicButton("Confirm");
		verifyEquals(checkoutPage.getTextPageTitle(), "Thank you");
		verifyEquals(checkoutPage.getTextOrderSuccessfullyMsg(), "Your order has been successfully processed!");
		verifyTrue(checkoutPage.isOrderNumberDisplay());
		orderNumber = checkoutPage.getTextOrderNumber();
		System.out.println("Order Number = " + orderNumber);
		checkoutPage.clickOnDynamicButton("Continue"); // ---> HomePage
		homePage = PageGeneratorManager.getHomePage(driver);
		
		myAccountPage = (FE_MyAccountPO) homePage.openDynamicHeaderPage("My account");
		verifyTrue(myAccountPage.isDynamicPageFormDisplayed("master-wrapper-content"));
		myAccountPage.clickOnDynamicMyAccountNavigationLink("Orders");
		verifyEquals(myAccountPage.getMyAccountPageTitle(), "My account - Orders");
		
		myAccountPage.isDynamicOrdersDisplayed(orderNumber);
		myAccountPage.clickOnDynamicOrderDetailsLink(orderNumber);
		orderDetailsPage = PageGeneratorManager.getOrderDetailsPage(driver);
		verifyTrue(orderDetailsPage.isDynamicPageFormDisplayed("page order-details-page"));
		verifyEquals(orderDetailsPage.getTextPageTitle(), "Order information");
		
		verifyEquals(orderDetailsPage.getTextDetailsOrderNumber(), "ORDER #" + orderNumber);
		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("order-date"), "Order Date: " + getTodayWithWeekdayByTimeZone(zoneID));
		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("order-status"), "Order Status: Pending");
		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("order-total"), "Order Total: $2,510.00");
		
		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("billing-info"), 
				"Billing Address" + "\n" + 
				firstName_B1 + " " + lastName_B1 + "\n" + 
				"Email: " + email_B1 + "\n" +  
				"Phone: " + phoneNumber_B1 + "\n" + 
				"Fax: " + faxNumber_B1 + "\n" + 
				company_B1 + "\n" + 
				address_B1 + "\n" + 
				city_B1 + "," + state_B1 + "," + zipCode_B1 + "\n" + 
				country_B1);
		
		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("shipping-info"), 
				"Shipping Address" + "\n" + 
				firstName_S1 + " " + lastName_S1 + "\n" + 
				"Email: " + email_S1 + "\n" +  
				"Phone: " + phoneNumber_S1 + "\n" + 
				"Fax: " + faxNumber_S1 + "\n" + 
				company_S1 + "\n" + 
				address_S1 + "\n" + 
				city_S1 + "," + zipCode_S1 + "\n" + 
				country_S1);
		
		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("payment-method-info"), 
				"Payment" + "\n" + 
						"Payment Method: " + paymentMethod_1 + "\n" + 
				"Payment Status: Pending");
		
		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("shipping-method-info"), 
				"Shipping" + "\n" + 
						"Shipping Method: " + shippingMethod_1 + "\n" + 
				"Shipping Status: Not yet shipped");
		
		verifyEquals(orderDetailsPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "sku"), "LE_IC_600");
		verifyEquals(orderDetailsPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "unit-price"), "$500.00");
		verifyEquals(orderDetailsPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "quantity"), "5");
		verifyEquals(orderDetailsPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "total"), "$2,500.00");
		
		verifyEquals(orderDetailsPage.getTextSectionOptions(), "Gift wrapping: Yes [+$10.00]");
		
		verifyEquals(orderDetailsPage.getTextSectionTotalInfo(), 
				"Sub-Total: $2,510.00" + "\n" + 
						"Shipping: $0.00" + "\n" +  
						"Tax: $0.00" + "\n" + 
				"Order Total: $2,510.00");
		
		orderDetailsPage.clickOnDynamicButton("Re-order"); 
		shoppingCartpage = PageGeneratorManager.getShoppingCartPage(driver);
		
	}
	
	@Test
	public void TC_06_CheckoutProductOrderByVisaCart() {
		
		homePage.openDynamicHeaderLink("Shopping cart");
		shoppingCartpage = PageGeneratorManager.getShoppingCartPage(driver);
		
		shoppingCartpage.selectItemInDynamicDropdownByID("checkout_attribute_1", "Yes [+$10.00]");
		shoppingCartpage.clickToCheckDynamicCheckbox("termsofservice");
		shoppingCartpage.clickOnDynamicButton("checkout");
		checkoutPage = PageGeneratorManager.getCheckoutPage(driver);
		verifyTrue(checkoutPage.isDynamicPageFormDisplayed("page checkout-page"));
		
		checkoutPage.clickToUncheckDynamicCheckbox("ShipToSameAddress");
		
		checkoutPage.selectExistedAddressInDynamicDropdownByID("billing-address-select", 0);
		checkoutPage.clickOnDynamicCheckoutContinueButton("billing-buttons-container");
		
		checkoutPage.selectExistedAddressInDynamicDropdownByID("shipping-address-select", 1);
		checkoutPage.clickOnDynamicCheckoutContinueButton("shipping-buttons-container");
		
		checkoutPage.clickToCheckDynamicCheckoutRadioButtonOrCheckbox(shippingMethod_1);
		checkoutPage.clickOnDynamicCheckoutContinueButton("shipping-method-buttons-container");
		
		checkoutPage.clickToCheckDynamicCheckoutRadioButtonOrCheckbox(paymentMethod_2);
		checkoutPage.clickOnDynamicCheckoutContinueButton("payment-method-buttons-container");
		
		verifyTrue(checkoutPage.isSectionPaymentInfoDisplayed());
		checkoutPage.selectItemInDynamicDropdownByID("CreditCardType", "Visa");
		checkoutPage.inputToDynamicTextbox("CardholderName", "Frank Sing");
		checkoutPage.inputToDynamicTextbox("CardNumber", "4929457706958891");
		checkoutPage.selectItemInDynamicDropdownByID("ExpireMonth", "03");
		checkoutPage.selectItemInDynamicDropdownByID("ExpireYear", "2022");
		checkoutPage.inputToDynamicTextbox("CardCode", "333");
		checkoutPage.clickOnDynamicCheckoutContinueButton("payment-info-buttons-container");
		
		verifyEquals(checkoutPage.getTextDynamicCheckoutInfoList("billing-info"), 
				firstName_B1 + " " + lastName_B1 + "\n" + 
				"Email: " + email_B1 + "\n" +  
				"Phone: " + phoneNumber_B1 + "\n" + 
				"Fax: " + faxNumber_B1 + "\n" + 
				company_B1 + "\n" + 
				address_B1 + "\n" + 
				city_B1 + "," + state_B1 + "," + zipCode_B1 + "\n" + 
				country_B1);

		verifyEquals(checkoutPage.getTextDynamicCheckoutInfoList("shipping-info"), 
				firstName_S1 + " " + lastName_S1 + "\n" + 
				"Email: " + email_S1 + "\n" +  
				"Phone: " + phoneNumber_S1 + "\n" + 
				"Fax: " + faxNumber_S1 + "\n" + 
				company_S1 + "\n" + 
				address_S1 + "\n" + 
				city_S1 + "," + zipCode_S1 + "\n" + 
				country_S1);

		verifyEquals(checkoutPage.getTextDynamicCheckoutInfoList("payment-method-info"), "Payment Method: " + paymentMethod_2);
		verifyEquals(checkoutPage.getTextDynamicCheckoutInfoList("shipping-method-info"), "Shipping Method: " + shippingMethod_1);
		
		verifyEquals(checkoutPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "sku"), "LE_IC_600");
		verifyEquals(checkoutPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "unit-price"), "$500.00");
		verifyEquals(checkoutPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "quantity"), "5");
		verifyEquals(checkoutPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "subtotal"), "$2,500.00");
		
		verifyEquals(checkoutPage.getTextCartOptions(), "Gift wrapping: Yes [+$10.00]");
		
		verifyEquals(checkoutPage.getTextCartFooterTotalInfo(), 
				"Sub-Total: $2,510.00" + "\n" + 
				"Shipping: (Ground) $0.00" + "\n" +  
				"Tax: $0.00" + "\n" + 
				"Total: $2,510.00" + "\n" + 
				"You will earn: 251 points");

		checkoutPage.clickOnDynamicButton("Confirm");
		verifyEquals(checkoutPage.getTextPageTitle(), "Thank you");
		verifyEquals(checkoutPage.getTextOrderSuccessfullyMsg(), "Your order has been successfully processed!");
		verifyTrue(checkoutPage.isOrderNumberDisplay());
		orderNumber = checkoutPage.getTextOrderNumber();
		System.out.println("Order Number = " + orderNumber);
		checkoutPage.clickOnDynamicButton("Continue"); 
		homePage = PageGeneratorManager.getHomePage(driver);
	
		myAccountPage = (FE_MyAccountPO) homePage.openDynamicHeaderPage("My account");
		verifyTrue(myAccountPage.isDynamicPageFormDisplayed("master-wrapper-content"));
		myAccountPage.clickOnDynamicMyAccountNavigationLink("Orders");
		verifyEquals(myAccountPage.getMyAccountPageTitle(), "My account - Orders");
		
		myAccountPage.isDynamicOrdersDisplayed(orderNumber);
		myAccountPage.clickOnDynamicOrderDetailsLink(orderNumber);
		orderDetailsPage = PageGeneratorManager.getOrderDetailsPage(driver);
		verifyTrue(orderDetailsPage.isDynamicPageFormDisplayed("page order-details-page"));
		verifyEquals(orderDetailsPage.getTextPageTitle(), "Order information");
		
		verifyEquals(orderDetailsPage.getTextDetailsOrderNumber(), "ORDER #" + orderNumber);
		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("order-date"), "Order Date: " + getTodayWithWeekdayByTimeZone(zoneID));
		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("order-status"), "Order Status: Pending");
		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("order-total"), "Order Total: $2,510.00");
		
		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("billing-info"), 
				"Billing Address" + "\n" + 
				firstName_B1 + " " + lastName_B1 + "\n" + 
				"Email: " + email_B1 + "\n" +  
				"Phone: " + phoneNumber_B1 + "\n" + 
				"Fax: " + faxNumber_B1 + "\n" + 
				company_B1 + "\n" + 
				address_B1 + "\n" + 
				city_B1 + "," + state_B1 + "," + zipCode_B1 + "\n" + 
				country_B1);
		
		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("shipping-info"), 
				"Shipping Address" + "\n" + 
				firstName_S1 + " " + lastName_S1 + "\n" + 
				"Email: " + email_S1 + "\n" +  
				"Phone: " + phoneNumber_S1 + "\n" + 
				"Fax: " + faxNumber_S1 + "\n" + 
				company_S1 + "\n" + 
				address_S1 + "\n" + 
				city_S1 + "," + zipCode_S1 + "\n" + 
				country_S1);

		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("payment-method-info"), 
				"Payment" + "\n" + 
				"Payment Method: " + paymentMethod_2 + "\n" + 
				"Payment Status: Pending");

		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("shipping-method-info"), 
				"Shipping" + "\n" + 
				"Shipping Method: " + shippingMethod_1 + "\n" + 
				"Shipping Status: Not yet shipped");
		
		verifyEquals(orderDetailsPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "sku"), "LE_IC_600");
		verifyEquals(orderDetailsPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "unit-price"), "$500.00");
		verifyEquals(orderDetailsPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "quantity"), "5");
		verifyEquals(orderDetailsPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "total"), "$2,500.00");

		verifyEquals(orderDetailsPage.getTextSectionOptions(), "Gift wrapping: Yes [+$10.00]");
		
		verifyEquals(orderDetailsPage.getTextSectionTotalInfo(), 
				"Sub-Total: $2,510.00" + "\n" + 
				"Shipping: $0.00" + "\n" +  
				"Tax: $0.00" + "\n" + 
				"Order Total: $2,510.00");

		orderDetailsPage.clickOnDynamicButton("Re-order"); 
		shoppingCartpage = PageGeneratorManager.getShoppingCartPage(driver);

	}
	
	@Test
	public void TC_07_Reorder() {
		
		shoppingCartpage.inputToDynamicQuantityTextboxByProductname("Lenovo IdeaCentre 600 All-in-One PC", "10");
		shoppingCartpage.clickOnUpdateButton();
		verifyEquals(shoppingCartpage.getTextDynamicShoppingCartProductTotalPrice("Lenovo IdeaCentre 600 All-in-One PC"), "$5,000.00");

		shoppingCartpage.selectItemInDynamicDropdownByID("checkout_attribute_1", "No");
		shoppingCartpage.clickToCheckDynamicCheckbox("termsofservice");
		shoppingCartpage.clickOnDynamicButton("checkout");
		checkoutPage = PageGeneratorManager.getCheckoutPage(driver);
		verifyTrue(checkoutPage.isDynamicPageFormDisplayed("page checkout-page"));

		checkoutPage.clickToUncheckDynamicCheckbox("ShipToSameAddress");
		checkoutPage.selectItemInDynamicDropdownByID("billing-address-select", "New Address");
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_FirstName", firstName_B2);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_LastName", lastName_B2);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_Email", email_B2);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_Company", company_B2);
		checkoutPage.selectItemInDynamicDropdownByID("BillingNewAddress_CountryId", country_B2);
		checkoutPage.selectItemInDynamicDropdownByID("BillingNewAddress_StateProvinceId", state_B2);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_City", city_B2);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_Address1", address_B2);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_ZipPostalCode", zipCode_B2);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_PhoneNumber", phoneNumber_B2);
		checkoutPage.inputToDynamicTextbox("BillingNewAddress_FaxNumber", faxNumber_B2);
		checkoutPage.clickOnDynamicCheckoutContinueButton("billing-buttons-container");

		checkoutPage.selectItemInDynamicDropdownByID("shipping-address-select", "New Address");
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_FirstName", firstName_S2);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_LastName", lastName_S2);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_Email", email_S2);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_Company", company_S2);
		checkoutPage.selectItemInDynamicDropdownByID("ShippingNewAddress_CountryId", country_S2);
		checkoutPage.selectItemInDynamicDropdownByID("ShippingNewAddress_StateProvinceId", state_S2);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_City", city_S2);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_Address1", address_S2);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_ZipPostalCode", zipCode_S2);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_PhoneNumber", phoneNumber_S2);
		checkoutPage.inputToDynamicTextbox("ShippingNewAddress_FaxNumber", faxNumber_S2);
		checkoutPage.clickOnDynamicCheckoutContinueButton("shipping-buttons-container");
		
		checkoutPage.clickToCheckDynamicCheckoutRadioButtonOrCheckbox(shippingMethod_2);
		checkoutPage.clickOnDynamicCheckoutContinueButton("shipping-method-buttons-container");
		
		checkoutPage.clickToCheckDynamicCheckoutRadioButtonOrCheckbox(paymentMethod_1);
		checkoutPage.clickOnDynamicCheckoutContinueButton("payment-method-buttons-container");
		
		verifyTrue(checkoutPage.isSectionPaymentInfoDisplayed());
		checkoutPage.clickOnDynamicCheckoutContinueButton("payment-info-buttons-container");
		
		verifyEquals(checkoutPage.getTextDynamicCheckoutInfoList("billing-info"), 
				firstName_B2 + " " + lastName_B2 + "\n" + 
				"Email: " + email_B2 + "\n" +  
				"Phone: " + phoneNumber_B2 + "\n" + 
				"Fax: " + faxNumber_B2 + "\n" + 
				company_B2 + "\n" + 
				address_B2 + "\n" + 
				city_B2 + "," + state_B2 + "," + zipCode_B2 + "\n" + 
				country_B2);

		verifyEquals(checkoutPage.getTextDynamicCheckoutInfoList("shipping-info"), 
				firstName_S2 + " " + lastName_S2 + "\n" + 
				"Email: " + email_S2 + "\n" +  
				"Phone: " + phoneNumber_S2 + "\n" + 
				"Fax: " + faxNumber_S2 + "\n" + 
				company_S2 + "\n" + 
				address_S2 + "\n" + 
				city_S2 + "," + zipCode_S2 + "\n" + 
				country_S2);
		
		verifyEquals(checkoutPage.getTextDynamicCheckoutInfoList("payment-method-info"), "Payment Method: " + paymentMethod_1);
		verifyEquals(checkoutPage.getTextDynamicCheckoutInfoList("shipping-method-info"), "Shipping Method: " + shippingMethod_2);
		
		verifyEquals(checkoutPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "sku"), "LE_IC_600");
		verifyEquals(checkoutPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "unit-price"), "$500.00");
		verifyEquals(checkoutPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "quantity"), "10");
		verifyEquals(checkoutPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "subtotal"), "$5,000.00");
		
		verifyEquals(checkoutPage.getTextCartOptions(), "Gift wrapping: No");
		
		verifyEquals(checkoutPage.getTextCartFooterTotalInfo(), 
				"Sub-Total: $5,000.00" + "\n" + 
				"Shipping: (Next Day Air) $0.00" + "\n" +  
				"Tax: $0.00" + "\n" + 
				"Total: $5,000.00" + "\n" + 
				"You will earn: 500 points");
		
		checkoutPage.clickOnDynamicButton("Confirm");
		verifyEquals(checkoutPage.getTextPageTitle(), "Thank you");
		verifyEquals(checkoutPage.getTextOrderSuccessfullyMsg(), "Your order has been successfully processed!");
		verifyTrue(checkoutPage.isOrderNumberDisplay());
		orderNumber = checkoutPage.getTextOrderNumber();
		System.out.println("Order Number = " + orderNumber);
		checkoutPage.clickOnDynamicButton("Continue"); // ---> HomePage
		homePage = PageGeneratorManager.getHomePage(driver);
		
		myAccountPage = (FE_MyAccountPO) homePage.openDynamicHeaderPage("My account");
		verifyTrue(myAccountPage.isDynamicPageFormDisplayed("master-wrapper-content"));
		myAccountPage.clickOnDynamicMyAccountNavigationLink("Orders");
		verifyEquals(myAccountPage.getMyAccountPageTitle(), "My account - Orders");
		
		myAccountPage.isDynamicOrdersDisplayed(orderNumber);
		myAccountPage.clickOnDynamicOrderDetailsLink(orderNumber);
		orderDetailsPage = PageGeneratorManager.getOrderDetailsPage(driver);
		verifyTrue(orderDetailsPage.isDynamicPageFormDisplayed("page order-details-page"));
		verifyEquals(orderDetailsPage.getTextPageTitle(), "Order information");
		
		verifyEquals(orderDetailsPage.getTextDetailsOrderNumber(), "ORDER #" + orderNumber);
		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("order-date"), "Order Date: " + getTodayWithWeekdayByTimeZone(zoneID));
		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("order-status"), "Order Status: Pending");
		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("order-total"), "Order Total: $5,000.00");
		
		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("billing-info"), 
				"Billing Address" + "\n" + 
				firstName_B2 + " " + lastName_B2 + "\n" + 
				"Email: " + email_B2 + "\n" +  
				"Phone: " + phoneNumber_B2 + "\n" + 
				"Fax: " + faxNumber_B2 + "\n" + 
				company_B2 + "\n" + 
				address_B2 + "\n" + 
				city_B2 + "," + state_B2 + "," + zipCode_B2 + "\n" + 
				country_B2);
		
		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("shipping-info"), 
				"Shipping Address" + "\n" + 
				firstName_S2 + " " + lastName_S2 + "\n" + 
				"Email: " + email_S2 + "\n" +  
				"Phone: " + phoneNumber_S2 + "\n" + 
				"Fax: " + faxNumber_S2 + "\n" + 
				company_S2 + "\n" + 
				address_S2 + "\n" + 
				city_S2 + "," + zipCode_S2 + "\n" + 
				country_S2);
		
		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("payment-method-info"), 
				"Payment" + "\n" + 
				"Payment Method: " + paymentMethod_1 + "\n" + 
				"Payment Status: Pending");
		
		verifyEquals(orderDetailsPage.getTextDynamicOrderDetails("shipping-method-info"), 
				"Shipping" + "\n" + 
				"Shipping Method: " + shippingMethod_2 + "\n" + 
				"Shipping Status: Not yet shipped");
		
		verifyEquals(orderDetailsPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "sku"), "LE_IC_600");
		verifyEquals(orderDetailsPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "unit-price"), "$500.00");
		verifyEquals(orderDetailsPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "quantity"), "10");
		verifyEquals(orderDetailsPage.getTextDynamicAttributeByProductname("Lenovo IdeaCentre 600 All-in-One PC", "total"), "$5,000.00");
		
		verifyEquals(orderDetailsPage.getTextSectionOptions(), "Gift wrapping: No");
		
		verifyEquals(orderDetailsPage.getTextSectionTotalInfo(), 
				"Sub-Total: $5,000.00" + "\n" + 
				"Shipping: $0.00" + "\n" +  
				"Tax: $0.00" + "\n" + 
				"Order Total: $5,000.00");

	}
		
	@AfterClass(alwaysRun=true)
	public void afterClass() { 
		closeBrowserAndDriver(driver);
		
	}

}
