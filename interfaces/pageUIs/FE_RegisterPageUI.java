package pageUIs;

public class FE_RegisterPageUI {
	
	public static final String REGISTER_FORM = "//div[@class='page registration-page']";
	public static final String MALE_RADIOBUTTON = "//input[@id='gender-male']";
	public static final String FIRSTNAME_TEXTBOX = "//input[@id='FirstName']";
	public static final String LASTNAME_TEXTBOX = "//input[@id='LastName']";
	public static final String DOB_DROPDOWN = "//select[@name='DateOfBirthDay']";
	public static final String MOB_DROPDOWN = "//select[@name='DateOfBirthMonth']";
	public static final String YOB_DROPDOWN = "//select[@name='DateOfBirthYear']";
	public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
	public static final String COMPANY_TEXTBOX = "//input[@id='Company']";
	public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
	public static final String CONFIRMPASSWORD_TEXTBOX = "//input[@id='ConfirmPassword']";
	public static final String REGISTER_BUTTON = "//input[@id='register-button']";
	public static final String REGISTER_SUCCESS_MESSAGE_DISPLAYED = "//div[@class='result' and text()='Your registration completed']";
	public static final String REGISTER_SUCCESS_MESSAGE_TEXT = "//div[@class='result']";
	public static final String LOGOUT_LINK = "//a[@class='ico-logout' and text()='Log out']";
	public static final String REGISTER_EXISTED_EMAIL_MGS_TEXT = "//div[@class='message-error validation-summary-errors']//li";
	public static final String REGISTER_EXISTED_EMAIL_MGS_DISPLAYED = "//div//li[text()='The specified email already exists']";
	public static final String FIRSTNAME_ERROR_MESSAGE_DISPLAYED = "//span[@id='FirstName-error']";
	public static final String FIRSTNAME_ERROR_MESSAGE_TEXT = "//span[@id='FirstName-error']";
	public static final String LASTNAMEE_ERROR_MESSAGE_DISPLAYED = "//span[@id='LastName-error']";
	public static final String LASTNAME_ERROR_MESSAGE_TEXT = "//span[@id='LastName-error']";
	public static final String EMAIL_ERROR_MESSAGE_DISPLAYED = "//span[@id='Email-error']";
	public static final String EMAIL_ERROR_MESSAGE_TEXT = "//span[@id='Email-error']";
	public static final String INVALID_PASSWORD_ERROR_MESSAGE_TEXT_1 = "//span[@id='Password-error']/p";
	public static final String INVALID_PASSWORD_ERROR_MESSAGE_TEXT_2 = "//span[@id='Password-error']//li";
	public static final String PASSWORD_ERROR_MESSAGE_DISPLAYED = "//span[@id='Password-error']";
	public static final String PASSWORD_ERROR_MESSAGE_TEXT = "//span[@id='Password-error']";
	public static final String CONFIRMPASSWORD_ERROR_MESSAGE_DISPLAYED = "//span[@id='ConfirmPassword-error']";
	public static final String CONFIRMPASSWORD_ERROR_MESSAGE_TEXT = "//span[@id='ConfirmPassword-error']";
	

	
	
	
	
}
