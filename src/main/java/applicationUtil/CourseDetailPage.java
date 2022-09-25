package applicationUtil;

import java.util.ArrayList;
import java.util.List;

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
			// login to application
			loginutillObj = new LoginUtil(driver);

			result = loginutillObj.doSignUp(driver);
			if (!result) {
				return result;
			}

			homePageUtilObj = new HomePageUtil(driver);
			result = homePageUtilObj.clickOnCourseOnHomePage(driver);

			if (!result) {
				return result;
			}
			// click on buy now
			result = clickOnBuyNow(driver);

			if (!result) {
				return result;
			}
			
			result = verifyEMIoption(driver);
			if (!result) {
				return result;
			}

			result = verifyPacks(driver);
			if (!result) {
				return result;
			}

			result = selectCoupon_verifyAmount(driver);

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

			result = chooseYourPack(driver, testData.getChoosePack());
			if (!result) {
				return result;
			}

			result = buyNowPack(driver);

			if (!result) {
				return result;
			}

			result = verifyViewDetails(driver);
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

	public boolean selectCoupon_verifyAmount(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			String packageAmountString = courseDetailPageObj.packSellingPrice().getText();
			Double packageAmount = amountCorrectFormat(packageAmountString);

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

			String discountAmountString = courseDetailPageObj.packDiscountPrice().getText();
			Double discountAmount = amountCorrectFormat(discountAmountString);

			String afterCouponPackageAmountString = courseDetailPageObj.packSellingPrice().getText();
			Double afterCouponPackageAmount = amountCorrectFormat(afterCouponPackageAmountString);

			if (packageAmount == afterCouponPackageAmount + discountAmount) {
				return true;
			} else {
				coursePageMsgList.add("The amount is not same of packages before and after");
				return false;
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
			result = cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.availOfferLabelBox(), 30);

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

	public boolean verifyPacks(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		boolean bool = true;
		try {

			while (bool) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tv_pack_title", "id", 10);
				if (!result) {
					coursePageMsgList.add("The pack title is not visible");
					return result;
				}
				String titleOfPack = courseDetailPageObj.packTitle().getText();

				cfObj.swipeLeftOnElement(courseDetailPageObj.packTitle(), driver);

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tv_pack_title", "id", 10);
				if (!result) {
					coursePageMsgList.add("The next pack title is not visible");
					return result;
				}

				String nextTitleOfPack = courseDetailPageObj.packTitle().getText();

				if (titleOfPack.equalsIgnoreCase(nextTitleOfPack)) {
					bool = false;
				}

			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verifyPacks_Exception: " + e.getMessage());
		}
		return result;

	}

	public boolean chooseYourPack(AppiumDriver<MobileElement> driver, String choosenPack) {
		boolean result = true;
		boolean bool = true;
		try {

			while (bool) {
				String titleOfPack = courseDetailPageObj.packTitle().getText();
				if (titleOfPack.equalsIgnoreCase(choosenPack)) {

					bool = false;
					result = true;

				} else {

					cfObj.swipeLeftOnElement(courseDetailPageObj.packTitle(), driver);

					String nextTitleOfPack = courseDetailPageObj.packTitle().getText();

					if (titleOfPack.equalsIgnoreCase(nextTitleOfPack)) {
						System.out.println("No more packs available to swipe");
						coursePageMsgList.add("The pack is not available for this course");
						return true;
					}
				}
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("chooseYourPack_Exception: " + e.getMessage());
		}
		return result;

	}

	public boolean verifyViewDetails(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.viewDetailsBtn(), 10);
			if (!result) {
				coursePageMsgList.add("The view details btn not visible");
				return false;
			}

			cfObj.commonClick(courseDetailPageObj.viewDetailsBtn());

			String courseAmountString = courseDetailPageObj.viewDetailsCoursePrice().getText();
			Double courseAmount = amountCorrectFormat(courseAmountString);

			String courseTotalAmountString = courseDetailPageObj.viewDetailsTotalPrice().getText();
			Double courseTotalAmount = amountCorrectFormat(courseTotalAmountString);

			result = cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.viewDetailsDiscountPrice(), 5);
			if (result) {
				String courseDiscountAmountString = courseDetailPageObj.viewDetailsDiscountPrice().getText();
				Double courseDiscountAmount = amountCorrectFormat(courseDiscountAmountString);

				if (courseTotalAmount == courseAmount - courseDiscountAmount) {
					cfObj.commonClick(courseDetailPageObj.viewDetailsCloseBtn());
					return true;
				} else {

					coursePageMsgList.add("The amount in view details is not same");
					return false;
				}
			} else {
				if (courseAmount == courseTotalAmount) {
					cfObj.commonClick(courseDetailPageObj.viewDetailsCloseBtn());
					return true;
				} else {
					coursePageMsgList.add("The amount in view details is not same");
					return false;
				}
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verifyViewDetails_Exception: " + e.getMessage());
		}
		return result;

	}

	public boolean verifyEMIoption(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.scrollUtill(driver, 1);
			result = cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.emiOptionTitle(), 5);
			if (result) {
				result = cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.emiDesc(), 10);
				if (!result) {
					coursePageMsgList.add("The show details of emi desc not visible");
				}

				cfObj.commonClick(courseDetailPageObj.emiDesc());

				List<MobileElement> noOfInstallments = courseDetailPageObj.getListEmiTitlesOfInstallment();
				List<MobileElement> emiValidities = courseDetailPageObj.getListEmiValidityOfInstallments();
				List<MobileElement> emiAmounts = courseDetailPageObj.getListEmiAmountOfInstallments();

				for (int i = 0; i < noOfInstallments.size(); i++) {

					result = cfObj.commonWaitForElementToBeVisible(driver, noOfInstallments.get(i), 10);
					if (!result) {
						coursePageMsgList.add("The emi installment title is not visible");
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, emiValidities.get(i), 10);
					if (!result) {
						coursePageMsgList.add("The emi validity is not visible");
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, emiAmounts.get(i), 10);
					if (!result) {
						coursePageMsgList.add("The emi amount is not visible");
					}

				}

				result = cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.emiDialogBtn(), 10);
				if (!result) {
					coursePageMsgList.add("The avail btn is not visible");
				}

				cfObj.commonClick(courseDetailPageObj.emiDialogBtn());

				String checkEMIstatus = courseDetailPageObj.emiOptionTitle().getText();
				
				if (checkEMIstatus.contains("Applied")) {
					cfObj.commonClick(courseDetailPageObj.emiCheckedBtn());
				}
				else {
					coursePageMsgList.add("The emi is applied but title not changed with applied");
					return false;
				}

			} else {
				return true;
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verifyEMIoption_Exception: " + e.getMessage());
		}
		return result;
	}

	public Double amountCorrectFormat(String str) {
		int index = 0;
		for (int k = 0; k < str.length(); k++) {
			if (str.charAt(k) >= 48 && str.charAt(k) <= 57) {
				index = k;
				break;
			}
		}

		String[] arr = str.substring(index).split(",");
		String amnt = "";

		for (int m = 0; m < arr.length; m++) {
			amnt = amnt + arr[m];
		}
		Double amountMain = Double.parseDouble(amnt);
		return amountMain;
	}
}
