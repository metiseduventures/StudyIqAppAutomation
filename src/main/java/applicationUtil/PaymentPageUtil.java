package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.PaymentPage_OR;
import pojo.testdata.TestData;
import util.Common_Function;

public class PaymentPageUtil {

	PaymentPage_OR paymentPageObj;
	Common_Function cfObj = new Common_Function();
	List<String> paymentPageMsgList = new ArrayList<String>();

	public PaymentPageUtil(AppiumDriver<MobileElement> driver) {
		paymentPageObj = new PaymentPage_OR();

		PageFactory.initElements(new AppiumFieldDecorator(driver), paymentPageObj);
	}

	public boolean selectPaymentOption(AppiumDriver<MobileElement> driver, String strPaymentOption, TestData testData) {
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

					Thread.sleep(4000);

					driver.hideKeyboard();

					cfObj.scrollIntoText(driver, "Net Banking");

					result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.netBankPaymentOption(), 10);
					if (!result) {
						paymentPageMsgList.add("The netbanking option in paytm is not visible");
						return result;
					}
					cfObj.commonClick(paymentPageObj.netBankPaymentOption());

					result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.payBtn(), 10);
					if (!result) {	
						paymentPageMsgList.add("The payBtn is not visible");
						return result;
					}
					cfObj.commonClick(paymentPageObj.payBtn());

					result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.successfulPayBtn(), 10);
					if (!result) {
						paymentPageMsgList.add("The successful Paytm payment btn is not visible");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.failurePayBtn(), 10);
					if (!result) {
						paymentPageMsgList.add("The successful Paytm payment btn is not visible");
						return result;
					}

					if (testData.getIsKey().equalsIgnoreCase("pass")) {

						cfObj.commonClick(paymentPageObj.successfulPayBtn());
						result = true;
						
					} else if (testData.getIsKey().equalsIgnoreCase("fail")) {

						boolean bool = true;
						while (bool) {

							cfObj.commonClick(paymentPageObj.failurePayBtn());

							result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tv_payment_status_pc",
									"id", 15);
							if (result) {

								result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tv_payment_desc_pc",
										"id", 10);
								if (!result) {
									paymentPageMsgList.add("The payment status description is not visible");
									return result;
								}

								result = cfObj.commonWaitForElementToBeVisible(driver,
										paymentPageObj.retryPayAfterUnsuccessBtn(), 10);
								if (!result) {
									paymentPageMsgList.add("The payment status description is not visible");
									return result;
								}

								cfObj.commonClick(paymentPageObj.retryPayAfterUnsuccessBtn());

								result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "view_details_ps",
										"id", 10);
								if (!result) {
									paymentPageMsgList.add("The payment choose method page is not visible");
									return result;
								}

								driver.navigate().back();

								driver.navigate().back();
								
								result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "txt_course_title", "id", 10);
								if (!result) {
									paymentPageMsgList.add("It is not on the cdp page");
									return result;
								}

								return true;
							}

							result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
									"//android.widget.TextView[contains(@text,'Your transaction has failed.')]",
									"xpath", 10);
							if (!result) {
								paymentPageMsgList.add("The transaction failed popup not visible");
								return result;
							}

							result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.retryPaymentBtn(),
									10);
							if (!result) {
								paymentPageMsgList.add("The retry btn is not visible");
								return result;
							}

							cfObj.commonClick(paymentPageObj.retryPaymentBtn());

							result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.payBtn(), 10);
							if (!result) {
								paymentPageMsgList.add("The payBtn is not visible");
								return result;
							}

							cfObj.commonClick(paymentPageObj.payBtn());

							result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.successfulPayBtn(),
									10);
							if (!result) {
								paymentPageMsgList.add("The successful Paytm payment btn is not visible");
								return result;
							}

							result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.failurePayBtn(), 10);
							if (!result) {
								paymentPageMsgList.add("The successful Paytm payment btn is not visible");
								return result;
							}

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
