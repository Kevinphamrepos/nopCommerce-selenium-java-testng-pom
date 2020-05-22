package pageUIs;

public class BE_AdministrationPageUI { 
	
	public static final String DASHBOARD_PAGE_TITLE_DISPLAYED = "//div[@class='content-header']/h1[contains(text(),'Dashboard')]";
	public static final String ADMIN_SUB_PAGE_TITLE_TEXT = "//h1[@class='pull-left']"; 
	public static final String DYNAMIC_SIDEBAR_MENU = "//div[@class='sidebar']//span[text()='%s']";
	public static final String DYNAMIC_SIDEBAR_SUB_MENU = "//ul[@class='treeview-menu']//span[text()='%s']";
	public static final String DYNAMIC_SEARCH_PANEL = "//div[@class='row search-row opened']//div[@class='s%s']"; 
	public static final String ADMIN_SEARCH_SECTION_DISPLAYED = "//div[contains(@class,'search-body')]";
	public static final String ADMIN_SEARCH_BAR = "//div[@class='search-text']";
	public static final String DYNAMIC_BUTTON_BY_ID = "//button[@id='%s']";
	public static final String DYNAMIC_SEARCH_AREA_TEXTBOX = "";
	public static final String DYNAMIC_SEARCH_AREA_DROPDOWN = "";
	public static final String SEARCH_RESULT_PRODUCT_NAME = "";
	public static final String DYNAMIC_COLUMN_HEADER_INDEX  = "//thead/tr/th[contains(text(),'%s')]/preceding-sibling::th"; 
	public static final String DYNAMIC_COLUNM_DATA_BY_COLUMN_INDEX = "//tbody/tr/td[%s]";
	public static final String DYNAMIC_TABLE_COLUMN_TEXT_BY_PRODUCT_NAME = "//tbody/tr/td[contains(text(),'%s')]/ancestor::tr/td[%s]"; 
	public static final String DYNAMIC_PUBLISHED_ICON_BY_PRODUCT_NAME = "//tbody/tr/td[contains(text(),'%s')]/ancestor::tr/td[contains(@class,' text-center')]/i";
	public static final String DYNAMIC_ICON_BY_REFER_VALUE = "//tbody/tr/td[contains(text(),'%s')]/ancestor::tr/td/a[contains(text(),'%s')]";
	public static final String DATA_TABLE_EMPTY_MSG_TEXT = "//tbody/tr/td[@class='dataTables_empty']";
	public static final String DYNAMIC_CUSTOMER_DETAILS_TABLE_EMPTY_MSG_TEXT_BY_ID = "//table[@id='%s']//td[@class='dataTables_empty'][contains(text(),'No data available in table')]";
	public static final String BLUE_ADD_NEW_BUTTON = "//div[@class='pull-right']/a[@class='btn bg-blue']";
	public static final String DYNAMIC_RIGHT_BLUE_BUTTON = "//div[@class='pull-right']//button[@class='btn bg-blue' and contains(text(),'%s')]";
	public static final String CUSTOMER_ROLES_MULTI_DROPDOWN_XPATH = "//div[@class='k-multiselect-wrap k-floatwrap']"; 
	public static final String CUSTOMER_ROLES_ALL_ITEMS_XPATH = "//ul[@id='SelectedCustomerRoleIds_listbox']/li";
	public static final String DYNAMIC_SELECTED_ROLE = "//ul[@id='SelectedCustomerRoleIds_taglist']/li/span[text()='%s']";
	public static final String DYNAMIC_SELECTED_ROLE_ITEM_DELETE_ICON = "//ul[@id='SelectedCustomerRoleIds_taglist']/li/span[text()='%s']/following-sibling::span[@class='k-icon k-delete']";
	public static final String DYNAMIC_SAVE_BUTTON = "//button[@name='%s']";
	public static final String ALERT_MESSAGE_TEXT = "//div[@class='alert alert-success alert-dismissable']";
	public static final String ALERT_MESSAGE_CLOSE_BUTTON = "//div[@class='alert alert-success alert-dismissable']/button";
	public static final String DYNAMIC_BACK_LINK = "//h1[@class='pull-left']//a[contains(text(),'%s')]";
	public static final String ADDRESSES_LINK = "//span[contains(text(),'Addresses')]";
	public static final String ADD_NEW_ADDRESS_BUTTON = "//button[contains(text(),'Add new address')]";
	public static final String DYNAMIC_SECTION_BY_ID_COLLAPSED_CONTAINER = "//div[@id='%s']//div[contains(@class,'panel-container')]";
	public static final String DYNAMIC_SECTION_BY_ID_HEADING_PANEL = "//div[@id='%s']//div[@class='panel-heading']";
	
	
	
	
	
}