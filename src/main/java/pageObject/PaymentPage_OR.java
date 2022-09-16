package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class PaymentPage_OR {
	
	
	@AndroidFindBy(id = "tv_payment_option_name")
	private List<MobileElement> listPaymentOption;

	public List<MobileElement> getListPaymentOption() {
		return listPaymentOption;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@resource-id='ptm-nb']/android.view.View/android.view.View/android.widget.TextView")
	private MobileElement netBankPaymentOptionElement;
	
	public MobileElement netBankPaymentOption(){
		return netBankPaymentOptionElement;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'PAY')]")
	private MobileElement payBtnElement;
	
	public MobileElement payBtn() {
		return payBtnElement;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'Successful')]")
	private MobileElement successfulPayBtnElement;
	
	public MobileElement successfulPayBtn() {
		return successfulPayBtnElement;
	}
	

}
