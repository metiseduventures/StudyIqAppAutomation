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

			List<MobileElement> paymentOptions = paymentPageObj.getListPaymentOption();

			for (int i = 0; i < paymentOptions.size(); i++) {
				if (paymentOptions.get(i).getText().equalsIgnoreCase(strPaymentOption)) {
					cfObj.commonClick(paymentOptions.get(i));

					Thread.sleep(4000);

					driver.hideKeyboard();

					cfObj.scrollIntoText(driver, "Net Banking");

					cfObj.commonClick(paymentPageObj.netBankPaymentOption());

					cfObj.commonClick(paymentPageObj.payBtn());

					
					cfObj.commonClick(paymentPageObj.successfulPayBtn());
					return true;
				}
			}
			paymentPageMsgList.add("The payment is not available");
			result = false;
		} catch (Exception e) {
			result = false;
			paymentPageMsgList.add("selectPaymentOption_Exception: " + e.getMessage());
		}
		return result;
	}

}
