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
				result = cfObj.commonWaitForElementToBeVisible(driver, paymentOptions.get(i), 5);
				if (!result) {
					paymentPageMsgList.add("The paymentOption name is not visible");
				}
			}

			for (int i = 0; i < paymentOptions.size(); i++) {

				String methodString = paymentOptions.get(i).getText();

				if (methodString.equalsIgnoreCase(strPaymentOption) && strPaymentOption.equalsIgnoreCase("Paytm")) {

					cfObj.commonClick(paymentOptions.get(i));

					Thread.sleep(3000);

					driver.hideKeyboard();

					cfObj.scrollIntoText(driver, "Net Banking");

					result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.netBankPaymentOption(), 5);
					if (!result) {
						paymentPageMsgList.add("The netbank option in paytm is not visible");
					}
					cfObj.commonClick(paymentPageObj.netBankPaymentOption());
					
					result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.payBtn(), 5);
					if (!result) {
						paymentPageMsgList.add("The payBtn is not visible");
					}
					cfObj.commonClick(paymentPageObj.payBtn());

					result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.successfulPayBtn(), 5);
					if (!result) {
						paymentPageMsgList.add("The successful Paytm payment btn is not visible");
					}
					cfObj.commonClick(paymentPageObj.successfulPayBtn());
				} else {
					cfObj.commonClick(paymentOptions.get(i));

					paymentPageMsgList.add("The payment method is not working");
					return false;
				}
			}

		} catch (Exception e) {
			result = false;
			paymentPageMsgList.add("selectPaymentOption_Exception: " + e.getMessage());
		}
		return result;
	}

}
