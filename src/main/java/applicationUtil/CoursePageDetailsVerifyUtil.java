package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.CoursePageDetailsVerify_OR;
import util.Common_Function;

public class CoursePageDetailsVerifyUtil {
	CoursePageDetailsVerify_OR cdpVerify_OR;
	LoginUtil loginutillObj;
	HomePageUtil homePageUtilObj;
	public ArrayList<String> cdpVerifyMsgList = new ArrayList<String>();

	public Common_Function cfObj = new Common_Function();

	public CoursePageDetailsVerifyUtil(AppiumDriver<MobileElement> driver) {

		cdpVerify_OR = new CoursePageDetailsVerify_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), cdpVerify_OR);

	}

	public boolean verifyCoursePage(AppiumDriver<MobileElement> driver) throws Exception {
		boolean result = true;
		loginutillObj = new LoginUtil(driver);

		try {
			boolean checkLoginPageOrNot = loginutillObj.checkSignUpLoginPage(driver);
			if (checkLoginPageOrNot == true) {

				result = loginutillObj.doSignUp(driver);
				if (!result) {
					cdpVerifyMsgList.addAll(loginutillObj.loginMsgList);
					return result;
				}

			} else {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.IMG_CLOSE, "id", 30);
				if (result) {
					cfObj.commonClick(cfObj.commonGetElement(driver, ConstantUtil.IMG_CLOSE, "id"));
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.NAV_LIB, "id", 30);
				if (!result) {
					cdpVerifyMsgList.add("Home page not opened after login");
					return result;
				}
			}

			homePageUtilObj = new HomePageUtil(driver);
			result = homePageUtilObj.clickOnCourseOnHomePage(driver);
			if (!result) {
				cdpVerifyMsgList.addAll(homePageUtilObj.homePageMsglist);
				return result;
			}

			result = courseInfo(driver);
			if (!result) {
				return result;
			}

			result = verifyBuyBtnAbove(driver);
			if (!result) {
				return result;
			}

			result = verifyCourseContent(driver);
			if (!result) {
				return result;
			}

			result = verifyCoursePrice(driver);
			if (!result) {
				return result;
			}

			result = verifyExamsCovered(driver);
			if (!result) {
				return result;
			}

			result = verifyAboutAuthor(driver);
			if (!result) {
				return result;
			}

			result = verifyDemoVideos(driver);
			if (!result) {
				return result;
			}

			result = verifyFreeCourses(driver);
			if (!result) {
				return result;
			}

			result = verifyTestimonials(driver);
			if (!result) {
				return result;
			}

			result = verifyBuyBtnBelow(driver);
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			result = false;
		}

		return result;
	}

	public boolean courseInfo(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			// title of page
			result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.titleOfPage(), 5);
			if (!result) {
				cdpVerifyMsgList.add("The title of page is not visible");
				return result;
			}

			// price course
			result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.coursePriceAtAbove(), 5);
			if (!result) {
				cdpVerifyMsgList.add("The price of course is not visible");
				return result;
			}

			// course description
			result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.courseDescription(), 5);
			if (!result) {
				cdpVerifyMsgList.add("The description of course is not visible");
				return result;
			}

			// select language
			result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.selectLang(), 5);
			if (!result) {
				cdpVerifyMsgList.add("The select language option is not visible");
				return result;
			}
			cfObj.commonClick(cdpVerify_OR.selectLang());
			cfObj.commonClick(cdpVerify_OR.continueBtnLang());
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "btn_buy_one", "id", 10);
			if (!result) {
				cdpVerifyMsgList.add("After changing the lang, the cdp not appearing");
				return result;
			}

			// share
			result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.shareCourse(), 5);
			if (!result) {
				cdpVerifyMsgList.add("The share btn is not visible");
				return result;
			}
			cfObj.commonClick(cdpVerify_OR.shareCourse());
			((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));

			// emi
			result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.emiText(), 5);
			if (!result) {
				cdpVerifyMsgList.add("EMI option is not available");
				return result;
			}

		} catch (Exception e) {
			cdpVerifyMsgList.add("courseInfoException");
			result = false;
		}
		return result;
	}

	public boolean verifyBuyBtnAbove(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.buyNowBtnAbove(), 5);
			if (!result) {
				cdpVerifyMsgList.add("The buy now button above is not visible");
				return result;
			}

			cfObj.commonClick(cdpVerify_OR.buyNowBtnAbove());

			result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.buyNowAllTxt().get(0), 10);
			if (!result) {
				cdpVerifyMsgList.add("After clicking buyNowBtn, choose package window not open");
				return result;
			}
			((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));

		} catch (Exception e) {
			cdpVerifyMsgList.add("verifyBuyBtnAboveException");
			result = false;
		}
		return result;
	}

	public boolean verifyCourseContent(AppiumDriver<MobileElement> driver) {
		boolean result = true;

		try {
			// course content view
			result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.viewCourseContent(), 5);
			if (!result) {
				cdpVerifyMsgList.add("The view btn of course content is not visible");
			}

			// view
			cfObj.commonClick(cdpVerify_OR.viewCourseContent());
			cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "txt_content", "id", 10);
			result = verifyViewCourseContent(driver);
			if (!result) {
				return result;
			}
		} catch (Exception e) {
			cdpVerifyMsgList.add("verifyCourseContentException");
			result = false;
		}
		return result;
	}

	public boolean verifyViewCourseContent(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			List<MobileElement> courseContents = cdpVerify_OR.namesOfCourseContentElements();

			for (int i = 0; i < courseContents.size(); i++) {

				cfObj.commonWaitForElementToBeVisible(driver, courseContents.get(i), 5);
				if (!result) {
					cdpVerifyMsgList.add("The elements of view page not visible");
				}
				cfObj.commonClick(cdpVerify_OR.openDrawerIcons().get(i));
				cfObj.commonClick(courseContents.get(i));
			}
			((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));

		} catch (Exception e) {
			cdpVerifyMsgList.add("verifyViewCourseContentException");
			result = false;
		}
		return result;
	}

	public boolean verifyCoursePrice(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			String coursePriceAbove = cdpVerify_OR.coursePriceAtAbove().getText();
			cfObj.scrollUtillTheElementFound(driver, "btn_buy_two");
			String coursePriceBelow = cdpVerify_OR.mainPriceAtBottom().getText();
			cfObj.scrollUpUtillTheElementFound(driver, "btn_buy_one");

			if (coursePriceAbove.equalsIgnoreCase(coursePriceBelow)) {
				result = true;
			} else {
				cdpVerifyMsgList.add("The price at bottom and above are not same");
				return false;
			}

		} catch (Exception e) {
			cdpVerifyMsgList.add("verifyCoursePriceException");
			result = false;
		}
		return result;
	}

	public boolean verifyExamsCovered(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		boolean loop = true;
		try {

			String visibleText = "Author";
			cfObj.scrollIntoText(driver, visibleText);

			while (loop) {
				List<MobileElement> examNames = cdpVerify_OR.examCoveredTxt();
				for (int i = 0; i < examNames.size();) {
					result = cfObj.commonWaitForElementToBeVisible(driver, examNames.get(i), 5);
					if (!result) {
						cdpVerifyMsgList.add("The exam covered elements is not visible");
						return result;
					}
					String text1 = examNames.get(i).getText();
					cfObj.swipeLeftOnElement(examNames.get(i), driver);
					for (int j = 1; j < examNames.size();) {
						String text2 = examNames.get(i).getText();
						if (text1.equalsIgnoreCase(text2)) {
							loop = false;
							break;
						}
						loop = true;
						break;

					}
					if (loop == false) {
						break;
					}
				}
			}
		} catch (Exception e) {
			cdpVerifyMsgList.add("verifyExamsCoveredException");
			result = false;
		}
		return result;
	}

	public boolean verifyAboutAuthor(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			String visibleText = "Author";
			cfObj.scrollIntoText(driver, visibleText);

			result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.authorImage(), 5);
			if (!result) {
				cdpVerifyMsgList.add("The author image is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.authorName(), 5);
			if (!result) {
				cdpVerifyMsgList.add("The author name is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.authorDesigination(), 5);
			if (!result) {
				cdpVerifyMsgList.add("The author desigination is not visible");
				return result;
			}

			List<MobileElement> bioElements = cdpVerify_OR.authorBios();
			int n = bioElements.size();
			for (int i = 0; i < n; i++) {
				result = cfObj.commonWaitForElementToBeVisible(driver, bioElements.get(i), 5);
				if (!result) {
					cdpVerifyMsgList.add("The bio element is not visible");
					return result;
				}
			}

		} catch (Exception e) {
			cdpVerifyMsgList.add("verifyAboutAuthorException");
			result = false;
		}
		return result;
	}

	public boolean verifyDemoVideos(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			String visibleText = "Get free with this course";
			cfObj.scrollIntoText(driver, visibleText);

			// select language
			List<MobileElement> langs = cdpVerify_OR.demoVideoLang();
			List<MobileElement> videos = cdpVerify_OR.demoVideos();
			for (int i = 0; i < langs.size(); i++) {
				cfObj.commonClick(langs.get(i));
				for (int j = 0; j < videos.size(); j++) {
					result = cfObj.commonWaitForElementToBeVisible(driver, videos.get(i), 5);
					if (!result) {
						cdpVerifyMsgList.add("The videos are not availabe and not present");
						return result;
					}
					cfObj.commonClick(videos.get(j));
				}
			}
		} catch (Exception e) {
			cdpVerifyMsgList.add("verifyDemoVideosException");
			result = false;
		}
		return result;
	}

	public boolean verifyFreeCourses(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			String visibleText = "Get free with this course";
			cfObj.scrollIntoText(driver, visibleText);

			List<MobileElement> freeCourses = cdpVerify_OR.freeCourses();
			for (int i = 0; i < freeCourses.size();i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, freeCourses.get(i), 5);
				if (!result) {
					cdpVerifyMsgList.add("The free course section is not visible");
					return result;
				}

				cfObj.commonClick(freeCourses.get(i));

				result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.titleOfPage(), 5);
				if (!result) {
					cdpVerifyMsgList.add("After clicking on free course btn, course page does not open");
				}

				((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));
			}

		} catch (Exception e) {
			cdpVerifyMsgList.add("verifyFreeCoursesException");
			result = false;
		}
		return result;
	}

	public boolean verifyTestimonials(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			String visibleText = "Our success stories";
			cfObj.scrollIntoText(driver, visibleText);

			result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.titleOfTestimonial(), 5);
			if (!result) {
				cdpVerifyMsgList.add("The title of testimonial is not visible");
				return result;
			}

			List<MobileElement> nameTestimonials = cdpVerify_OR.nameTestimonials();
			List<MobileElement> imgTestimonials = cdpVerify_OR.imageTestimonials();
			List<MobileElement> desTestimonials = cdpVerify_OR.desTestimonials();
			List<MobileElement> playBtnTestimonials = cdpVerify_OR.playTestimonials();

			for (int i = 0; i < imgTestimonials.size(); i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, nameTestimonials.get(i), 5);
				if (!result) {
					cdpVerifyMsgList.add("The name on testimonial is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, imgTestimonials.get(i), 5);
				if (!result) {
					cdpVerifyMsgList.add("The image on testimonial is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, desTestimonials.get(i), 5);
				if (!result) {
					cdpVerifyMsgList.add("The description of testimonial is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, playBtnTestimonials.get(i), 5);
				if (!result) {
					cdpVerifyMsgList.add("The play button on testimonial is not visible");
					return result;
				}

				cfObj.commonClick(playBtnTestimonials.get(i));

				((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));

			}

		} catch (Exception e) {
			cdpVerifyMsgList.add("verifyTestimonialsException");
			result = false;
		}
		return result;
	}

	public boolean verifyBuyBtnBelow(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.scrollByID("//android.widget.Button[@resource-id='com.studyiq.android.stag:id/btn_buy_two']", driver);

			result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.buyNowBtnBelow(), 5);
			if (!result) {
				cdpVerifyMsgList.add("The buy now button above is not visible");
				return result;
			}
			
			cfObj.commonClick(cdpVerify_OR.buyNowBtnBelow());

			result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.buyNowAllTxt().get(0), 10);
			if (!result) {
				cdpVerifyMsgList.add("After clicking buyNowBtn, choose package window not open");
				return result;
			}
			((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));

		} catch (Exception e) {
			cdpVerifyMsgList.add("verifyBuyBtnBelowException");
			result = false;
		}
		return result;
	}

}
