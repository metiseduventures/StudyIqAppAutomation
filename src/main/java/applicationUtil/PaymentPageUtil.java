package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.PaymentPage_OR;
import util.Common_Function;

public class PaymentPageUtil {

	PaymentPage_OR paymentPageObj;
	Common_Function cfObj = new Common_Function();
	List<String> paymentPageMsgList = new ArrayList<String>();

	public PaymentPageUtil(AppiumDriver<MobileElement> driver) {
		paymentPageObj = new PaymentPage_OR();

		PageFactory.initElements(new AppiumFieldDecorator(driver), paymentPageObj);
	}

	public boolean selectPaymentOption(AppiumDriver<MobileElement> driver, String strPaymentOption) {
		boolean result = true;

		try {

			for (MobileElement paymentOptionElement : paymentPageObj.getListPaymentOption()) {

				if (paymentOptionElement.getText().toString().equalsIgnoreCase(strPaymentOption)) {
					cfObj.commonClick(paymentOptionElement);
					break;
				}

			}
			// if address is not fill enter the address

			if (cfObj.commonGetElements(driver, "//android.widget.CheckedTextView[@text = 'Bihar']", "xpath")
					.size() > 0) {
				cfObj.commonClick(
						cfObj.commonGetElement(driver, "//android.widget.CheckedTextView[@text = 'Bihar']", "xpath"));
			}

		} catch (Exception e) {
			result = false;
			paymentPageMsgList.add("selectPaymentOption_Exception: " + e.getMessage());
		}
		return result;
	}

}
