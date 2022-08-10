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

}
