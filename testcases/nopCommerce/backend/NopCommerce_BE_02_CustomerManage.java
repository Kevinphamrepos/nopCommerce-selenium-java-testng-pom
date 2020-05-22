package nopCommerce.backend;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.PageGeneratorManager;
import pageObjects.BE_AdministrationPO;
import pageObjects.BE_LoginPO;

public class NopCommerce_BE_02_CustomerManage extends AbstractTest {
	private WebDriver driver;
	
	private BE_LoginPO loginBEPage;
	private BE_AdministrationPO administrationPage;

	String emailAdmin, passwordAdmin;
	String productName, expectedCategory;
	String email, password, firstName, lastName, dateOfBirth, monthBirth, dayBirth, company;  
	String newEmail, newPassword, newFirstName, newLastName, newDateOfBirth, newMonthBirth, newDayBirth, newCompany;  
	String addFirstName, addLastName, addEmail, addCompany, addCountry, addState, addCity, addAddress, addZipcode, addPhoneNumber, addFaxNumber;
	String editAddFirstName, editAddLastName, editAddEmail, editAddCompany, editAddCountry, editAddState, editAddCity, editAddAddress, editAddZipcode, editAddPhoneNumber, editAddFaxNumber;
	int randomNumber_1, randomNumber_2;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		
		emailAdmin = "admin@yourstore.com";
		passwordAdmin = "admin";
		
		// Create Data for New Customer Form
		randomNumber_1 = randomNumber();
		email = "Kevinpham_" + randomNumber_1 + "@gmail.com";
		password = "Abcxyz123";
		firstName = "Kevin_" + randomNumber_1;
		lastName = "Pham_" + randomNumber_1;
		company = "VIP Ltd. Co. No_" + randomNumber_1;
		dateOfBirth = "01/20/1989";
		monthBirth = "1";
		dayBirth = "20";
		
		// Create Data for Edit Customer Form
		randomNumber_2 = randomNumber();
		newEmail = "Kevinpham_" + randomNumber_2 + "@gmail.com";
		newPassword = "Abcxyz123";
		newFirstName = "Kevin_" + randomNumber_2;
		newLastName = "Pham_" + randomNumber_2;
		newCompany = "Google Inc.";
		newDateOfBirth = "10/15/1989";
		newMonthBirth = "10";
		newDayBirth = "15";
		
		// Create Data for Add New Address
		addFirstName = "Kevin";
		addLastName = "Pham";
		addEmail = "Kevinpham.nopadmin@gmail.com";
		addCompany = "Facebook Inc.";
		addCountry = "United States";
		addState = "California";
		addCity = "San Francisco";
		addAddress = "27 Rieder Parkway";
		addZipcode = "10001";
		addPhoneNumber = "415 318 3333";
		addFaxNumber = "(412) 3000123";
		
		// Create Data for Edit Address
		editAddFirstName = "Jay";
		editAddLastName = "Chou";
		editAddEmail = "Jaychou.nopadmin@gmail.com";
		editAddCompany = "Amazon Inc.";
		editAddCountry = "Canada";
		editAddState = "Ontario";
		editAddCity = "Toronto";
		editAddAddress = "123 University Avenue";
		editAddZipcode = "M5H";
		editAddPhoneNumber = "+1 123-707-0001";
		editAddFaxNumber = "+1 123 888 1133";
		
		driver = getBrowserDriverAutoUrl(browserName, urlValue);
		loginBEPage = PageGeneratorManager.getLoginBEPage(driver);
		verifyTrue(loginBEPage.isDynamicPageFormDisplayed("page login-page"));
		
		loginBEPage.inputToDynamicTextbox("Email", "admin@yourstore.com");
		loginBEPage.inputToDynamicTextbox("Password", "admin");
		loginBEPage.clickOnDynamicButton("Log in");
		administrationPage = PageGeneratorManager.getAdministrationPage(driver);
		verifyTrue(administrationPage.isDashboardPageTitleDisplayed());
		
