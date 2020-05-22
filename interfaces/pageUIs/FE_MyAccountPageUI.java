package pageUIs;

public class FE_MyAccountPageUI {
	
	public static final String DYNAMIC_ACCOUNT_NAVIGATION = "//div[@class='block block-account-navigation']//li/a[text()='%s']";
	public static final String MY_ACCOUNT_PAGE_TITLE = "//div[@class='page-title']/h1";
	public static final String ACCOUNT_INFO_SECTION = "//div[@class='section address-item']";
	public static final String DYNAMIC_ACCOUNT_INFO = "//div[@class='section address-item']//ul[@class='info']/li[@class='%s']";
	public static final String DYNAMIC_PRODUCT_REVIEW_NAME = "//div[@class='review-info']/span/a[text()='%s']";
	public static final String ACCOUNT_INFO_DELETE_ALL = "//div[@class='buttons']/input[@value='Delete']";
	public static final String DYNAMIC_ORDER_NUMBER_DISPLAYED = "//div[@class='order-list']//div[@class='title']/strong[contains(text(),'%s')]";
	public static final String DYNAMIC_ORDER_DETAILS_LINK = "//strong[contains(text(),'%s')]/parent::div/following-sibling::div/input[@value='Details']";
	
}
