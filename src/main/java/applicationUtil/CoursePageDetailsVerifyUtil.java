package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import apiUtil.CourseApiUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.CoursePageDetailsVerify_OR;
import pojo.courseList.CourseList;
import pojo.courseView.CourseView;
import pojo.testdata.TestData;
import util.Common_Function;
import util.ConfigFileReader;

public class CoursePageDetailsVerifyUtil {
	CoursePageDetailsVerify_OR cdpVerify_OR;
	LoginUtil loginutillObj;
	HomePageUtil homePageUtilObj;
	CourseApiUtil courseApiUtilObj;
	CourseView courseViewObj;
	CourseList courseListObj;
	ConfigFileReader configFileReader;
	public ArrayList<String> cdpVerifyMsgList = new ArrayList<String>();

	public Common_Function cfObj = new Common_Function();

	public CoursePageDetailsVerifyUtil(AppiumDriver<MobileElement> driver) {

		cdpVerify_OR = new CoursePageDetailsVerify_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), cdpVerify_OR);

	}

	public boolean verifyCoursePage(AppiumDriver<MobileElement> driver, TestData testData) throws Exception {
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
				cdpVerifyMsgList.add("Error in getting course list from api");
				return false;
			}

			result = loginutillObj.doSignUp(driver);
			if (!result) {
				cdpVerifyMsgList.addAll(loginutillObj.loginMsgList);
				return result;
			}
			
			result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.getListBottomMenuMyHome().get(0), 10);
			if (!result) {
				cdpVerifyMsgList.add("The button of my home on bottom is not visible");
				return result;
			}

			cfObj.commonClick(cdpVerify_OR.getListBottomMenuMyHome().get(0));

			if (testData.getCourseType().contains("video")) {

				strCourseSlug = configFileReader.getVideoSlug();

				result = homePageUtilObj.clickOnCourseOnHomePage(driver);
				if (!result) {
					cdpVerifyMsgList.addAll(homePageUtilObj.homePageMsglist);
					return result;
				}
			} else if (testData.getCourseType().contains("books")) {

				strCourseSlug = configFileReader.getBooksSlug();

				result = homePageUtilObj.clickOnBookOnHomePage(driver);
				if (!result) {
					cdpVerifyMsgList.addAll(homePageUtilObj.homePageMsglist);
					return result;
				}
			} else if (testData.getCourseType().contains("live")) {

				strCourseSlug = configFileReader.getLiveSlug();

				result = homePageUtilObj.clickOnLiveCourseOnHomePage(driver);
				if (!result) {
					cdpVerifyMsgList.addAll(homePageUtilObj.homePageMsglist);
					return result;
				}
			} else if (testData.getCourseType().contains("test-series")) {

				strCourseSlug = configFileReader.getTestseriesSlug();

				result = homePageUtilObj.clickOnTestSeriesOnHomePage(driver);
				if (!result) {
					cdpVerifyMsgList.addAll(homePageUtilObj.homePageMsglist);
					return result;
				}
			}

			System.out.println(strCourseSlug);
			courseApiUtilObj = new CourseApiUtil();
			courseViewObj = new CourseView();
			courseViewObj = courseApiUtilObj.getCourseViewData(strCourseSlug);
			System.out.println(courseViewObj.getData().getPriceInfo());

			result = courseInfo(driver, courseViewObj, testData);
			if (!result) {
				return result;
			}

			result = verifyCoursePrice(driver);
			if (!result) {
				return result;
			}

			if (!testData.getCourseType().equalsIgnoreCase("live")) {

				result = verifyExamsCovered(driver, courseViewObj);
				if (!result) {
					return result;
				}
			}

			result = verifyAboutAuthor(driver, courseViewObj);
			if (!result) {
				return result;
			}

//			result = verifyDemoVideos(driver, courseViewObj);
//			if (!result) {
//				return result;
//			}

//			result = verifyCrossSell(driver, courseViewObj, testData);
//			if (!result) {
//				return result;
//			}

//			result = verifyCourseContent(driver);
//			if (!result) {
//				return result;
//			}

			result = verifyFreeCourses(driver, courseViewObj);
			if (!result) {
				return result;
			}