		administrationPage.clickOnDynamicSidebarMenu("Customers");
		administrationPage.clickOnDynamicSidebarSubmenu("Customers");
		verifyTrue(administrationPage.isDynamicAdministrationSubPageHeaderDisplayed("Customers"));
		
	}
	
	@Test
	public void TC_07_CreateNewCustomer() {
		
		administrationPage.clickOnBlueAddnewButton();
		verifyTrue(administrationPage.isDynamicAdministrationSubPageHeaderDisplayed("Add a new customer"));
		
		administrationPage.clickForOpenDynamicSection("customer-info");
		administrationPage.inputToDynamicTextbox("Email", email);
		administrationPage.inputToDynamicTextbox("Password", password);
		administrationPage.inputToDynamicTextbox("FirstName", firstName);
		administrationPage.inputToDynamicTextbox("LastName", lastName);
		administrationPage.clickOnDynamicRadioButton("Gender_Male");;
		administrationPage.inputToDynamicTextbox("DateOfBirth", dateOfBirth);
		administrationPage.inputToDynamicTextbox("Company", company);
		
		administrationPage.deSelectDynamicItemInCustomMultiDropdown("Registered");
		administrationPage.selectItemInCustomMultiDropdown("Guests");
		
		administrationPage.clickToCheckDynamicCheckbox("Active");
		administrationPage.inputToDynamicTextarea("AdminComment", "Add a new Customer (Guest)");
		administrationPage.clickOnDynamicSaveButton("save-continue");	
		verifyTrue(administrationPage.isAlertMessageDisplayed("The new customer has been added successfully."));
		administrationPage.clickToCloseAlertMessage();
		
		verifyTrue(administrationPage.isDynamicAdministrationSubPageHeaderDisplayed("Edit customer details - " + lastName + " " + firstName));
		
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Email", email));
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("FirstName", firstName));
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("LastName", lastName));
		verifyTrue(administrationPage.isRadioButtonSelected("Gender_Male"));
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("DateOfBirth", dateOfBirth));
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Company", company));
		verifyTrue(administrationPage.isCheckboxSelected("Active"));
		verifyTrue(administrationPage.isCustomerRolesIsSelectedAsExpectation("Guests"));
		verifyEquals(administrationPage.getTextFromDynamicTextboxOrTextarea("AdminComment"), "Add a new Customer (Guest)");
		
		administrationPage.clickOnDynamicBackLink("back to customer list");
		verifyTrue(administrationPage.isDynamicAdministrationSubPageHeaderDisplayed("Customers"));
		
		administrationPage.clickToOpenSearchSection();
		administrationPage.deSelectDynamicItemInCustomMultiDropdown("Registered");
		administrationPage.selectItemInCustomMultiDropdown("Guests");
		administrationPage.clickOnDynamicButtonByID("search-customers");
		
		verifyTrue(administrationPage.isDynamicValueDisplayedOnPageAsExpectation("Name", firstName + " " + lastName));
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(firstName + " " + lastName, "Customer roles"), "Guests");
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(firstName + " " + lastName, "Company name"), company);
		
	}		
	
	@Test
	public void TC_08_SearchCustomerByEmail() {
		
		administrationPage.refreshCurrentPage();
		administrationPage.clickToOpenSearchSection();
		
		administrationPage.inputToDynamicTextbox("SearchEmail", email);
		administrationPage.deSelectDynamicItemInCustomMultiDropdown("Registered");
		administrationPage.selectItemInCustomMultiDropdown("Guests");
		administrationPage.clickOnDynamicButtonByID("search-customers");
		
		verifyEquals(administrationPage.getTotalNumberSearchResultByDynamicColumnName("Name"), 1);
		verifyTrue(administrationPage.isDynamicValueDisplayedOnPageAsExpectation("Name", firstName + " " + lastName));
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(firstName + " " + lastName, "Customer roles"), "Guests");
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(firstName + " " + lastName, "Company name"), company);
		
	}
	
	@Test
	public void TC_09_SearchCustomerByFirstnameAndLastname() {
		
		administrationPage.refreshCurrentPage();
		administrationPage.clickToOpenSearchSection();
		
		administrationPage.inputToDynamicTextbox("SearchFirstName", firstName);
		administrationPage.inputToDynamicTextbox("SearchLastName", lastName);
		administrationPage.deSelectDynamicItemInCustomMultiDropdown("Registered");
		administrationPage.selectItemInCustomMultiDropdown("Guests");
		administrationPage.clickOnDynamicButtonByID("search-customers");
		
		verifyEquals(administrationPage.getTotalNumberSearchResultByDynamicColumnName("Name"), 1);
		verifyTrue(administrationPage.isDynamicValueDisplayedOnPageAsExpectation("Name", firstName + " " + lastName));
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(firstName + " " + lastName, "Customer roles"), "Guests");
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(firstName + " " + lastName, "Company name"), company);
		
	}
	
	@Test
	public void TC_10_SearchCustomerByCompany() {
		
		administrationPage.refreshCurrentPage();
		administrationPage.clickToOpenSearchSection();
		
		administrationPage.inputToDynamicTextbox("SearchCompany", company);
		administrationPage.deSelectDynamicItemInCustomMultiDropdown("Registered");
		administrationPage.selectItemInCustomMultiDropdown("Guests");
		administrationPage.clickOnDynamicButtonByID("search-customers");
		
		verifyEquals(administrationPage.getTotalNumberSearchResultByDynamicColumnName("Name"), 1);
		verifyTrue(administrationPage.isDynamicValueDisplayedOnPageAsExpectation("Name", firstName + " " + lastName));
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(firstName + " " + lastName, "Customer roles"), "Guests");
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(firstName + " " + lastName, "Company name"), company);
		
	}
	
	@Test
	public void TC_11_SearchCustomerByFullData() {
		
		administrationPage.refreshCurrentPage();
		administrationPage.clickToOpenSearchSection();
		
		administrationPage.inputToDynamicTextbox("SearchEmail", email);
		administrationPage.inputToDynamicTextbox("SearchFirstName", firstName);
		administrationPage.inputToDynamicTextbox("SearchLastName", lastName);
		administrationPage.selectItemInDynamicDropdownByID("SearchMonthOfBirth", monthBirth);
		administrationPage.selectItemInDynamicDropdownByID("SearchDayOfBirth", dayBirth);
		administrationPage.inputToDynamicTextbox("SearchCompany", company);
		administrationPage.deSelectDynamicItemInCustomMultiDropdown("Registered");
		administrationPage.selectItemInCustomMultiDropdown("Guests");
		administrationPage.clickOnDynamicButtonByID("search-customers");
		
		verifyEquals(administrationPage.getTotalNumberSearchResultByDynamicColumnName("Name"), 1);
		verifyTrue(administrationPage.isDynamicValueDisplayedOnPageAsExpectation("Name", firstName + " " + lastName));
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(firstName + " " + lastName, "Customer roles"), "Guests");
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(firstName + " " + lastName, "Company name"), company);
		
	}
	
	@Test
	public void TC_12_EditCustomer() {
		
		administrationPage.clickOnDynamicIconByReferValue(firstName + " " + lastName, "Edit"); 
		verifyTrue(administrationPage.isDynamicAdministrationSubPageHeaderDisplayed("Edit customer details - " + lastName + " " + firstName));
		
		administrationPage.clickForOpenDynamicSection("customer-info");
		administrationPage.inputToDynamicTextbox("Email", newEmail);
		administrationPage.inputToDynamicTextbox("Password", newPassword);
		administrationPage.inputToDynamicTextbox("FirstName", newFirstName);
		administrationPage.inputToDynamicTextbox("LastName", newLastName);
		administrationPage.clickOnDynamicRadioButton("Gender_Female");;
		administrationPage.inputToDynamicTextbox("DateOfBirth", newDateOfBirth);
		administrationPage.inputToDynamicTextbox("Company", newCompany);
		
		administrationPage.inputToDynamicTextarea("AdminComment", "Edit Customer Info (Guest)");
		administrationPage.clickOnDynamicSaveButton("save");
		verifyTrue(administrationPage.isAlertMessageDisplayed("The customer has been updated successfully."));
		administrationPage.clickToCloseAlertMessage();
		verifyTrue(administrationPage.isDynamicAdministrationSubPageHeaderDisplayed("Customers"));
		
		administrationPage.clickToOpenSearchSection();
		
		administrationPage.inputToDynamicTextbox("SearchEmail", newEmail);
		administrationPage.inputToDynamicTextbox("SearchFirstName", newFirstName);
		administrationPage.inputToDynamicTextbox("SearchLastName", newLastName);
		administrationPage.selectItemInDynamicDropdownByID("SearchMonthOfBirth", newMonthBirth);
		administrationPage.selectItemInDynamicDropdownByID("SearchDayOfBirth", newDayBirth);
		administrationPage.inputToDynamicTextbox("SearchCompany", newCompany);
		administrationPage.deSelectDynamicItemInCustomMultiDropdown("Registered");
		administrationPage.selectItemInCustomMultiDropdown("Guests");
		administrationPage.clickOnDynamicButtonByID("search-customers");
		
		verifyEquals(administrationPage.getTotalNumberSearchResultByDynamicColumnName("Name"), 1);
		verifyTrue(administrationPage.isDynamicValueDisplayedOnPageAsExpectation("Name", newFirstName + " " + newLastName));
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(newFirstName + " " + newLastName, "Customer roles"), "Guests");
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(newFirstName + " " + newLastName, "Company name"), newCompany);

	}
		
	@Test
	public void TC_13_AddNewAddressInCustomerDetails() {
		
		administrationPage.clickOnDynamicIconByReferValue(newFirstName + " " + newLastName, "Edit");
		verifyTrue(administrationPage.isDynamicAdministrationSubPageHeaderDisplayed("Edit customer details - " + newLastName + " " + newFirstName));
		
		administrationPage.clickForOpenDynamicSection("customer-address");
		administrationPage.clickOnAddnewaddressButton();
		verifyTrue(administrationPage.isDynamicAdministrationSubPageHeaderDisplayed("Add a new address"));
		
		administrationPage.inputToDynamicTextbox("Address_FirstName", addFirstName);
		administrationPage.inputToDynamicTextbox("Address_LastName", addLastName);
		administrationPage.inputToDynamicTextbox("Address_Email", addEmail);
		administrationPage.inputToDynamicTextbox("Address_Company", addCompany);
		administrationPage.selectItemInDynamicDropdownByID("Address_CountryId", addCountry);
		administrationPage.selectItemInDynamicDropdownByID("Address_StateProvinceId", addState);
		administrationPage.inputToDynamicTextbox("Address_City", addCity);
		administrationPage.inputToDynamicTextbox("Address_Address1", addAddress);
		administrationPage.inputToDynamicTextbox("Address_ZipPostalCode", addZipcode);
		administrationPage.inputToDynamicTextbox("Address_PhoneNumber", addPhoneNumber);
		administrationPage.inputToDynamicTextbox("Address_FaxNumber", addFaxNumber);
		
		administrationPage.clickOnDynamicBlueButton("Save");
		verifyTrue(administrationPage.isAlertMessageDisplayed("The new address has been added successfully."));
		administrationPage.clickToCloseAlertMessage();
		verifyTrue(administrationPage.isDynamicAdministrationSubPageHeaderDisplayed("Edit address"));

		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Address_FirstName", addFirstName));
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Address_LastName", addLastName));
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Address_Email", addEmail));
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Address_Company", addCompany));
		administrationPage.isSelectedItemInDynamicDropdownDisplayedByID("Address_CountryId", addCountry);
		administrationPage.isSelectedItemInDynamicDropdownDisplayedByID("Address_StateProvinceId", addState);
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Address_City", addCity));
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Address_Address1", addAddress));
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Address_ZipPostalCode", addZipcode));
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Address_PhoneNumber", addPhoneNumber));
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Address_FaxNumber", addFaxNumber));

		administrationPage.clickOnDynamicBackLink("back to customer details");
		verifyTrue(administrationPage.isDynamicAdministrationSubPageHeaderDisplayed("Edit customer details"));
		
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(addEmail, "First name"), addFirstName);
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(addEmail, "Last name"), addLastName);
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(addEmail, "Phone number"), addPhoneNumber);
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(addEmail, "Fax number"), addFaxNumber);
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(addEmail, "Address"), 
																						addCompany + "\n" + 
																						addAddress + "\n" + 
																						addCity + "," + addState + "," + addZipcode + "\n" + 
																						addCountry);
		
	}
	
	@Test
	public void TC_14_EditAddressInCustomerDetails() {
		
		administrationPage.clickOnDynamicSidebarSubmenu("Customers");
		verifyTrue(administrationPage.isDynamicAdministrationSubPageHeaderDisplayed("Customers"));
		administrationPage.clickToOpenSearchSection();
		
		administrationPage.inputToDynamicTextbox("SearchEmail", newEmail);
		administrationPage.inputToDynamicTextbox("SearchFirstName", newFirstName);
		administrationPage.inputToDynamicTextbox("SearchLastName", newLastName);
		administrationPage.selectItemInDynamicDropdownByID("SearchMonthOfBirth", newMonthBirth);
		administrationPage.selectItemInDynamicDropdownByID("SearchDayOfBirth", newDayBirth);
		administrationPage.inputToDynamicTextbox("SearchCompany", newCompany);
		administrationPage.deSelectDynamicItemInCustomMultiDropdown("Registered");
		administrationPage.selectItemInCustomMultiDropdown("Guests");
		administrationPage.clickOnDynamicButtonByID("search-customers");
		verifyEquals(administrationPage.getTotalNumberSearchResultByDynamicColumnName("Name"), 1);
		verifyTrue(administrationPage.isDynamicValueDisplayedOnPageAsExpectation("Name", newFirstName + " " + newLastName));
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(newFirstName + " " + newLastName, "Customer roles"), "Guests");
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(newFirstName + " " + newLastName, "Company name"), newCompany);

		administrationPage.clickOnDynamicIconByReferValue(newFirstName + " " + newLastName, "Edit");
		verifyTrue(administrationPage.isDynamicAdministrationSubPageHeaderDisplayed("Edit customer details - " + newLastName + " " + newFirstName));
		
		administrationPage.clickForOpenDynamicSection("customer-address");
		administrationPage.clickOnDynamicIconByReferValue(addEmail, "Edit");
		verifyTrue(administrationPage.isDynamicAdministrationSubPageHeaderDisplayed("Edit address"));
		
		administrationPage.inputToDynamicTextbox("Address_FirstName", editAddFirstName);
		administrationPage.inputToDynamicTextbox("Address_LastName", editAddLastName);
		administrationPage.inputToDynamicTextbox("Address_Email", editAddEmail);
		administrationPage.inputToDynamicTextbox("Address_Company", editAddCompany);
		administrationPage.selectItemInDynamicDropdownByID("Address_CountryId", editAddCountry);
		administrationPage.selectItemInDynamicDropdownByID("Address_StateProvinceId", editAddState);
		administrationPage.inputToDynamicTextbox("Address_City", editAddCity);
		administrationPage.inputToDynamicTextbox("Address_Address1", editAddAddress);
		administrationPage.inputToDynamicTextbox("Address_ZipPostalCode", editAddZipcode);
		administrationPage.inputToDynamicTextbox("Address_PhoneNumber", editAddPhoneNumber);
		administrationPage.inputToDynamicTextbox("Address_FaxNumber", editAddFaxNumber);
		
		administrationPage.clickOnDynamicSaveButton("save");
		verifyTrue(administrationPage.isAlertMessageDisplayed("The address has been updated successfully."));
		administrationPage.clickToCloseAlertMessage();
		verifyTrue(administrationPage.isDynamicAdministrationSubPageHeaderDisplayed("Edit address"));

		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Address_FirstName", editAddFirstName));
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Address_LastName", editAddLastName));
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Address_Email", editAddEmail));
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Address_Company", editAddCompany));
		administrationPage.isSelectedItemInDynamicDropdownDisplayedByID("Address_CountryId", editAddCountry);
		administrationPage.isSelectedItemInDynamicDropdownDisplayedByID("Address_StateProvinceId", editAddState);
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Address_City", editAddCity));
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Address_Address1", editAddAddress));
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Address_ZipPostalCode", editAddZipcode));
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Address_PhoneNumber", editAddPhoneNumber));
		verifyTrue(administrationPage.isDynamicTextboxOrTextareaValueAsExpectation("Address_FaxNumber", editAddFaxNumber));

		administrationPage.clickOnDynamicBackLink("back to customer details");
		verifyTrue(administrationPage.isDynamicAdministrationSubPageHeaderDisplayed("Edit customer details"));
		
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(editAddEmail, "First name"), editAddFirstName);
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(editAddEmail, "Last name"), editAddLastName);
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(editAddEmail, "Phone number"), editAddPhoneNumber);
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(editAddEmail, "Fax number"), editAddFaxNumber);
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(editAddEmail, "Address"), 
																						editAddCompany + "\n" + 
																						editAddAddress + "\n" + 
																						editAddCity + "," + editAddState + "," + editAddZipcode + "\n" + 
																						editAddCountry);
		
	}
	
	@Test
	public void TC_15_DeleteAddressInCustomerDetails() {
		
		administrationPage.clickOnDynamicSidebarSubmenu("Customers");
		verifyTrue(administrationPage.isDynamicAdministrationSubPageHeaderDisplayed("Customers"));
		administrationPage.clickToOpenSearchSection();
		
		administrationPage.inputToDynamicTextbox("SearchEmail", newEmail);
		administrationPage.inputToDynamicTextbox("SearchFirstName", newFirstName);
		administrationPage.inputToDynamicTextbox("SearchLastName", newLastName);
		administrationPage.selectItemInDynamicDropdownByID("SearchMonthOfBirth", newMonthBirth);
		administrationPage.selectItemInDynamicDropdownByID("SearchDayOfBirth", newDayBirth);
		administrationPage.inputToDynamicTextbox("SearchCompany", newCompany);
		administrationPage.deSelectDynamicItemInCustomMultiDropdown("Registered");
		administrationPage.selectItemInCustomMultiDropdown("Guests");
		administrationPage.clickOnDynamicButtonByID("search-customers");
		verifyEquals(administrationPage.getTotalNumberSearchResultByDynamicColumnName("Name"), 1);
		verifyTrue(administrationPage.isDynamicValueDisplayedOnPageAsExpectation("Name", newFirstName + " " + newLastName));
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(newFirstName + " " + newLastName, "Customer roles"), "Guests");
		verifyEquals(administrationPage.getTextDynamicCellValueByReferValue(newFirstName + " " + newLastName, "Company name"), newCompany);

		administrationPage.clickOnDynamicIconByReferValue(newFirstName + " " + newLastName, "Edit");
		verifyTrue(administrationPage.isDynamicAdministrationSubPageHeaderDisplayed("Edit customer details - " + newLastName + " " + newFirstName));
		
		administrationPage.clickForOpenDynamicSection("customer-address");
		administrationPage.clickOnDynamicIconByReferValue(editAddEmail, "Delete");
		administrationPage.acceptAlert();
		verifyEquals(administrationPage.getTextDynamicCustomerDetailsDataTableEmptyMsg("customer-addresses-grid"), "No data available in table");
	}

	@AfterClass(alwaysRun=true)
	public void afterClass() { 
		closeBrowserAndDriver(driver);
		
	}

}
