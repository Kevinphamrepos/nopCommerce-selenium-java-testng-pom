package pageUIs;

public class FE_CheckoutPageUI {
	
	public static final String DYNAMIC_CHECKOUT_CONTINUE_BUTTON = "//div[@id='%s']//input[@value='Continue']";
	public static final String DYNAMIC_CHECKOUT_RADIOBUTTON_CHECKBOX = "//label[contains(text(),'%s')]/preceding-sibling::input";
	public static final String CHECKOUT_SECTION_PAYMENT_INFO = "//div[@class='section payment-info']";
	public static final String DYNAMIC_CHECKOUT_INFO_LIST = "//div[@class='%s']//ul[@class='info-list']";
	public static final String CHECKOUT_CART_OPTIONS = "//div[@class='cart-options']/div";
	public static final String CART_FOOTER_TOTAL_INFO = "//div[@class='total-info']//table[@class='cart-total']";
	public static final String DYNAMIC_CART_FOOTER_TOTAL_DETAIL_INFO = "//tr[@class='%s']";
	public static final String ORDER_SUCCESSFULLY_MSG = "//div[@class='section order-completed']//div[@class='title']/strong";
	public static final String ORDER_NUMBER_DISPLAYED = "//div[@class='order-number']/strong";

}
