package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.LibraryPage_OR;
import pojo.myLibrary.MyLibrary;
import pojo.testdata.TestData;
import util.Common_Function;
import util.ConfigFileReader;
import apiUtil.LibraryApiUtil;
import apiUtil.LoginApiUtil;
import pojo.login.Login;

public class LibraryPageUtil {

	LibraryPage_OR libraryPage_OR;
	LoginUtil loginUtillObj;
	LoginApiUtil loginApiUtilObj;
	HomePageUtil homePageUtilObj;
	PaymentPageUtil paymentPageUtilObj;
	CourseDetailPage courseDetailPage;

	public ArrayList<String> libraryPageMsgList = new ArrayList<String>();

	public Common_Function cfObj = new Common_Function();

	public LibraryPageUtil(AppiumDriver<MobileElement> driver) {
		libraryPage_OR = new LibraryPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), libraryPage_OR);
	}

	public boolean verifyLibraryContent(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		homePageUtilObj = new HomePageUtil(driver);
		LoginApiUtil loginApiUtilObj;
		Login loginObj;
		LibraryApiUtil libraryApiObj;
		MyLibrary myLibrayApiObj;
		try {
			loginUtillObj = new LoginUtil(driver);
			result = loginUtillObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
			if (!result) {
				libraryPageMsgList.addAll(loginUtillObj.loginMsgList);
				return result;
			}

			loginApiUtilObj = new LoginApiUtil();

			loginObj = loginApiUtilObj.doLoginWeb(ConfigFileReader.strUserMobileNumber);
			if (loginObj == null) {
				libraryPageMsgList.add("Fail to Login via api");
				return result;
			}

			libraryApiObj = new LibraryApiUtil();

			myLibrayApiObj = libraryApiObj.getLibraryData(loginObj.getData().getApiToken(),
					loginObj.getData().getUserId());
			if (myLibrayApiObj == null) {
				libraryPageMsgList.add("Error in my library api");
				return result;
			}

			result = verifyMyLibraryTitle(driver);
			if (!result) {
				return result;
			}

			result = verifyCourseContent(driver, myLibrayApiObj);
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("Exception in verifyLibraryContent " + e.getMessage());
		}
		return result;
	}

	public boolean verifyMyLibraryTitle(AppiumDriver<MobileElement> driver) {
		boolean result = true;

		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.getListBottomMenuMyLibrary().get(0),
					10);
			if (!result) {
				libraryPageMsgList.add("The text of my library on bottom is not visible");
				return result;
			}

			cfObj.commonClick(libraryPage_OR.getListBottomMenuMyLibrary().get(0));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "your_purchases", "id", 5);
			if (!result) {
				libraryPageMsgList.add("It is not library page");
				return result;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyMyLibraryTitleException " + e.getMessage());
		}
		return result;
	}

	public boolean verifyCourseContent(AppiumDriver<MobileElement> driver, MyLibrary mylibraryObj) {
		boolean result = true;
		int size;
		try {
			size = mylibraryObj.getData().size();

			for (int i = 0; i < size; i++) {
				if (mylibraryObj.getData().get(i).getCourseTypeName().equals("Video")) {

					result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.videoCoursesBtn(), 5);
					if (!result) {
						libraryPageMsgList.add("The video courses menu button not visible");
						return result;
					}

					cfObj.commonClick(libraryPage_OR.videoCoursesBtn());

					List<MobileElement> courseStatusList = libraryPage_OR.listOfCourseValidOrRenewText();

					for (int j = 0; j < courseStatusList.size(); j++) {

						String courseNameFromApi = mylibraryObj.getData().get(i).getCourseData().get(j)
								.getCourseTitle();

						if (!courseNameFromApi
								.equalsIgnoreCase(libraryPage_OR.listOfCourseTitlesInLib().get(j).getText())) {
							libraryPageMsgList.add("The course name in library is not same as in api");
							result = false;
						}

						String statusText = courseStatusList.get(j).getText();
						if (statusText.equalsIgnoreCase("RENEW NOW")) {

							cfObj.commonClick(libraryPage_OR.listOfRenewNowBtn().get(0));

							result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.noOfOffersAvail(),
									10);
							if (!result) {
								libraryPageMsgList.add("It is not package and offer page");
								return result;
							}

							driver.navigate().back();

							result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.videoCoursesBtn(), 5);
							if (!result) {
								libraryPageMsgList.add(
										"The video courses menu button not visible after course is opened and came back");
								return result;
							}

						} else {
							cfObj.commonClick(libraryPage_OR.listOfCourseTitlesInLib().get(j));

							result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.myCourseBtn(), 5);
							if (!result) {
								libraryPageMsgList.add("Not inside the course clicked in library");
								return result;
							}

							if (!courseNameFromApi.equalsIgnoreCase(libraryPage_OR.headingList().get(0).getText())) {
								libraryPageMsgList.add("The course name in library is not same as in api");
								result = false;
							}

							driver.navigate().back();

							result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.videoCoursesBtn(), 5);
							if (!result) {
								libraryPageMsgList.add(
										"The video courses menu button not visible after course is opened and came back");
								return result;
							}
						}
					}

				} else if (mylibraryObj.getData().get(i).getCourseTypeName().equals("Live Classes")) {

					result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.liveClassesBtn(), 5);
					if (!result) {
						libraryPageMsgList.add("The live classes menu button not visible");
						return result;
					}

					cfObj.commonClick(libraryPage_OR.liveClassesBtn());

					List<MobileElement> courseStatusList = libraryPage_OR.listOfCourseValidOrRenewText();

					for (int j = 0; j < courseStatusList.size(); j++) {

						String courseNameFromApi = mylibraryObj.getData().get(i).getCourseData().get(j)
								.getCourseTitle();

						if (!courseNameFromApi
								.equalsIgnoreCase(libraryPage_OR.listOfCourseTitlesInLib().get(j).getText())) {
							libraryPageMsgList.add("The course name in library is not same as in api");
							result = false;
						}

						String statusText = courseStatusList.get(j).getText();
						if (statusText.equalsIgnoreCase("RENEW NOW")) {

							cfObj.commonClick(libraryPage_OR.listOfRenewNowBtn().get(0));

							result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.noOfOffersAvail(),
									10);
							if (!result) {
								libraryPageMsgList.add("It is not package and offer page");
								return result;
							}

							driver.navigate().back();

							result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.liveClassesBtn(), 5);
							if (!result) {
								libraryPageMsgList.add(
										"The video courses menu button not visible after course is opened and came back");
								return result;
							}

						} else {
							cfObj.commonClick(libraryPage_OR.listOfCourseTitlesInLib().get(j));

							result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.myCourseBtn(), 5);
							if (!result) {
								libraryPageMsgList.add("Not inside the course clicked in library");
								return result;
							}

							if (!courseNameFromApi.equalsIgnoreCase(libraryPage_OR.headingList().get(0).getText())) {
								libraryPageMsgList.add("The course name in library is not same as in api");
								result = false;
							}

							driver.navigate().back();

							result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.liveClassesBtn(), 5);
							if (!result) {
								libraryPageMsgList.add(
										"The video courses menu button not visible after course is opened and came back");
								return result;
							}
						}
					}
				} else if (mylibraryObj.getData().get(i).getCourseTypeName().equals("Testseries")) {

					result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.testSeriesBtn(), 5);
					if (!result) {
						libraryPageMsgList.add("The test series menu button not visible");
						return result;
					}

					cfObj.commonClick(libraryPage_OR.testSeriesBtn());

					List<MobileElement> courseStatusList = libraryPage_OR.listOfCourseValidOrRenewText();

					for (int j = 0; j < courseStatusList.size(); j++) {

						String courseNameFromApi = mylibraryObj.getData().get(i).getCourseData().get(j)
								.getCourseTitle();

						if (!courseNameFromApi
								.equalsIgnoreCase(libraryPage_OR.listOfCourseTitlesInLib().get(j).getText())) {
							libraryPageMsgList.add("The course name in library is not same as in api");
							result = false;
						}

						String statusText = courseStatusList.get(j).getText();
						if (statusText.equalsIgnoreCase("RENEW NOW")) {

							cfObj.commonClick(libraryPage_OR.listOfRenewNowBtn().get(0));

							result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.noOfOffersAvail(),
									10);
							if (!result) {
								libraryPageMsgList.add("It is not package and offer page");
								return result;
							}

							driver.navigate().back();

							result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.testSeriesBtn(), 5);
							if (!result) {
								libraryPageMsgList.add(
										"The video courses menu button not visible after course is opened and came back");
								return result;
							}

						} else {
							cfObj.commonClick(libraryPage_OR.listOfCourseTitlesInLib().get(j));

							Thread.sleep(2000);

							if (!courseNameFromApi.equalsIgnoreCase(libraryPage_OR.headingList().get(0).getText())) {
								libraryPageMsgList.add("The course name in library is not same as in api");
								result = false;
							}

							driver.navigate().back();

							result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.testSeriesBtn(), 5);
							if (!result) {
								libraryPageMsgList.add(
										"The video courses menu button not visible after course is opened and came back");
								return result;
							}
						}
					}

				} else {
					libraryPageMsgList.add("The course type is wrong");
					return false;
				}
			}

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean verifyMyLibrary(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		homePageUtilObj = new HomePageUtil(driver);
		LoginApiUtil loginApiUtilObj;
		Login loginObj;
		LibraryApiUtil libraryApiObj;
		MyLibrary myLibrayApiObj;

		try {
			loginUtillObj = new LoginUtil(driver);
			result = loginUtillObj.verifyLogin(driver, ConfigFileReader.strUserMobileNumber);
			if (!result) {
				libraryPageMsgList.addAll(loginUtillObj.loginMsgList);
				return result;
			}

			loginApiUtilObj = new LoginApiUtil();

			loginObj = loginApiUtilObj.doLoginWeb(ConfigFileReader.strUserMobileNumber);
			if (loginObj == null) {
				libraryPageMsgList.add("Fail to Login via api");
				return result;
			}

			libraryApiObj = new LibraryApiUtil();

			myLibrayApiObj = libraryApiObj.getLibraryData(loginObj.getData().getApiToken(),
					loginObj.getData().getUserId());
			if (myLibrayApiObj == null) {
				libraryPageMsgList.add("Error in my library api");
				return result;
			}

			result = verifyMyLibraryTitle(driver);
			if (!result) {
				return result;
			}

			result = verifyValidCourse(driver, myLibrayApiObj);
			if (!result) {
				return result;
			}

			result = verifyExpiredCourse(driver, myLibrayApiObj);
			if (!result) {
				return result;
			}

//			result = verifyExpiringSoonCourse(driver, myLibrayApiObj);
//			if (!result) {
//				return result;
//			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("Exception in verifyMyLibrary " + e.getMessage());
		}
		return result;
	}

	public boolean verifyValidCourse(AppiumDriver<MobileElement> driver, MyLibrary mylibraryObj) {
		boolean result = true;
		int size;
		try {
			size = mylibraryObj.getData().size();

			for (int i = 0; i < size; i++) {

				if (mylibraryObj.getData().get(i).getCourseTypeName().equals("Video")) {

					result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.videoCoursesBtn(), 5);
					if (!result) {
						libraryPageMsgList.add("The video courses menu button not visible");
						return result;
					}

					cfObj.commonClick(libraryPage_OR.videoCoursesBtn());

					List<MobileElement> courseStatusList = libraryPage_OR.listOfCourseValidOrRenewText();

					for (int j = 0; j < courseStatusList.size(); j++) {

						String courseNameFromApi = mylibraryObj.getData().get(i).getCourseData().get(j)
								.getCourseTitle();

						if (!courseNameFromApi
								.equalsIgnoreCase(libraryPage_OR.listOfCourseTitlesInLib().get(j).getText())) {
							libraryPageMsgList.add("The course name in library is not same as in api");
							result = false;
						}

						String statusText = courseStatusList.get(j).getText();

						if (statusText.contains("Valid Upto")) {

							if (!(mylibraryObj.getData().get(i).getCourseData().get(j).getValidityDaysLeft() > 15)) {
								libraryPageMsgList.add("The validity days from api is less than 15");
								result = false;
							}
						}
					}

				} else if (mylibraryObj.getData().get(i).getCourseTypeName().equals("Live Classes")) {

					result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.liveClassesBtn(), 5);
					if (!result) {
						libraryPageMsgList.add("The live classes menu button not visible");
						return result;
					}

					cfObj.commonClick(libraryPage_OR.liveClassesBtn());

					List<MobileElement> courseStatusList = libraryPage_OR.listOfCourseValidOrRenewText();

					for (int j = 0; j < courseStatusList.size(); j++) {

						String courseNameFromApi = mylibraryObj.getData().get(i).getCourseData().get(j)
								.getCourseTitle();

						if (!courseNameFromApi
								.equalsIgnoreCase(libraryPage_OR.listOfCourseTitlesInLib().get(j).getText())) {
							libraryPageMsgList.add("The course name in library is not same as in api");
							result = false;
						}

						String statusText = courseStatusList.get(j).getText();
						if (statusText.contains("Valid Upto")) {

							if (!(mylibraryObj.getData().get(i).getCourseData().get(j).getValidityDaysLeft() > 15)) {
								libraryPageMsgList.add("The validity days from api is less than 15");
								result = false;
							}
						}
					}
				} else if (mylibraryObj.getData().get(i).getCourseTypeName().equals("Testseries")) {

					result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.testSeriesBtn(), 5);
					if (!result) {
						libraryPageMsgList.add("The test series menu button not visible");
						return result;
					}

					cfObj.commonClick(libraryPage_OR.testSeriesBtn());

					List<MobileElement> courseStatusList = libraryPage_OR.listOfCourseValidOrRenewText();

					for (int j = 0; j < courseStatusList.size(); j++) {

						String courseNameFromApi = mylibraryObj.getData().get(i).getCourseData().get(j)
								.getCourseTitle();

						if (!courseNameFromApi
								.equalsIgnoreCase(libraryPage_OR.listOfCourseTitlesInLib().get(j).getText())) {
							libraryPageMsgList.add("The course name in library is not same as in api");
							result = false;
						}

						String statusText = courseStatusList.get(j).getText();
						if (statusText.contains("Valid Upto")) {

							if (!(mylibraryObj.getData().get(i).getCourseData().get(j).getValidityDaysLeft() > 15)) {
								libraryPageMsgList.add("The validity days from api is less than 15");
								result = false;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("Exception in verifyValidCourse " + e.getMessage());
		}
		return result;
	}

	public boolean verifyExpiredCourse(AppiumDriver<MobileElement> driver, MyLibrary mylibraryObj) {
		boolean result = true;
		int size;
		try {
			size = mylibraryObj.getData().size();

			for (int i = 0; i < size; i++) {

				if (mylibraryObj.getData().get(i).getCourseTypeName().equals("Video")) {

					result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.videoCoursesBtn(), 5);
					if (!result) {
						libraryPageMsgList.add("The video courses menu button not visible");
						return result;
					}

					cfObj.commonClick(libraryPage_OR.videoCoursesBtn());

					List<MobileElement> courseStatusList = libraryPage_OR.listOfCourseValidOrRenewText();

					for (int j = 0; j < courseStatusList.size(); j++) {

						String courseNameFromApi = mylibraryObj.getData().get(i).getCourseData().get(j)
								.getCourseTitle();

						if (!courseNameFromApi
								.equalsIgnoreCase(libraryPage_OR.listOfCourseTitlesInLib().get(j).getText())) {
							libraryPageMsgList.add("The course name in library is not same as in api");
							result = false;
						}

						String statusText = courseStatusList.get(j).getText();

						if (statusText.equalsIgnoreCase("RENEW NOW")) {

							result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
									"txt_course_validity_status", "id", 5);
							if (!result) {

								if (!(mylibraryObj.getData().get(i).getCourseData().get(j).getValidityDaysLeft() < 0)) {
									libraryPageMsgList.add("The validity days from api is more than 0");
									result = false;
								}
							}
						}
					}

				} else if (mylibraryObj.getData().get(i).getCourseTypeName().equals("Live Classes")) {

					result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.liveClassesBtn(), 5);
					if (!result) {
						libraryPageMsgList.add("The live classes menu button not visible");
						return result;
					}

					cfObj.commonClick(libraryPage_OR.liveClassesBtn());

					List<MobileElement> courseStatusList = libraryPage_OR.listOfCourseValidOrRenewText();

					for (int j = 0; j < courseStatusList.size(); j++) {

						String courseNameFromApi = mylibraryObj.getData().get(i).getCourseData().get(j)
								.getCourseTitle();

						if (!courseNameFromApi
								.equalsIgnoreCase(libraryPage_OR.listOfCourseTitlesInLib().get(j).getText())) {
							libraryPageMsgList.add("The course name in library is not same as in api");
							result = false;
						}

						String statusText = courseStatusList.get(j).getText();
						if (statusText.equalsIgnoreCase("RENEW NOW")) {

							result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
									"txt_course_validity_status", "id", 5);
							if (!result) {

								if (!(mylibraryObj.getData().get(i).getCourseData().get(j).getValidityDaysLeft() < 0)) {
									libraryPageMsgList.add("The validity days from api is more than 0");
									result = false;
								}
							}
						}
					}
				} else if (mylibraryObj.getData().get(i).getCourseTypeName().equals("Testseries")) {

					result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.testSeriesBtn(), 5);
					if (!result) {
						libraryPageMsgList.add("The test series menu button not visible");
						return result;
					}

					cfObj.commonClick(libraryPage_OR.testSeriesBtn());

					List<MobileElement> courseStatusList = libraryPage_OR.listOfCourseValidOrRenewText();

					for (int j = 0; j < courseStatusList.size(); j++) {

						String courseNameFromApi = mylibraryObj.getData().get(i).getCourseData().get(j)
								.getCourseTitle();

						if (!courseNameFromApi
								.equalsIgnoreCase(libraryPage_OR.listOfCourseTitlesInLib().get(j).getText())) {
							libraryPageMsgList.add("The course name in library is not same as in api");
							result = false;
						}

						String statusText = courseStatusList.get(j).getText();
						if (statusText.equalsIgnoreCase("RENEW NOW")) {

							result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
									"txt_course_validity_status", "id", 5);

							if (!result) {

								if (!(mylibraryObj.getData().get(i).getCourseData().get(j).getValidityDaysLeft() < 0)) {
									libraryPageMsgList.add("The validity days from api is more than 0");
									result = false;
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("Exception in verifyValidCourse " + e.getMessage());
		}
		return result;
	}

	public boolean verifyExpiringSoonCourse(AppiumDriver<MobileElement> driver, MyLibrary mylibraryObj) {
		boolean result = true;
		int size;
		try {
			size = mylibraryObj.getData().size();

			for (int i = 0; i < size; i++) {

				if (mylibraryObj.getData().get(i).getCourseTypeName().equals("Video")) {

					result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.videoCoursesBtn(), 5);
					if (!result) {
						libraryPageMsgList.add("The video courses menu button not visible");
						return result;
					}

					cfObj.commonClick(libraryPage_OR.videoCoursesBtn());

					List<MobileElement> courseStatusList = libraryPage_OR.listOfCourseValidOrRenewText();

					for (int j = 0; j < courseStatusList.size(); j++) {

						String courseNameFromApi = mylibraryObj.getData().get(i).getCourseData().get(j)
								.getCourseTitle();

						if (!courseNameFromApi
								.equalsIgnoreCase(libraryPage_OR.listOfCourseTitlesInLib().get(j).getText())) {
							libraryPageMsgList.add("The course name in library is not same as in api");
							result = false;
						}

						String statusText = courseStatusList.get(j).getText();

						if (statusText.equalsIgnoreCase("RENEW NOW")) {

							result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
									"txt_course_validity_status", "id", 5);
							if (result) {
								
								//problem that if we have two renew button, how to distinguish
								if (!(mylibraryObj.getData().get(i).getCourseData().get(j).getValidityDaysLeft() < 15)
										|| !(mylibraryObj.getData().get(i).getCourseData().get(j)
												.getValidityDaysLeft() > 0)) {
									libraryPageMsgList.add("The validity days from api is more than 15 or less than 0");
									result = false;
								}
							}
						}
					}

				} else if (mylibraryObj.getData().get(i).getCourseTypeName().equals("Live Classes")) {

					result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.liveClassesBtn(), 5);
					if (!result) {
						libraryPageMsgList.add("The live classes menu button not visible");
						return result;
					}

					cfObj.commonClick(libraryPage_OR.liveClassesBtn());

					List<MobileElement> courseStatusList = libraryPage_OR.listOfCourseValidOrRenewText();

					for (int j = 0; j < courseStatusList.size(); j++) {

						String courseNameFromApi = mylibraryObj.getData().get(i).getCourseData().get(j)
								.getCourseTitle();

						if (!courseNameFromApi
								.equalsIgnoreCase(libraryPage_OR.listOfCourseTitlesInLib().get(j).getText())) {
							libraryPageMsgList.add("The course name in library is not same as in api");
							result = false;
						}

						String statusText = courseStatusList.get(j).getText();
						if (statusText.equalsIgnoreCase("RENEW NOW")) {

							result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
									"txt_course_validity_status", "id", 5);
							if (result) {

								if (!(mylibraryObj.getData().get(i).getCourseData().get(j).getValidityDaysLeft() < 15)
										|| !(mylibraryObj.getData().get(i).getCourseData().get(j)
												.getValidityDaysLeft() > 0)) {
									libraryPageMsgList.add("The validity days from api is more than 15 or less than 0");
									result = false;
								}
							}
						}
					}
				} else if (mylibraryObj.getData().get(i).getCourseTypeName().equals("Testseries")) {

					result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.testSeriesBtn(), 5);
					if (!result) {
						libraryPageMsgList.add("The test series menu button not visible");
						return result;
					}

					cfObj.commonClick(libraryPage_OR.testSeriesBtn());

					List<MobileElement> courseStatusList = libraryPage_OR.listOfCourseValidOrRenewText();

					for (int j = 0; j < courseStatusList.size(); j++) {

						String courseNameFromApi = mylibraryObj.getData().get(i).getCourseData().get(j)
								.getCourseTitle();

						if (!courseNameFromApi
								.equalsIgnoreCase(libraryPage_OR.listOfCourseTitlesInLib().get(j).getText())) {
							libraryPageMsgList.add("The course name in library is not same as in api");
							result = false;
						}

						String statusText = courseStatusList.get(j).getText();
						if (statusText.equalsIgnoreCase("RENEW NOW")) {

							result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
									"txt_course_validity_status", "id", 5);
							if (result) {

								if (!(mylibraryObj.getData().get(i).getCourseData().get(j).getValidityDaysLeft() < 15)
										|| !(mylibraryObj.getData().get(i).getCourseData().get(j)
												.getValidityDaysLeft() > 0)) {
									libraryPageMsgList.add("The validity days from api is more than 15 or less than 0");
									result = false;
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("Exception in verifyValidCourse " + e.getMessage());
		}
		return result;
	}

	/*
	 * Above code is new Implementation
	 */

	public boolean verifyLibraryPage(AppiumDriver<MobileElement> driver, TestData testData) {
		boolean result = true;
		homePageUtilObj = new HomePageUtil(driver);
		LoginApiUtil loginApiUtilObj;
		Login loginObj;
		LibraryApiUtil libraryApiObj;
		MyLibrary myLibrayApiObj;
		try {

			loginUtillObj = new LoginUtil(driver);
			result = loginUtillObj.verifyLogin(driver, "9878252339");
			if (!result) {
				libraryPageMsgList.addAll(loginUtillObj.loginMsgList);
				return result;
			}

			loginApiUtilObj = new LoginApiUtil();

			loginObj = loginApiUtilObj.doLoginWeb(ConfigFileReader.strUserMobileNumber);
			if (loginObj == null) {
				libraryPageMsgList.add("Fail to Login via api");
				return result;
			}

			libraryApiObj = new LibraryApiUtil();

			myLibrayApiObj = libraryApiObj.getLibraryData(loginObj.getData().getApiToken(),
					loginObj.getData().getUserId());
			if (myLibrayApiObj == null) {
				libraryPageMsgList.add("Error in my library api");
				return result;
			}

			result = verifyMyLibraryTitle(driver);
			if (!result) {
				return result;
			}

			result = verifyExpireOrNot(driver, testData);
			if (!result) {
				return result;
			}

			if (testData.getIsExpire() == false && testData.getIsFree() == false) {

				result = openCoursesInLibrary(driver, testData);
				if (!result) {
					return result;
				}
			} else if (testData.getIsFree() == true) {
				System.out.println("The course was free and has been removed successfully");
			} else if (testData.getIsExpire() == true && testData.getIsKey() == "fail") {
				System.out.println("The course was expired but the payment has been aborted");
			} else {
				System.out.println("The course was expired and now renewed");
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("Exception in verifyLibraryPage " + e.getMessage());
		}

		return result;
	}

	public boolean verifyExpireOrNot(AppiumDriver<MobileElement> driver, TestData testData) {
		boolean result = true;
		courseDetailPage = new CourseDetailPage(driver);
		String courseName;
		try {
			if (testData.getCourseType().equalsIgnoreCase("video")) {

				cfObj.commonClick(libraryPage_OR.videoCoursesBtn());

			} else if (testData.getCourseType().equalsIgnoreCase("test-series")) {

				cfObj.commonClick(libraryPage_OR.testSeriesBtn());

			} else if (testData.getCourseType().equalsIgnoreCase("live")) {

				cfObj.commonClick(libraryPage_OR.liveClassesBtn());

			} else {
				cfObj.commonClick(libraryPage_OR.videoCoursesBtn());
			}

			if (testData.getIsExpire() == true && testData.getIsFree() == false) {

				// scroll method till renew now

				List<MobileElement> courseStatusList = libraryPage_OR.listOfCourseValidOrRenewText();
				for (int i = 0; i < courseStatusList.size(); i++) {

					String statusText = courseStatusList.get(i).getText();
					if (statusText.equalsIgnoreCase("RENEW NOW")) {

						result = cfObj.commonWaitForElementToBeVisible(driver,
								libraryPage_OR.listOfCourseTitlesInLib().get(i), 5);
						if (!result) {
							libraryPageMsgList.add("The course title text is not visible");
							return result;
						}

						courseName = libraryPage_OR.listOfCourseTitlesInLib().get(i).getText();

						result = cfObj.commonWaitForElementToBeVisible(driver,
								libraryPage_OR.listOfRenewNowBtn().get(0), 5);
						if (!result) {
							libraryPageMsgList.add("The renew text and button is not visible");
							return result;
						}

						result = libraryPage_OR.listOfCourseImagesInLib().get(i).isDisplayed();
						if (!result) {
							libraryPageMsgList.add("Their is no image displayed");
							return result;
						}

						cfObj.commonClick(libraryPage_OR.listOfRenewNowBtn().get(0));

						result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.noOfOffersAvail(), 10);
						if (result) {

							String noOfOffersAvail = libraryPage_OR.noOfOffersAvail().getText();
							String[] arr = noOfOffersAvail.split(" ");
							int countOfOffers = Integer.parseInt(arr[0]);

							if (countOfOffers > 0) {

								result = courseDetailPage.verifyInvalidCoupon(driver);
								if (!result) {
									libraryPageMsgList.addAll(courseDetailPage.coursePageMsgList);
									return result;
								}

								result = courseDetailPage.selectCoupon_verifyAmount(driver);
								if (!result) {
									libraryPageMsgList.addAll(courseDetailPage.coursePageMsgList);
									return result;
								}

								result = courseDetailPage.changeCoupon(driver);
								if (!result) {
									libraryPageMsgList.addAll(courseDetailPage.coursePageMsgList);
									return result;
								}

								result = courseDetailPage.applyManualCoupon(driver);
								if (!result) {
									libraryPageMsgList.addAll(courseDetailPage.coursePageMsgList);
									return result;
								}

							}
						}

						result = courseDetailPage.buyNowPack(driver);
						if (!result) {
							libraryPageMsgList.addAll(courseDetailPage.coursePageMsgList);
							return result;
						}

						result = courseDetailPage.verifyViewDetails(driver);
						if (!result) {
							libraryPageMsgList.addAll(courseDetailPage.coursePageMsgList);
							return result;
						}

						if ((ConfigFileReader.strEnv).equalsIgnoreCase("stag")
								|| (ConfigFileReader.strEnv).equalsIgnoreCase("dev")) {

							paymentPageUtilObj = new PaymentPageUtil(driver);

							result = paymentPageUtilObj.selectPaymentOption(driver, testData.getPaymentMethod(),
									testData);
							if (!result) {
								libraryPageMsgList.addAll(paymentPageUtilObj.paymentPageMsgList);
								return result;
							}

							if (testData.getIsKey().equalsIgnoreCase("pass")) {

								result = courseDetailPage.courseBuyStatus(driver);
								if (!result) {
									libraryPageMsgList.addAll(courseDetailPage.coursePageMsgList);
									return result;
								}

								result = courseDetailPage.checkCourseInLibrary(driver, courseName, testData);
								if (!result) {
									libraryPageMsgList.addAll(courseDetailPage.coursePageMsgList);
									return result;
								}

							} else {
								System.out.println(
										"User on Choose Payment Method page- the payment is aborted and the course is not renewed");
							}

						} else if ((ConfigFileReader.strEnv).equalsIgnoreCase("prod")) {

							System.out.println("The envirnonment is production, the course is not renewed");

						} else {
							libraryPageMsgList.add("The envirnoment is different from dev, stag and prod");
							return false;
						}
						return result;
					}
				}

			} else if (testData.getIsExpire() == false && testData.getIsFree() == false) {

				// scroll method till valid upto

				List<MobileElement> statusList = libraryPage_OR.listOfCourseValidOrRenewText();
				for (int i = 0; i < statusList.size(); i++) {
					String validityText = statusList.get(i).getText();
					if (validityText.contains("Valid Upto")) {

						result = cfObj.commonWaitForElementToBeVisible(driver,
								libraryPage_OR.listOfCourseTitlesInLib().get(i), 5);
						if (!result) {
							libraryPageMsgList.add("The course title text is not visible");
							return result;
						}

						result = libraryPage_OR.listOfCourseImagesInLib().get(i).isDisplayed();
						if (!result) {
							libraryPageMsgList.add("Their is no image displayed");
							return result;
						}

						result = cfObj.commonWaitForElementToBeVisible(driver, statusList.get(i), 5);
						if (!result) {
							libraryPageMsgList.add("The valid upto text is not visible");
							return result;
						}

						cfObj.commonClick(libraryPage_OR.listOfCourseTitlesInLib().get(i));

						return result;
					}
				}
			} else if (testData.getIsFree()) {

				cfObj.scrollUtillTheElementFound(driver, "//txt_course_remove");

				List<MobileElement> statusList = libraryPage_OR.listOfCourseValidOrRenewText();
				for (int i = 0; i < statusList.size(); i++) {
					String removeText = statusList.get(i).getText();
					if (removeText.equalsIgnoreCase("Remove")) {

						result = cfObj.commonWaitForElementToBeVisible(driver,
								libraryPage_OR.listOfCourseTitlesInLib().get(i), 5);
						if (!result) {
							libraryPageMsgList.add("The course title text is not visible");
							return result;
						}

						String freeCourseName = libraryPage_OR.listOfCourseTitlesInLib().get(i).getText();

						result = libraryPage_OR.listOfCourseImagesInLib().get(i).isDisplayed();
						if (!result) {
							libraryPageMsgList.add("Their is no image displayed");
							return result;
						}

						result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.listOfRemoveBtn().get(0),
								5);
						if (!result) {
							libraryPageMsgList.add("The remove button is not visible");
							return result;
						}

						cfObj.commonClick(libraryPage_OR.listOfRemoveBtn().get(0));

						result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.removeFreeCourse(), 5);
						if (!result) {
							libraryPageMsgList.add("The remove free course button in pop up is not visible");
							return result;
						}

						cfObj.commonClick(libraryPage_OR.removeFreeCourse());

						cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.yourPurchaseText(), 5);
						if (!result) {
							libraryPageMsgList.add("The purchase text is not visible after remove of free course");
							return result;
						}

						cfObj.commonSetTextTextBox(libraryPage_OR.searchBoxLibrary(), freeCourseName);

						result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.resultAfterSearch(), 5);
						if (!result) {
							libraryPageMsgList.add("No result found text is not visible");
							return result;
						}
						String resultText = libraryPage_OR.resultAfterSearch().getText();
						if (resultText.equalsIgnoreCase("No result found")) {
							return true;
						} else {
							libraryPageMsgList.add("The course is not delete or text is not visible");
							return false;
						}
					}

				}

			} else {
				libraryPageMsgList.add("The free and expire testdata is wrong");
				return false;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyExpireOrNot_Exception " + e.getMessage());
		}
		return result;
	}

	public boolean openCoursesInLibrary(AppiumDriver<MobileElement> driver, TestData testData) {
		boolean result = true;

		try {

			if (testData.getCourseType().equalsIgnoreCase("live")) {

				result = verifyMyDownloadsSection(driver);
				if (!result) {
					return result;
				}

				result = verifyMyListSection(driver);
				if (!result) {
					return result;
				}

				result = verifyMyNotesSection(driver);
				if (!result) {
					return result;
				}

				result = verifyMoreSection(driver);
				if (!result) {
					return result;
				}

				result = verifyMyCourseSection(driver);
				if (!result) {
					return result;
				}

				result = verifyVideoSectionInSmartCourses(driver);
				if (!result) {
					return result;
				}

				result = verifyCourseNotifications(driver);
				if (!result) {
					return result;
				}

				result = verifyExtendValidity(driver, testData);
				if (!result) {
					return result;
				}
			}

			else if (testData.getCourseType().equalsIgnoreCase("test-series")) {

				System.out.println("The test series is working but no content inside");

			} else if (testData.getCourseType().equalsIgnoreCase("video")) {

				result = verifyMyDownloadsSection(driver);
				if (!result) {
					return result;
				}

				result = verifyMyDoubtsSection(driver);
				if (!result) {
					return result;
				}

				result = verifyMyListSection(driver);
				if (!result) {
					return result;
				}

				result = verifyMyNotesSection(driver);
				if (!result) {
					return result;
				}

				result = verifyMoreSection(driver);
				if (!result) {
					return result;
				}

				result = verifyMyCourseSection(driver);
				if (!result) {
					return result;
				}

				result = verifyVideoSectionInSmartCourses(driver);
				if (!result) {
					return result;
				}

				result = verifyCourseNotifications(driver);
				if (!result) {
					return result;
				}

				result = verifyExtendValidity(driver, testData);
				if (!result) {
					return result;
				}

			} else {
				System.out.println("The coursetype is " + testData.getCourseType() + ". No column for this in library");
				return true;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("openCoursesInLibraryException " + e.getMessage());
		}
		return result;
	}

	public boolean verifyMyDownloadsSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;

		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.myDownloadsBtn(), 5);
			if (!result) {
				libraryPageMsgList.add("My downloads not visible");
				return result;
			}
			cfObj.commonClick(libraryPage_OR.myDownloadsBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "txt_sorry", "id", 5);
			if (result) {
				System.out.println("The downloads is empty");
				return true;
			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver,
						libraryPage_OR.getListTitleOfVideosInTopic().get(0), 5);
				if (!result) {
					libraryPageMsgList.add("My downloads is empty or element title not visible");
					return result;
				}

				cfObj.commonClick(libraryPage_OR.deleteVideoBtn().get(0));
				return true;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyMyDownloadsSectionException " + e.getMessage());
		}
		return result;
	}

	public boolean verifyMyListSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;

		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.myListBtn(), 5);
			if (!result) {
				libraryPageMsgList.add("My List not visible");
				return result;
			}
			cfObj.commonClick(libraryPage_OR.myListBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "txt_sorry", "id", 5);
			if (result) {
				System.out.println("The list is empty");
				return true;

			} else {
				String titleOfFirstList = libraryPage_OR.myListTitles().get(0).getText();
				result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.myListTitles().get(0), 5);
				if (!result) {
					libraryPageMsgList.add("My list is empty or element title not visible");
					return result;
				}

				cfObj.commonClick(libraryPage_OR.myListTitles().get(0));

				result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.myListTitles().get(0), 5);
				if (result) {
					return true;
				} else {
					String dialogBoxTitleInList = libraryPage_OR.dialogTitleInList().getText();
					if (titleOfFirstList.equalsIgnoreCase(dialogBoxTitleInList)) {

						result = cfObj.commonWaitForElementToBeVisible(driver,
								libraryPage_OR.getListOfVideoNamesInMyList().get(0), 5);
						if (!result) {
							libraryPageMsgList.add("The video name is not visible");
							return result;
						}

						cfObj.commonClick(libraryPage_OR.getListOfDeleteVideosInMyList().get(0));

					}
				}
				return true;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyMyListSectionException " + e.getMessage());
		}
		return result;
	}

	public boolean verifyMyNotesSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;

		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.myNotesBtn(), 5);
			if (!result) {
				libraryPageMsgList.add("My Notes not visible");
				return result;
			}
			cfObj.commonClick(libraryPage_OR.myNotesBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "txt_sorry", "id", 5);
			if (result) {
				System.out.println("The list is empty");
				return true;

			} else {

				result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.courseNameInNotes().get(0), 5);
				if (!result) {
					libraryPageMsgList.add("course name is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.courseDateInNotes().get(0), 5);
				if (!result) {
					libraryPageMsgList.add("course date is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.goToVideoInNotes().get(0), 5);
				if (!result) {
					libraryPageMsgList.add("go to video btn is not visible");
					return result;
				}
				cfObj.commonClick(libraryPage_OR.goToVideoInNotes().get(0));
				driver.navigate().back();

				result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.downloadContentInNotes().get(0),
						5);
				if (!result) {
					libraryPageMsgList.add("download btn is not visible");
					return result;
				}
				cfObj.commonClick(libraryPage_OR.downloadContentInNotes().get(0));

				result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.editContentInNotesBtn().get(0),
						5);
				if (!result) {
					libraryPageMsgList.add("Edit notes btn is not visible");
					return result;
				}
				String noteToBeChangedString = "India is the best";
				cfObj.commonClick(libraryPage_OR.editContentInNotesBtn().get(0));
				result = cfObj.commonSetTextTextBox(libraryPage_OR.editTextInNotes(), noteToBeChangedString);
				if (!result) {
					libraryPageMsgList.add("The content not changed");
					return result;
				}
				cfObj.commonClick(libraryPage_OR.saveBtnInNotes());

				result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.deleteContentInNotes().get(0), 5);
				if (!result) {
					libraryPageMsgList.add("Delete notes btn is not visible");
					return result;
				}
				cfObj.commonClick(libraryPage_OR.deleteContentInNotes().get(0));
				cfObj.commonClick(libraryPage_OR.deleteNote());

				return true;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyMyNotesSectionException " + e.getMessage());
		}
		return result;
	}

	public boolean verifyMoreSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.moreBtn(), 5);
			if (!result) {
				libraryPageMsgList.add("More Btn is not visible");
				return result;
			}
			cfObj.commonClick(libraryPage_OR.moreBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.customerSupportBtn(), 5);
			if (!result) {
				libraryPageMsgList.add("customer support element is not visible");
				return result;
			}
			cfObj.commonClick(libraryPage_OR.customerSupportBtn());
			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.shareBtn(), 5);
			if (!result) {
				libraryPageMsgList.add("Share btn element is not visible");
				return result;
			}
			cfObj.commonClick(libraryPage_OR.shareBtn());
			driver.navigate().back();

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyMoreSectionException " + e.getMessage());
		}
		return result;
	}

	public boolean verifyMyCourseSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;

		try {
			cfObj.swipeRightOnElement(libraryPage_OR.myListBtn(), driver);
			cfObj.swipeRightOnElement(libraryPage_OR.myDownloadsBtn(), driver);
			cfObj.commonClick(libraryPage_OR.myCourseBtn());

			List<MobileElement> titleOfVideosInTopics = libraryPage_OR.getListTitleOfVideosInTopic();
			List<MobileElement> downloadBtnElements = libraryPage_OR.downloadBtnListInCS();

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "inner_download", "id", 5);
			if (result) {
				result = cfObj.commonWaitForElementToBeVisible(driver,
						libraryPage_OR.getListTitleOfVideosInTopic().get(0), 5);
				if (!result) {
					libraryPageMsgList.add("The title of videos in topics not visible");
					return result;
				}
				result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.downloadBtnListInCS().get(0), 5);
				if (!result) {
					libraryPageMsgList.add("The download btn not visible");
					return result;
				}
			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.getListTopicsOfCourse().get(0),
						5);
				if (!result) {
					libraryPageMsgList.add("The topic is not visible in course");
				}

				cfObj.commonClick(libraryPage_OR.getListTopicsOfCourse().get(0));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "inner_download", "id", 5);
				if (result) {
					result = cfObj.commonWaitForElementToBeVisible(driver, titleOfVideosInTopics.get(0), 5);
					if (!result) {
						libraryPageMsgList.add("The title of videos in topics not visible");
						return result;
					}
					result = cfObj.commonWaitForElementToBeVisible(driver, downloadBtnElements.get(0), 5);
					if (!result) {
						libraryPageMsgList.add("The download btn not visible");
						return result;
					}
				} else {
					result = cfObj.commonWaitForElementToBeVisible(driver,
							libraryPage_OR.getListSubTopicsOfCourse().get(0), 5);
					if (!result) {
						libraryPageMsgList.add("The topic is not visible in course");
					}

					cfObj.commonClick(libraryPage_OR.getListSubTopicsOfCourse().get(0));

					if (result) {
						result = cfObj.commonWaitForElementToBeVisible(driver, titleOfVideosInTopics.get(0), 5);
						if (!result) {
							libraryPageMsgList.add("The title of videos in topics not visible");
							return result;
						}
						result = cfObj.commonWaitForElementToBeVisible(driver, downloadBtnElements.get(0), 5);
						if (!result) {
							libraryPageMsgList.add("The download btn not visible");
							return result;
						}
					} else {
						System.out.println("So many times, clicked on sub topics, video not coming ");
					}
				}
			}
		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyMyCourseInSmartCoursesException " + e.getMessage());
		}
		return result;
	}

	public boolean verifyVideoSectionInSmartCourses(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyVideoSectionInSmartCoursesException " + e.getMessage());
		}
		return result;
	}

	public boolean verifyCourseNotifications(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.courseNotification(), 5);
			if (!result) {
				libraryPageMsgList.add("The notification button is not visible");
				return result;
			}

			cfObj.commonClick(libraryPage_OR.courseNotification());

			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.videoBoxInNotification(), 5);
			if (!result) {
				System.out.println("The notification video box title is not visible");
				result = true;
			} else {

				// video box
				cfObj.commonClick(libraryPage_OR.videoBoxInNotification());

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "img_no_data", "id", 5);
				if (result) {
					System.out.println("The video list is empty");

				} else {

					result = cfObj.commonWaitForElementToBeVisible(driver,
							libraryPage_OR.videoTitlesInNotificationList().get(0), 5);
					if (!result) {
						libraryPageMsgList.add("The notification video title is not visible");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver,
							libraryPage_OR.videoDatesInNotificationList().get(0), 5);
					if (!result) {
						libraryPageMsgList.add("The notification video date is not visible");
						return result;
					}
				}

			}

			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.textBoxInNotification(), 5);
			if (!result) {
				System.out.println("The notification text box title is not visible");
				result = true;
			} else {
				// text box
				cfObj.commonClick(libraryPage_OR.textBoxInNotification());

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "img_no_data", "id", 5);
				if (result) {
					System.out.println("The text list is empty");

				} else {

					result = cfObj.commonWaitForElementToBeVisible(driver,
							libraryPage_OR.textTitlesInNotificationList().get(0), 5);
					if (!result) {
						libraryPageMsgList.add("The notification text box title is not visible");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver,
							libraryPage_OR.textDatesInNotificationList().get(0), 5);
					if (!result) {
						libraryPageMsgList.add("The notification text box date is not visible");
						return result;
					}

					cfObj.commonClick(libraryPage_OR.textTitlesInNotificationList().get(0));

					result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.pdfDownloadBtn(), 5);
					if (!result) {
						libraryPageMsgList.add("The download pdf btn is not visible");
						return result;
					}

					cfObj.commonClick(libraryPage_OR.pdfDownloadBtn());

				}
			}

			// quiz box
			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.quizBoxInNotification(), 5);
			if (!result) {
				System.out.println("The notification quiz box title is not visible");
				result = true;
			} else {
				cfObj.commonClick(libraryPage_OR.quizBoxInNotification());

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "img_no_data", "id", 5);
				if (result) {
					System.out.println("The quiz list is empty");

				} else {

					result = cfObj.commonWaitForElementToBeVisible(driver,
							libraryPage_OR.quizTitlesInNotificationList().get(0), 5);
					if (!result) {
						libraryPageMsgList.add("The notification quiz title is not visible");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver,
							libraryPage_OR.quizDatesInNotificationList().get(0), 5);
					if (!result) {
						libraryPageMsgList.add("The notification quiz date is not visible");
						return result;
					}
				}
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyCourseUpdatesException " + e.getMessage());
		}
		return result;
	}

	public boolean verifyMyDoubtsSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.myDoubtsBtn(), 5);
			if (!result) {
				System.out.println("The doubt section/btn is not visible");
				return true;
			}

			cfObj.commonClick(libraryPage_OR.myDoubtsBtn());

			String doubt = "I have a doubt";
			result = cfObj.commonSetTextTextBox(libraryPage_OR.doubtDashboardMsgText(), doubt);
			if (!result) {
				libraryPageMsgList.add("My edit text box is not visible");
				return result;
			}

			cfObj.commonClick(libraryPage_OR.sendDoubtMsgText());

			String sentDoubtText = libraryPage_OR.sentMsgText().get(0).getText();
			if (doubt.equalsIgnoreCase(sentDoubtText)) {
				return true;
			} else {
				libraryPageMsgList.add("My doubt msg is not same as sent");
				return false;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyMyDoubtsSectionException " + e.getMessage());
		}
		return result;
	}

	public boolean checkCourseInLibrary(AppiumDriver<MobileElement> driver, TestData testData,
			String actualCourseName) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.searchBoxLibrary(), 5);
			if (!result) {
				libraryPageMsgList.add("It is not library page or purchase text is not visible");
				return result;
			}

			cfObj.commonSetTextTextBox(libraryPage_OR.searchBoxLibrary(), actualCourseName);

			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.listOfCourseTitlesInLib().get(0), 5);
			if (!result) {
				libraryPageMsgList.add("The course title text is not visible");
				return result;
			}

			String courseNameAfterSearch = libraryPage_OR.listOfCourseTitlesInLib().get(0).getText();

			if (actualCourseName.equalsIgnoreCase(courseNameAfterSearch)) {
				return true;
			} else {
				libraryPageMsgList.add("The course bought in lib is not present");
				return false;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("checkCourseInLibrary_Exception " + e.getMessage());
		}
		return result;
	}

	public boolean verifyExtendValidity(AppiumDriver<MobileElement> driver, TestData testData) {
		boolean result = true;
		try {
			driver.navigate().back();

			cfObj.swipeLeftOnElement(libraryPage_OR.myDownloadsBtn(), driver);
			cfObj.swipeLeftOnElement(libraryPage_OR.myListBtn(), driver);

			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.moreBtn(), 5);
			if (!result) {
				libraryPageMsgList.add("More Btn is not visible");
				return result;
			}
			cfObj.commonClick(libraryPage_OR.moreBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.extendValidity(), 5);
			if (!result) {
				libraryPageMsgList.add("Extend validity button is not visible");
				return result;
			}

			cfObj.commonClick(libraryPage_OR.extendValidity());

			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.continueBtn(), 5);
			if (!result) {
				libraryPageMsgList.add("The continue btn is not visible");
				return result;
			}

			cfObj.commonClick(libraryPage_OR.continueBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.noOfOffersAvail(), 10);
			if (result) {

				String noOfOffersAvail = libraryPage_OR.noOfOffersAvail().getText();
				String[] arr = noOfOffersAvail.split(" ");
				int countOfOffers = Integer.parseInt(arr[0]);

				if (countOfOffers > 0) {

					result = courseDetailPage.verifyInvalidCoupon(driver);
					if (!result) {
						libraryPageMsgList.addAll(courseDetailPage.coursePageMsgList);
						return result;
					}

					result = courseDetailPage.selectCoupon_verifyAmount(driver);
					if (!result) {
						libraryPageMsgList.addAll(courseDetailPage.coursePageMsgList);
						return result;
					}

					result = courseDetailPage.changeCoupon(driver);
					if (!result) {
						libraryPageMsgList.addAll(courseDetailPage.coursePageMsgList);
						return result;
					}

					result = courseDetailPage.applyManualCoupon(driver);
					if (!result) {
						libraryPageMsgList.addAll(courseDetailPage.coursePageMsgList);
						return result;
					}

				}
			}

			result = courseDetailPage.buyNowPack(driver);
			if (!result) {
				libraryPageMsgList.addAll(courseDetailPage.coursePageMsgList);
				return result;
			}

			result = courseDetailPage.verifyViewDetails(driver);
			if (!result) {
				libraryPageMsgList.addAll(courseDetailPage.coursePageMsgList);
				return result;
			}

			if ((ConfigFileReader.strEnv).equalsIgnoreCase("stag")
					|| (ConfigFileReader.strEnv).equalsIgnoreCase("dev")) {

				paymentPageUtilObj = new PaymentPageUtil(driver);

				result = paymentPageUtilObj.selectPaymentOption(driver, testData.getPaymentMethod(), testData);
				if (!result) {
					libraryPageMsgList.addAll(paymentPageUtilObj.paymentPageMsgList);
					return result;
				}

				if (testData.getIsKey().equalsIgnoreCase("pass")) {

					result = courseDetailPage.courseBuyStatus(driver);
					if (!result) {
						libraryPageMsgList.addAll(courseDetailPage.coursePageMsgList);
						return result;
					}

				} else {
					System.out.println(
							"User on Choose Payment Method page- the payment is aborted and the course is not renewed");
				}

			} else if ((ConfigFileReader.strEnv).equalsIgnoreCase("prod")) {

				System.out.println("The envirnonment is production, the course is not renewed");

			} else {
				libraryPageMsgList.add("The envirnoment is different from dev, stag and prod");
				return false;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyExtendValidity_Exception " + e.getMessage());
		}
		return result;
	}

}