//			result = verifyTestimonials(driver);
//			if (!result) {
//				return result;
//			}

			result = verifyBuyBtnBelow(driver);
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			cdpVerifyMsgList.add("verifyCoursePage_Exception " + e.getMessage());
			result = false;
		}

		return result;
	}

	public boolean courseInfo(AppiumDriver<MobileElement> driver, CourseView courseViewObj, TestData testData) {
		boolean result = true;
		String strOriginalPrice;
		try {

			if (cdpVerify_OR.titleOfPage().size() == 0) {
				cdpVerifyMsgList.add("The title of page is not visible");
				result = false;
			}

			if (courseViewObj.getData().getCourseDetail().getCourseBasePrice() != null) {
				strOriginalPrice = courseViewObj.getData().getCourseDetail().getCourseBasePrice().toString();
				System.out.println(strOriginalPrice);
				if (cdpVerify_OR.coursePriceAtAbove().size() == 0) {
					cdpVerifyMsgList.add("Original price is not display on course detail page");
					result = false;
				}
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

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "oplus:id/oplus_chooser_action_nearby_tx", "id", 10);
			if (!result) {
				cdpVerifyMsgList.add("The share pop is not visible");
				return result;
			}
			
			((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));

			// emi
			if (courseViewObj.getData().getCourseDetail().getIsEmiAvailable() == 0) {
				System.out.println("EMI option is not available");
			}

			if (cdpVerify_OR.buyNowBtnAbove().size() != 1) {
				cdpVerifyMsgList.add("The buy now button above is not visible");
				result = false;
			} else {
				cfObj.commonClick(cdpVerify_OR.buyNowBtnAbove().get(0));

				result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.buyNowAllTxt().get(0), 10);
				if (!result) {
					cdpVerifyMsgList.add("After clicking buyNowBtn, choose package window not open");
					return result;
				}
				((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));
			}

			if (!testData.getCourseType().equalsIgnoreCase("books")) {
				if (!testData.getCourseType().equalsIgnoreCase("live")) {

					if (cdpVerify_OR.listOfCourseInfo().size() == 0) {
						System.out.println("Course info is not display in course detail page");
					}
				}
			}

		} catch (Exception e) {
			cdpVerifyMsgList.add("courseInfoException " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyCourseContent(AppiumDriver<MobileElement> driver) {
		boolean result = true;

		try {

			result = cfObj.scrollUtillTheElementFound(driver,
					"//android.widget.TextView[contains(@resource-id,'tv_str_show_content')]");

			result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.viewCourseContent(), 5);
			if (!result) {
				cdpVerifyMsgList.add("The view btn of course content is not visible");
			}

			cfObj.commonClick(cdpVerify_OR.viewCourseContent());
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "txt_content", "id", 10);
			if (!result) {
				cdpVerifyMsgList.add("The view course content text is not visible");
				return result;
			}

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

			String coursePriceAbove = cdpVerify_OR.coursePriceAtAbove().get(0).getText();

			cfObj.scrollUtillTheElementFound(driver, "btn_buy_two");

			String coursePriceBelow = cdpVerify_OR.mainPriceAtBottom().getText();

			if (coursePriceAbove.equalsIgnoreCase(coursePriceBelow)) {
				cfObj.scrollIntoText(driver, "Product Description");
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

	public boolean verifyExamsCovered(AppiumDriver<MobileElement> driver, CourseView courseViewObj) {
		boolean result = true;
		boolean loop = true;
		try {

			if (courseViewObj.getData().getExamCovered().size() != 0) {

				result = cfObj.scrollUtillTheElementFound(driver,
						"//android.view.View[contains(@resource-id,'view_exam_covered')]");

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
			}
		} catch (Exception e) {
			cdpVerifyMsgList.add("verifyExamsCoveredException");
			result = false;
		}
		return result;
	}

	public boolean verifyAboutAuthor(AppiumDriver<MobileElement> driver, CourseView courseViewObj) {
		boolean result = true;
		try {

			if (courseViewObj.getData().getAuthors().size() != 0) {

				result = cfObj.scrollUtillTheElementFound(driver,
						"//android.widget.TextView[contains(@resource-id,'txt_author_bio')]");

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

				if (!courseViewObj.getData().getAuthors().get(0).getAuthorName()
						.equalsIgnoreCase(cdpVerify_OR.authorName().getText())) {

					cdpVerifyMsgList.add("The author name is not same");
					result = false;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.authorDesigination(), 5);
				if (!result) {
					cdpVerifyMsgList.add("The author desigination is not visible");
					return result;
				}

				if (!courseViewObj.getData().getAuthors().get(0).getAuthorDesignation()
						.equalsIgnoreCase(cdpVerify_OR.authorDesigination().getText())) {

					cdpVerifyMsgList.add("The author designation is not same");
					result = false;
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
			}

		} catch (Exception e) {
			cdpVerifyMsgList.add("verifyAboutAuthorException");
			result = false;
		}
		return result;
	}

	public boolean verifyDemoVideos(AppiumDriver<MobileElement> driver, CourseView courseViewObj) {
		boolean result = true;
		int intStartTime, intPauseTime, intFinalTime;
		try {

			if (courseViewObj.getData().getDemoUrls().size() > 0) {

				// scroll method

				List<MobileElement> langs = cdpVerify_OR.demoVideoLang();
				List<MobileElement> videos = cdpVerify_OR.demoVideos();

				for (int i = 0; i < langs.size(); i++) {
					result = cfObj.commonWaitForElementToBeVisible(driver, langs.get(i), 5);
					if (!result) {
						cdpVerifyMsgList.add("The language is not visible");
						return result;
					}
					cfObj.commonClick(langs.get(i));

					for (int j = 0; j < videos.size(); j++) {
						result = cfObj.commonWaitForElementToBeVisible(driver, videos.get(i), 5);
						if (!result) {
							cdpVerifyMsgList.add("The videos are not availabe and not present");
							return result;
						}
						cfObj.commonClick(videos.get(j));

						cfObj.commonClick(cdpVerify_OR.frameDemoVideo());

						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "exo_position", "id", 10);
						if (!result) {
							cdpVerifyMsgList.add("Demo video not opened when click on videos from the list");
							return result;
						}

						// get video time
						intStartTime = Integer.valueOf(
								cfObj.commonGetElement(driver, "exo_position", "id").getText().toString().split("/")[0]
										.trim().split(":")[1].trim());
						System.out.println(intStartTime);

						if (intStartTime > 0) {
							cdpVerifyMsgList.add("Start time should be zero in starting of the video for course: "
									+ courseViewObj.getData().getCourseDetail().getCourseTitle());
							return false;
						}
						Thread.sleep(5000);

						// Click on play button

						cfObj.commonClick(cfObj.commonGetElement(driver, "exo_pause", "css"));

						// Click on pause button

						intPauseTime = Integer.valueOf(
								cfObj.commonGetElement(driver, "exo_position", "id").getText().toString().split("/")[0]
										.trim().split(":")[2].trim());
						System.out.println(intPauseTime);
						if (intPauseTime < 6) {
							cdpVerifyMsgList.add("Video is not playing when click on start button for course: "
									+ courseViewObj.getData().getCourseDetail().getCourseTitle());
							return false;
						}

						Thread.sleep(5000);

						intFinalTime = Integer.valueOf(
								cfObj.commonGetElement(driver, "exo_position", "id").getText().toString().split("/")[0]
										.trim().split(":")[2].trim());
						System.out.println(intFinalTime);

						if (intPauseTime != intFinalTime) {
							cdpVerifyMsgList.add("Video is not paused when click on paused button for course: "
									+ courseViewObj.getData().getCourseDetail().getCourseTitle());
							return false;
						}
					}
				}
			}
		} catch (Exception e) {
			cdpVerifyMsgList.add("verifyDemoVideosException");
			result = false;
		}
		return result;
	}

	public boolean verifyFreeCourses(AppiumDriver<MobileElement> driver, CourseView courseViewObj) {
		boolean result = true;
		try {

			if (courseViewObj.getData().getBundlecourses().size() != 0) {

				result = cfObj.scrollUtillTheElementFound(driver,
						"//android.widget.ImageView[contains(@resource-id,'img_bundle_course')]");

				List<MobileElement> freeCourses = cdpVerify_OR.freeCourses();
				for (int i = 0; i < freeCourses.size(); i++) {

					result = cfObj.commonWaitForElementToBeVisible(driver, freeCourses.get(i), 5);
					if (!result) {
						cdpVerifyMsgList.add("The free course section is not visible");
						return result;
					}

					cfObj.commonClick(freeCourses.get(i));

					result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.titleOfPage().get(0), 5);
					if (!result) {
						cdpVerifyMsgList.add("After clicking on free course btn, course page does not open");
					}

					((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));
				}
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

			// scroll method

			result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.titleOfTestimonial(), 5);
			if (!result) {
				System.out.println("The testimonial is not present for this course");
				return true;
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

			result = cfObj.scrollUtillTheElementFound(driver,
					"//android.widget.Button[contains(@resource-id,'btn_buy_two')]");

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

	public boolean verifyCrossSell(AppiumDriver<MobileElement> driver, CourseView courseViewObj, TestData testData) {
		boolean result = true;
		String strCrossSellSlug = null;
		CourseApiUtil courseApiUtilObj;
		try {
			if (courseViewObj.getData().getCrossSellDetails().size() != 0) {

				if (testData.getCourseType().contains("video")) {

					strCrossSellSlug = configFileReader.getVideoCrossSellSlug();

				} else if (testData.getCourseType().contains("books")) {

					strCrossSellSlug = configFileReader.getBooksCrossSellSlug();

				} else if (testData.getCourseType().contains("live")) {

					strCrossSellSlug = configFileReader.getLiveCrossSellSlug();

				} else if (testData.getCourseType().contains("test-series")) {

					strCrossSellSlug = configFileReader.getTestseriesCrossSellSlug();
				}

				courseApiUtilObj = new CourseApiUtil();
				courseViewObj = new CourseView();
				courseViewObj = courseApiUtilObj.getCourseViewData(strCrossSellSlug);

				result = cfObj.scrollUtillTheElementFound(driver,
						"//android.widget.ImageView[contains(@resource-id,'course_image_card')]");

				result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.similarCoursesText(), 5);
				if (!result) {
					cdpVerifyMsgList.add("The similar courses text is not visible");
					return result;
				}

				List<MobileElement> ImageList = cdpVerify_OR.listOfCrossSellImage();
				List<MobileElement> TitleList = cdpVerify_OR.listOfCrossSellTitle();
				List<MobileElement> PriceList = cdpVerify_OR.listOfCrossSellPrice();

				int imageSize = ImageList.size();
				int titleSize = TitleList.size();
				int priceSize = PriceList.size();

				if (imageSize > 3 && titleSize > 3 && priceSize > 3) {

					for (int i = 0; i < imageSize; i++) {

						result = cfObj.commonWaitForElementToBeVisible(driver, ImageList.get(i), 5);
						if (!result) {
							cdpVerifyMsgList.add("The image is not visible");
							return result;
						}

						result = cfObj.commonWaitForElementToBeVisible(driver, TitleList.get(i), 5);
						if (!result) {
							cdpVerifyMsgList.add("The title is not visible");
							return result;
						}

						result = cfObj.commonWaitForElementToBeVisible(driver, PriceList.get(i), 5);
						if (!result) {
							cdpVerifyMsgList.add("The price is not visible");
							return result;
						}

						result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.crossViewAllBtn(), 5);
						if (!result) {
							cdpVerifyMsgList.add("The view all btn is not visible");
							return result;
						}
					}

					cfObj.commonClick(TitleList.get(0));

					result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.titleOfPage().get(0), 5);
					if (!result) {
						cdpVerifyMsgList.add("The cdp is not open");
						return result;
					}
					((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));

					result = cfObj.commonWaitForElementToBeVisible(driver, cdpVerify_OR.similarCoursesText(), 5);
					if (!result) {
						cdpVerifyMsgList
								.add("The similar courses text is not visible after coming back from first course");
						return result;
					}

					cfObj.commonClick(cdpVerify_OR.crossViewAllBtn());

					String courseTitle = TitleList.get(0).getText();

					String courseTitleAfterClick = courseViewObj.getData().getCourseDetail().getCourseTitle();

					if (courseTitle.equalsIgnoreCase(courseTitleAfterClick)) {
						((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));
					} else {
						cdpVerifyMsgList.add("The course is not same");
						return false;
					}

				} else {
					cdpVerifyMsgList.add("The size of the courses should not be more than 3");
					return false;
				}

			}

		} catch (Exception e) {
			cdpVerifyMsgList.add("verifyCrossSellException " + e.getMessage());
			result = false;
		}
		return result;
	}

}
