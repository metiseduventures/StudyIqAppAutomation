package applicationUtil;

import java.util.ArrayList;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.CourseDetailPage_OR;
import pojo.testdata.TestData;
import util.Common_Function;
import util.ConfigFileReader;

public class CourseDetailPage {

	CourseDetailPage_OR courseDetailPageObj;
	LoginUtil loginutillObj;
	HomePageUtil homePageUtilObj;
	PaymentPageUtil paymentPageUtilObj;
	public ArrayList<String> coursePageMsgList = new ArrayList<String>();

	public Common_Function cfObj = new Common_Function();

	public CourseDetailPage(AppiumDriver<MobileElement> driver) {

		courseDetailPageObj = new CourseDetailPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), courseDetailPageObj);

	}

	public boolean verifyPurchaseCourse(AppiumDriver<MobileElement> driver, TestData testData) {
		boolean result = true;
		try {
			

			loginutillObj = new LoginUtil(driver);

			result = loginutillObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
			if (!result) {
				return result;
			}

			homePageUtilObj = new HomePageUtil(driver);
			result = homePageUtilObj.clickOnCourseOnHomePage(driver);

			if (!result) {
				return result;
			}

			result = clickOnBuyNow(driver);

			if (!result) {
				return result;
			}

			result = selectCoupon(driver);

			if (!result) {
				return result;
			}

			result = changeCoupon(driver);

			if (!result) {
				return result;
			}

			result = applyManualCoupon(driver);

			if (!result) {
				return result;
			}

			result = buyNowPack(driver);

			if (!result) {
				return result;
			}

			paymentPageUtilObj = new PaymentPageUtil(driver);

			result = paymentPageUtilObj.selectPaymentOption(driver, testData.getPaymentMethod());

			if (!result) {
				return result;
			}

		} catch (Exception e) {
			result = false;
		}

		return result;
	}

	public boolean selectCoupon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = clickOnAvailableOffer(driver);
			if (!result) {
				return result;
			}
			// apply first coupon
			cfObj.commonClick(courseDetailPageObj.getListBtnBuyApplyOffer().get(0));
			// wait for coupon to be applied

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.OFFER_CHANGE_PACK, "id", 30);

			if (!result) {
				System.out.println("Coupon is not applied");
			}

		} catch (Exception e) {
			result = false;
		}

		return result;
	}

	public boolean clickOnBuyNow(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(courseDetailPageObj.getListBtnBuyNow().get(0));

			// wait for buy now pack page is display

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.PACK_TITLE, "id", 30);

			if (!result) {
				System.out.println("Choose your pack content is not visible");
			}

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean clickOnAvailableOffer(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.scrollUtillTheElementFound(driver, ConstantUtil.OFFER_PACK_TITLE, "id");
			if (!result) {
				System.out.println("no offer available");
				return result;
			}
			cfObj.commonClick(courseDetailPageObj.getListLableOfferAvailable().get(0));
			// wait for available offer page to be opened

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.APPLY_OFFER, "id", 30);

			if (!result) {
				System.out.println("Offer list pop up is not opened");
			}

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean changeCoupon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			// Click on change offer
			cfObj.commonClick(courseDetailPageObj.getListLableChangePack().get(0));
			// wait for available offer page to be opened

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.APPLY_OFFER, "id", 30);

			if (!result) {
				System.out.println("Offer list pop up is not opened");
			}

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean applyManualCoupon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String strCouponName;
		try {
			strCouponName = courseDetailPageObj.getListOfferName().get(0).getText().toString().trim();
			System.out.println("strCouponName: " + strCouponName);

			// click on coupon text
			cfObj.commonClick(courseDetailPageObj.getListTextCouponCode().get(0));
			result = cfObj.commonSetTextTextBox(courseDetailPageObj.getListTextCouponCode().get(0), strCouponName);

			// click on apply button

			cfObj.commonClick(courseDetailPageObj.getListBtnApplyInputOffer().get(0));
			// wait for coupon to be applied

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.OFFER_CHANGE_PACK, "id", 30);

			if (!result) {
				System.out.println("manual Coupon is not applied: coupon code: " + strCouponName);
			}

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean buyNowPack(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			// click on buy now
			cfObj.commonClick(courseDetailPageObj.getListBtnBuyNowPack().get(0));

			// wait for payment page to be opened

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.PAYMENT_OPTION_NAME, "id",
					30);
			if (!result) {
				System.out.println("Payment page not opened when click on buy now button");
			}

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean verifyCourseDetailPageViaDeepLink(AppiumDriver<MobileElement> driver, TestData testData) {
		boolean result = true;
		try {
			if (!testData.isGuestUser()) {
				loginutillObj = new LoginUtil(driver);
				result = loginutillObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
			}
			driver.get("https://staging.studyiq.com/course-detail/UPSC");

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verifyCourseDetailPageViaDeepLink_Exception: " + e.getMessage());
		}
		return result;

	}

}
