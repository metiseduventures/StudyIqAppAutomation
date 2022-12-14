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

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Net Banking')]")
	private MobileElement netBankPaymentOptionElement;

	public MobileElement netBankPaymentOption() {
		return netBankPaymentOptionElement;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'PAY')]")
	private MobileElement payBtnElement;

	public MobileElement payBtn() {
		return payBtnElement;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button")
	private List<MobileElement> payBtnElements;
	
	public List<MobileElement> allPayBtns(){
		return payBtnElements;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'Successful')]")
	private MobileElement successfulPayBtnElement;

	public MobileElement successfulPayBtn() {
		return successfulPayBtnElement;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'Failure')]")
	private MobileElement failurePayBtnElement;

	public MobileElement failurePayBtn() {
		return failurePayBtnElement;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'Retry')]")
	private MobileElement retryPaymentBtnElement;

	public MobileElement retryPaymentBtn() {
		return retryPaymentBtnElement;
	}

	@AndroidFindBy(id = "bt_action_pc")
	private MobileElement retryPayAfterUnsuccessBtn;

	public MobileElement retryPayAfterUnsuccessBtn() {
		return retryPayAfterUnsuccessBtn;
	}

	@AndroidFindBy(id = "android:id/button1")
	private MobileElement yesBtnElement;

	public MobileElement yesBtn() {
		return yesBtnElement;
	}

	@AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
	private MobileElement toastPayAbortElement;

	public MobileElement toastPayAbort() {
		return toastPayAbortElement;
	}

}
