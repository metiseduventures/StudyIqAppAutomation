package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.PaymentPage_OR;
import pojo.testdata.TestData;
import pojo.testdata.TestDataTest;
import util.Common_Function;

public class PaymentPageUtil {

	PaymentPage_OR paymentPageObj;
	Common_Function cfObj = new Common_Function();
	List<String> paymentPageMsgList = new ArrayList<String>();

	public PaymentPageUtil(AppiumDriver<MobileElement> driver) {
		paymentPageObj = new PaymentPage_OR();

		PageFactory.initElements(new AppiumFieldDecorator(driver), paymentPageObj);
	}

	public boolean selectPaymentOption(AppiumDriver<MobileElement> driver, String strPaymentOption, String isKey) {
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

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//android.view.View[@resource-id='ptm-login']", "xpath", 5);
					if (!result) {
						paymentPageMsgList.add("It is not paytm payment page");
						return result;
					}

					if (isKey.equalsIgnoreCase("pass")) {

						cfObj.scrollIntoText(driver, "Net Banking");

						result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.netBankPaymentOption(),
								5);
						if (!result) {
							paymentPageMsgList.add("The netbanking option in paytm is not visible");
							return result;
						}
						cfObj.commonClick(paymentPageObj.netBankPaymentOption());
						cfObj.commonClick(paymentPageObj.netBankPaymentOption());
						
						//need to change pay btn (not working)
						
						result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.successfulPayBtn(), 5);
						if (!result) {
							paymentPageMsgList.add("The successful Paytm payment btn is not visible");
							return result;
						}

						result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.failurePayBtn(), 5);
						if (!result) {
							paymentPageMsgList.add("The successful Paytm payment btn is not visible");
							return result;
						}

						cfObj.commonClick(paymentPageObj.successfulPayBtn());

						return true;

					} else if (isKey.equalsIgnoreCase("fail")) {

						driver.hideKeyboard();

						driver.navigate().back();

						cfObj.commonClick(paymentPageObj.yesBtn());

						String toastMsgLangChange = paymentPageObj.toastPayAbort().getAttribute("name");

						if (toastMsgLangChange.equalsIgnoreCase("Payment Aborted!")) {
							return true;
						} else {
							paymentPageMsgList.add("The payment is aborted but the toast is not visible");
							return false;
						}
					} else {
						paymentPageMsgList.add("The isKey is not pass or fail, it is wrong");
						return false;
					}

				} else {
					result = false;
				}
			}

			if (!result) {

				paymentPageMsgList.add("The payment method is not working");
			}

		} catch (Exception e) {
			result = false;
			paymentPageMsgList.add("selectPaymentOption_Exception: " + e.getMessage());
		}
		return result;
	}

}
