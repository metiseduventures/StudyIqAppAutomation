package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import apiUtil.CourseApiUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.CourseDetailPage_OR;
import pojo.courseList.CourseList;
import pojo.courseView.CourseView;
import pojo.testdata.TestData;
import util.Common_Function;
import util.ConfigFileReader;

public class CourseDetailPage {

	CourseDetailPage_OR courseDetailPageObj;
	LoginUtil loginutillObj;
	HomePageUtil homePageUtilObj;
	PaymentPageUtil paymentPageUtilObj;
	CourseApiUtil courseApiUtilObj;
	CourseView courseViewObj;
	CourseList courseListObj;
	LibraryPageUtil libraryPageUtil;
	ConfigFileReader configFileReader;
	public ArrayList<String> coursePageMsgList = new ArrayList<String>();

	public Common_Function cfObj = new Common_Function();

	public CourseDetailPage(AppiumDriver<MobileElement> driver) {

		courseDetailPageObj = new CourseDetailPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), courseDetailPageObj);
	}

	public boolean verifyPurchaseCourse(AppiumDriver<MobileElement> driver, TestData testData) {
		boolean result = true;
		loginutillObj = new LoginUtil(driver);
		String strCourseSlug = null;
		CourseView courseViewObj;
		CourseApiUtil courseApiUtilObj;
		homePageUtilObj = new HomePageUtil(driver);
		configFileReader = new ConfigFileReader();

		try {
			courseApiUtilObj = new CourseApiUtil();
			courseListObj = courseApiUtilObj.getCourseList("bestselling-courses");
			if (courseListObj == null) {
				coursePageMsgList.add("Error in getting course list from api");
				return false;
			}
			// for guest user (existing)
			if (testData.isGuestUser()) {

				result = loginutillObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
				if (!result) {
					coursePageMsgList.addAll(loginutillObj.loginMsgList);
					return result;
				}
				
				if (testData.getCourseType().contains("video")) {

					strCourseSlug = configFileReader.getVideoSlug();
					
					result = homePageUtilObj.clickOnCourseOnHomePage(driver);
					if (!result) {
						coursePageMsgList.addAll(homePageUtilObj.homePageMsglist);
						return result;
					}
				} else if (testData.getCourseType().contains("books")) {
					
					strCourseSlug = configFileReader.getBooksSlug();

					result = homePageUtilObj.clickOnBookOnHomePage(driver);
					if (!result) {
						coursePageMsgList.addAll(homePageUtilObj.homePageMsglist);
						return result;
					}
				} else if (testData.getCourseType().contains("live")) {
					
					strCourseSlug = configFileReader.getLiveSlug();

					result = homePageUtilObj.clickOnLiveCourseOnHomePage(driver);
					if (!result) {
						coursePageMsgList.addAll(homePageUtilObj.homePageMsglist);
						return result;
					}
				} else if (testData.getCourseType().contains("test-series")) {
					
					strCourseSlug = configFileReader.getTestseriesSlug();

					result = homePageUtilObj.clickOnTestSeriesOnHomePage(driver);
					if (!result) {
						coursePageMsgList.addAll(homePageUtilObj.homePageMsglist);
						return result;
					}
				}

				System.out.println(strCourseSlug);
				courseApiUtilObj = new CourseApiUtil();
				courseViewObj = new CourseView();
				courseViewObj = courseApiUtilObj.getCourseViewData(strCourseSlug);
				System.out.println(courseViewObj.getData().getPriceInfo());

				result = clickOnBuyNow(driver, testData);
				if (!result) {
					return result;
				}

				result = verifyEMIoption(driver, courseViewObj);
				if (!result) {
					return result;
				}

				result = verifyPackages(driver, courseViewObj);
				if (!result) {
					return result;
				}

				if (testData.getCourseType().equalsIgnoreCase("books")) {
					cfObj.scrollUtill(driver, 3);
				}

				testData.setCourseName(courseViewObj.getData().getCourseDetail().getCourseTitle());
				String courseName = courseViewObj.getData().getCourseDetail().getCourseTitle();

				result = cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.noOfOffersAvail(), 10);
				if (result) {

					String noOfOffersAvail = courseDetailPageObj.noOfOffersAvail().getText();
					String[] arr = noOfOffersAvail.split(" ");
					int countOfOffers = Integer.parseInt(arr[0]);

					if (countOfOffers > 0) {

						result = verifyInvalidCoupon(driver);
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

					}
				}

				result = buyNowPack(driver);
				if (!result) {
					return result;
				}

				result = verifyViewDetails(driver);
				if (!result) {
					return result;
				}

				if ((ConfigFileReader.strEnv).equalsIgnoreCase("stag")
						|| (ConfigFileReader.strEnv).equalsIgnoreCase("dev")) {

					paymentPageUtilObj = new PaymentPageUtil(driver);

					result = paymentPageUtilObj.selectPaymentOption(driver, testData.getPaymentMethod(), testData);
					if (!result) {
						coursePageMsgList.addAll(paymentPageUtilObj.paymentPageMsgList);
						return result;
					}

					if (testData.getIsKey().equalsIgnoreCase("pass")) {

						result = courseBuyStatus(driver);
						if (!result) {
							return result;
						}

						result = checkCourseInLibrary(driver, courseName, testData);
						if (!result) {
							return result;
						}

					} else {
						System.out.println("User on Choose Payment Method page- the payment is aborted");
					}

				} else if ((ConfigFileReader.strEnv).equalsIgnoreCase("prod")) {

					System.out.println("The envirnonment is production, everything working fine");

				} else {

					coursePageMsgList.add("The envirnoment is different from dev, stag and prod");
					return false;

				}
			} else {

				result = loginutillObj.doSignUp(driver);
				if (!result) {
					coursePageMsgList.addAll(loginutillObj.loginMsgList);
					return result;
				}
				
				if (testData.getCourseType().contains("video")) {

					strCourseSlug = configFileReader.getVideoSlug();
					
					result = homePageUtilObj.clickOnCourseOnHomePage(driver);
					if (!result) {
						coursePageMsgList.addAll(homePageUtilObj.homePageMsglist);
						return result;
					}
				} else if (testData.getCourseType().contains("books")) {
					
					strCourseSlug = configFileReader.getBooksSlug();

					result = homePageUtilObj.clickOnBookOnHomePage(driver);
					if (!result) {
						coursePageMsgList.addAll(homePageUtilObj.homePageMsglist);
						return result;
					}
				} else if (testData.getCourseType().contains("live")) {
					
					strCourseSlug = configFileReader.getLiveSlug();

					result = homePageUtilObj.clickOnLiveCourseOnHomePage(driver);
					if (!result) {
						coursePageMsgList.addAll(homePageUtilObj.homePageMsglist);
						return result;
					}
				} else if (testData.getCourseType().contains("test-series")) {
					
					strCourseSlug = configFileReader.getTestseriesSlug();

					result = homePageUtilObj.clickOnTestSeriesOnHomePage(driver);
					if (!result) {
						coursePageMsgList.addAll(homePageUtilObj.homePageMsglist);
						return result;
					}
				}

				System.out.println(strCourseSlug);
				courseApiUtilObj = new CourseApiUtil();
				courseViewObj = new CourseView();
				courseViewObj = courseApiUtilObj.getCourseViewData(strCourseSlug);
				System.out.println(courseViewObj.getData().getPriceInfo());

				result = clickOnBuyNow(driver, testData);
				if (!result) {
					return result;
				}

				result = verifyEMIoption(driver, courseViewObj);
				if (!result) {
					return result;
				}

				result = verifyPackages(driver, courseViewObj);
				if (!result) {
					return result;
				}

				if (testData.getCourseType().equalsIgnoreCase("books")) {
					cfObj.scrollUtill(driver, 3);
				}

				testData.setCourseName(courseViewObj.getData().getCourseDetail().getCourseTitle());
				String courseName = courseViewObj.getData().getCourseDetail().getCourseTitle();

				result = cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.noOfOffersAvail(), 10);
				if (result) {

					String noOfOffersAvail = courseDetailPageObj.noOfOffersAvail().getText();
					String[] arr = noOfOffersAvail.split(" ");
					int countOfOffers = Integer.parseInt(arr[0]);

					if (countOfOffers > 0) {

						result = verifyInvalidCoupon(driver);
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

					}
				}

				result = buyNowPack(driver);
				if (!result) {
					return result;
				}

				result = verifyViewDetails(driver);
				if (!result) {
					return result;
				}

				if ((ConfigFileReader.strEnv).equalsIgnoreCase("stag")
						|| (ConfigFileReader.strEnv).equalsIgnoreCase("dev")) {

					paymentPageUtilObj = new PaymentPageUtil(driver);

					result = paymentPageUtilObj.selectPaymentOption(driver, testData.getPaymentMethod(), testData);
					if (!result) {
						coursePageMsgList.addAll(paymentPageUtilObj.paymentPageMsgList);
						return result;
					}

					if (testData.getIsKey().equalsIgnoreCase("pass")) {

						result = courseBuyStatus(driver);
						if (!result) {
							return result;
						}

						result = checkCourseInLibrary(driver, courseName, testData);
						if (!result) {
							return result;
						}

					} else {
						System.out.println("User on course page - the payment is failed");
					}

				} else if ((ConfigFileReader.strEnv).equalsIgnoreCase("prod")) {

					System.out.println("The envirnonment is production, everything working fine");

				} else {

					coursePageMsgList.add("The envirnoment is different from dev, stag and prod");
					return false;

				}
			}

		} catch (Exception e) {
			result = false;
		}

		return result;
	}

	public boolean verifyInvalidCoupon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = clickOnAvailableOffer(driver);
			if (!result) {
				return result;
			}

			// wait for available offer page to be opened
			result = cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.availOfferLabelBox(), 30);
			if (!result) {
				System.out.println("Offer list pop up is not opened");
			}

			String invalidCode = "INVALID123";

			result = cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.getListTextCouponCode().get(0),
					5);
			if (!result) {
				coursePageMsgList.add("The input of enter code is not visible");
				return result;
			}

			// click on coupon text
			cfObj.commonClick(courseDetailPageObj.getListTextCouponCode().get(0));

			result = cfObj.commonSetTextTextBox(courseDetailPageObj.getListTextCouponCode().get(0), invalidCode);

			result = cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.applyCodeAfterInputBtn(), 5);
			if (!result) {
				coursePageMsgList.add("The Apply btn is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.cancelCodeAfterInputBtn(), 5);
			if (!result) {
				coursePageMsgList.add("The cancel btn is not visible");
				return result;
			}

			// click on apply button
			cfObj.commonClick(courseDetailPageObj.applyCodeAfterInputBtn());

			String toastMsgLangChange = courseDetailPageObj.toastInvalidCoupon().getAttribute("name");

			if (toastMsgLangChange.equalsIgnoreCase("Entered coupon code is invalid. Please try another code.")
					|| toastMsgLangChange.equalsIgnoreCase("Invalid Coupon Code")) {
				return true;
			} else {
				coursePageMsgList.add("The coupon is invalid but the toast is not visible");
				return false;
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verifyInvalidCoupon_Exception: " + e.getMessage());

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
			coursePageMsgList.add("selectCoupon_verifyAmount_Exception: " + e.getMessage());
		}

		return result;
	}

	public boolean clickOnBuyNow(AppiumDriver<MobileElement> driver, TestData testData) {
		boolean result = true;
		try {

			cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.getListBtnBuyNow().get(0), 5);

			cfObj.commonClick(courseDetailPageObj.getListBtnBuyNow().get(0));

			if (testData.getCourseType().equalsIgnoreCase("books")) {
				return true;
			}

			// wait for buy now pack page is display

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.PACK_TITLE, "id", 30);

			if (!result) {
				System.out.println("Choose your pack content is not visible");
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("clickOnBuyNow_Exception: " + e.getMessage());
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
			coursePageMsgList.add("clickOnAvailableOffer_Exception: " + e.getMessage());
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
			coursePageMsgList.add("changeCoupon_Exception: " + e.getMessage());
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

			cfObj.commonClick(courseDetailPageObj.applyCodeAfterInputBtn());
			// wait for coupon to be applied

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.OFFER_CHANGE_PACK, "id", 30);

			if (!result) {
				System.out.println("manual Coupon is not applied: coupon code: " + strCouponName);
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("applyManualCoupon_Exception: " + e.getMessage());
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
			coursePageMsgList.add("buyNowPack_Exception: " + e.getMessage());
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

	public boolean verifyPacks(AppiumDriver<MobileElement> driver, CourseView courseViewObj) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tv_pack_title", "id", 5);
			if (!result) {
				coursePageMsgList.add("The pack title is not visible");
				return result;
			}

			for (int i = 1; i < courseViewObj.getData().getPackages().get(0).getPackages().size(); i++) {

				cfObj.swipeLeftOnElement(courseDetailPageObj.packTitle(), driver);

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tv_pack_title", "id", 5);
				if (!result) {
					coursePageMsgList.add("The next pack title is not visible");
					return result;
				}

				if (courseViewObj.getData().getPackages().get(0).getPackages().size() > 2) {

					cfObj.swipeRightOnElement(courseDetailPageObj.packTitle(), driver);
					cfObj.swipeRightOnElement(courseDetailPageObj.packTitle(), driver);

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tv_pack_title", "id", 5);
					if (!result) {
						coursePageMsgList.add("The next pack title is not visible");
						return result;
					}
				}
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tv_pack_title", "id", 5);
			if (!result) {
				coursePageMsgList.add("The next pack title is not visible");
				return result;
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

			int noOfPacks = courseViewObj.getData().getPackages().get(0).getPackages().size();

			if (noOfPacks == 1) {

				String titleOfPack = courseDetailPageObj.packTitle().getText();
				if (titleOfPack.equalsIgnoreCase(choosenPack)) {
					return true;
				} else {
					System.out.println("The required pack is not present");
					return true; // in prod and stag different packs, so test case fails
				}
			}

			for (int i = 1; i < noOfPacks; i++) {
				String titleOfPack = courseDetailPageObj.packTitle().getText();
				if (titleOfPack.equalsIgnoreCase(choosenPack)) {
					return true;
				} else {
					cfObj.swipeLeftOnElement(courseDetailPageObj.packTitle(), driver);
				}

			}

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
			result = cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.viewDetailsBtn(), 5);
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
				if (courseAmount.equals(courseTotalAmount)) {

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

	public boolean verifyEMIoption(AppiumDriver<MobileElement> driver, CourseView courseViewObj) {
		boolean result = true;
		try {
			if (courseViewObj.getData().getCourseType().getCourseTypeName().equalsIgnoreCase("books")) {
				return true;
			}

			if (courseViewObj.getData().getCourseDetail().getIsEmiAvailable() != 0) {

				cfObj.scrollUtill(driver, 2);

				result = cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.emiOptionTitle(), 5);
				if (!result) {
					coursePageMsgList.add("Emi option title is not visible");
					return result;

				}
				result = cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.emiDesc(), 5);
				if (!result) {
					coursePageMsgList.add("The show details of emi desc not visible");
				}

				cfObj.commonClick(courseDetailPageObj.emiDesc());

				List<MobileElement> noOfInstallments = courseDetailPageObj.getListEmiTitlesOfInstallment();
				List<MobileElement> emiValidities = courseDetailPageObj.getListEmiValidityOfInstallments();
				List<MobileElement> emiAmounts = courseDetailPageObj.getListEmiAmountOfInstallments();

				for (int i = 0; i < noOfInstallments.size(); i++) {

					result = cfObj.commonWaitForElementToBeVisible(driver, noOfInstallments.get(i), 5);
					if (!result) {
						coursePageMsgList.add("The emi installment title is not visible");
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, emiValidities.get(i), 5);
					if (!result) {
						coursePageMsgList.add("The emi validity is not visible");
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, emiAmounts.get(i), 5);
					if (!result) {
						coursePageMsgList.add("The emi amount is not visible");
					}

				}

				result = cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.emiDialogBtn(), 5);
				if (!result) {
					coursePageMsgList.add("The avail btn is not visible");
				}

				cfObj.commonClick(courseDetailPageObj.emiDialogBtn());

				String checkEMIstatus = courseDetailPageObj.emiOptionTitle().getText();

				if (checkEMIstatus.contains("Applied")) {
					cfObj.commonClick(courseDetailPageObj.emiCheckedBtn());
				} else {
					coursePageMsgList.add("The emi is applied but title not changed with applied");
					return false;
				}
			} else {
				System.out.println("Emi option is not available");
				return true;
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verifyEMIoption_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean courseBuyStatus(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.paymentStatus(), 5);
			if (!result) {
				coursePageMsgList.add("The status of course purchase is not visible or wrong page");
				return result;
			}

			String expectedPayStatus = "Your Order has been placed";
			String actualPayStatus = courseDetailPageObj.paymentStatus().getText();

			if (expectedPayStatus.equalsIgnoreCase(actualPayStatus)) {

				result = cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.paymentStatusDesc(), 5);
				if (!result) {
					coursePageMsgList.add("The description of course purchase is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, courseDetailPageObj.goToLibraryBtn(), 5);
				if (!result) {
					coursePageMsgList.add("The go to library btn is not visible");
					return result;
				}

				cfObj.commonClick(courseDetailPageObj.goToLibraryBtn());

				return true;

			} else {
				coursePageMsgList.add("Payment Unsuccessful and Order not placed");
				return false;
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("courseBuyStatus_Exception: " + e.getMessage());
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

	public boolean clickOnShareIconOnCourseDetailPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

		} catch (Exception e) {
			coursePageMsgList.add("clickOnShareIconOnCourseDetailPage_Exception " + e.getMessage());
			return result;
		}
		return result;
	}

	public boolean verifyPackages(AppiumDriver<MobileElement> driver, CourseView courseViewObj) {
		boolean result = true;
		try {

			if (courseViewObj.getData().getCourseType().getCourseTypeName().equalsIgnoreCase("books")) {

				result = verifyPackageFormForBooks(driver);
				if (!result) {
					return result;
				}

			} else {

				result = verifyPacks(driver, courseViewObj);
				if (!result) {
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verficationOfPackages_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyPackageFormForBooks(AppiumDriver<MobileElement> driver) { // make these locators of books
		boolean result = true;
		try {

			cfObj.scrollIntoText(driver, "State");

			if (courseDetailPageObj.getListBookingFormName().size() == 0) {
				coursePageMsgList.add("Name field is not display in booking form");
				result = false;
			}
			if (courseDetailPageObj.getListBookingFormPhoneNo().size() == 0) {
				coursePageMsgList.add("Phone number field is not display in booking form");
				result = false;
			}
			if (courseDetailPageObj.getListBookingFormEmail().size() == 0) {
				coursePageMsgList.add("Email field is not display in booking form");
				result = false;
			}
			if (courseDetailPageObj.getListBookingFormAddress().size() == 0) {
				coursePageMsgList.add("Address field is not display in booking form");
				result = false;
			}
			if (courseDetailPageObj.getListBookingFormPinCode().size() == 0) {
				coursePageMsgList.add("Pin code field is not display in booking form");
				result = false;
			}
			if (courseDetailPageObj.getListBookingFormCity().size() == 0) {
				coursePageMsgList.add("City field is not display in booking form");
				result = false;
			}
			if (courseDetailPageObj.getListBookingFormState().size() == 0) {
				coursePageMsgList.add("State field is not display in booking form");
				result = false;
			}

			if (!result) {
				return result;
			}

			if (courseDetailPageObj.getListBookingFormEmail().get(0).getText().length() < 0) {
				coursePageMsgList.add("Email field is empty");
				return result;
			}
			if (courseDetailPageObj.getListBookingFormName().get(0).getText().length() < 0) {
				coursePageMsgList.add("Name field is empty");
				return result;
			}
			if (courseDetailPageObj.getListBookingFormPhoneNo().get(0).getText().length() < 0) {
				coursePageMsgList.add("Phone field is empty");
				return result;
			}
			
			result = cfObj.commonSetTextTextBox(courseDetailPageObj.getListBookingFormName().get(0),
					"userName");
			if (!result) {
				coursePageMsgList.add("not able to add name");
				return result;
			}
			
			result = cfObj.commonSetTextTextBox(courseDetailPageObj.getListBookingFormAddress().get(0),
					"Unitech Cyber park");
			if (!result) {
				coursePageMsgList.add("not able to add address");
				return result;
			}
			
			driver.hideKeyboard();

			result = cfObj.commonSetTextTextBox(courseDetailPageObj.getListBookingFormPinCode().get(0), "122003");
			if (!result) {
				coursePageMsgList.add("not able add to pin code");
				return result;
			}

			driver.hideKeyboard();

			if (!courseDetailPageObj.getListBookingFormCity().get(0).getText().equalsIgnoreCase("GURUGRAM")) {
				coursePageMsgList.add("Mismatch in city expected to be Gurugram");
				return result;
			}

			if (!courseDetailPageObj.getListBookingFormState().get(0).getText().equalsIgnoreCase("HARYANA")) {
				coursePageMsgList.add("Mismatch in State expected to be HARYANA");
				return result;
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("verifyPackageFormFormBooks_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean checkCourseInLibrary(AppiumDriver<MobileElement> driver, String courseName, TestData testData) {
		boolean result = true;
		libraryPageUtil = new LibraryPageUtil(driver);
		try {

			if (!testData.getCourseType().equalsIgnoreCase("books")) {
				result = libraryPageUtil.checkCourseInLibrary(driver, testData, courseName);
				if (!result) {
					coursePageMsgList.addAll(libraryPageUtil.libraryPageMsgList);
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			coursePageMsgList.add("checkCourseInLibrary_Exception" + e.getMessage());
		}
		return result;
	}

}
