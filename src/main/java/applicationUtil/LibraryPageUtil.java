package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.LibraryPage_OR;
import pojo.testdata.TestData;
import util.Common_Function;

public class LibraryPageUtil {

	LibraryPage_OR libraryPage_OR;
	CourseDetailPage courseDetailPageUtil;
	LoginUtil loginUtillObj;
	HomePageUtil homePageUtilObj;
	PaymentPageUtil paymentPageUtilObj;

	public ArrayList<String> libraryPageMsgList = new ArrayList<String>();

	public Common_Function cfObj = new Common_Function();

	public LibraryPageUtil(AppiumDriver<MobileElement> driver) {
		libraryPage_OR = new LibraryPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), libraryPage_OR);
	}

	public boolean verifyLibraryPage(AppiumDriver<MobileElement> driver, TestData testData) {
		boolean result = true;
		try {
			loginUtillObj = new LoginUtil(driver);
			boolean checkLoginPageOrNot = loginUtillObj.checkSignUpLoginPage(driver);
			if (checkLoginPageOrNot == true) {

				result = loginUtillObj.doSignUp(driver);
				if (!result) {
					libraryPageMsgList.addAll(loginUtillObj.loginMsgList);
					return result;
				}
			} else {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.IMG_CLOSE, "id", 30);
				if (result) {
					cfObj.commonClick(cfObj.commonGetElement(driver, ConstantUtil.IMG_CLOSE, "id"));
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ConstantUtil.NAV_LIB, "id", 30);
				if (!result) {
					libraryPageMsgList.add("Home page not opened after login");
					return result;
				}
			}

			homePageUtilObj = new HomePageUtil(driver);
			result = homePageUtilObj.clickOnCourseOnHomePage(driver);
			if (!result) {
				return result;
			}

			result = buyProcess(driver, testData);
			if (!result) {
				return result;
			}

			if (testData.getIsKey().equalsIgnoreCase("pass")) {

				result = orderPlacedOrNot(driver);
				if (!result) {
					return result;
				}

				result = verifyMyLibraryTitle(driver);
				if (!result) {
					return result;
				}

				result = openCoursesInLibrary(driver);
				if (!result) {
					return result;
				}
			} else {
				System.out.println("Failed the payment");
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("Exception in verifyLibraryPage " + e.getMessage());
		}

		return result;
	}

	public boolean buyProcess(AppiumDriver<MobileElement> driver, TestData testData) {
		boolean result = true;
		courseDetailPageUtil = new CourseDetailPage(driver);
		try {
			result = courseDetailPageUtil.clickOnBuyNow(driver);
			if (!result) {
				libraryPageMsgList.addAll(courseDetailPageUtil.coursePageMsgList);
				return result;
			}

			result = courseDetailPageUtil.verifyEMIoption(driver);
			if (!result) {
				libraryPageMsgList.addAll(courseDetailPageUtil.coursePageMsgList);
				return result;
			}

			result = courseDetailPageUtil.verifyPacks(driver);
			if (!result) {
				libraryPageMsgList.addAll(courseDetailPageUtil.coursePageMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.noOfOffersAvail(), 10);
			if (result) {

				String noOfOffersAvail = libraryPage_OR.noOfOffersAvail().getText();
				String[] arr = noOfOffersAvail.split(" ");
				int countOfOffers = Integer.parseInt(arr[0]);

				if (countOfOffers > 0) {

					result = courseDetailPageUtil.verifyInvalidCoupon(driver);
					if (!result) {
						libraryPageMsgList.addAll(courseDetailPageUtil.coursePageMsgList);
						return result;
					}

					result = courseDetailPageUtil.selectCoupon_verifyAmount(driver);
					if (!result) {
						libraryPageMsgList.addAll(courseDetailPageUtil.coursePageMsgList);
						return result;
					}

					result = courseDetailPageUtil.changeCoupon(driver);
					if (!result) {
						libraryPageMsgList.addAll(courseDetailPageUtil.coursePageMsgList);
						return result;
					}

					result = courseDetailPageUtil.applyManualCoupon(driver);
					if (!result) {
						libraryPageMsgList.addAll(courseDetailPageUtil.coursePageMsgList);
						return result;
					}

				}
			}

			result = courseDetailPageUtil.chooseYourPack(driver, testData.getChoosePack());
			if (!result) {
				libraryPageMsgList.addAll(courseDetailPageUtil.coursePageMsgList);
				return result;
			}

			result = courseDetailPageUtil.buyNowPack(driver);
			if (!result) {
				libraryPageMsgList.addAll(courseDetailPageUtil.coursePageMsgList);
				return result;
			}

			result = courseDetailPageUtil.verifyViewDetails(driver);
			if (!result) {
				libraryPageMsgList.addAll(courseDetailPageUtil.coursePageMsgList);
				return result;
			}

			paymentPageUtilObj = new PaymentPageUtil(driver);

			result = paymentPageUtilObj.selectPaymentOption(driver, testData.getPaymentMethod(), testData);
			if (!result) {
				libraryPageMsgList.addAll(paymentPageUtilObj.paymentPageMsgList);
				return result;
			}

			if (testData.getIsKey().equalsIgnoreCase("pass")) {

				result = courseDetailPageUtil.courseBuyStatus(driver);
				if (!result) {
					libraryPageMsgList.addAll(courseDetailPageUtil.coursePageMsgList);
					return result;
				}

			} else {
				libraryPageMsgList.add("There is failure in course purchase");
				return true;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("Exception in buyProcess  " + e.getMessage());
		}
		return result;
	}

	public boolean orderPlacedOrNot(AppiumDriver<MobileElement> driver) {
		boolean result = true;

		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "bt_action_pc", "id", 10);
			if (!result) {
				libraryPageMsgList.add("The order status page is not visible");
				return result;
			}

			cfObj.commonClick(libraryPage_OR.goToLibraryBtn());

		} catch (Exception e) {
			libraryPageMsgList.add("orderPlacedOrNotException " + e.getMessage());
		}
		return result;
	}

	public boolean verifyMyLibraryTitle(AppiumDriver<MobileElement> driver) {
		boolean result = true;

		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.myLibraryTextList().get(0), 10);
			if (!result) {
				libraryPageMsgList.add("My library page not open");
				return result;
			}

		} catch (Exception e) {
			result = false;
			libraryPageMsgList.add("verifyMyLibraryTitleException " + e.getMessage());
		}
		return result;
	}

	public boolean openCoursesInLibrary(AppiumDriver<MobileElement> driver) {
		boolean result = true;

		try {
			driver.findElement(By.id("tv_nav_library")).click();

			List<MobileElement> titleOfCourses = libraryPage_OR.titleOfCoursesList();
			List<MobileElement> titleBoxOfCourses = libraryPage_OR.titleOfCoursesBoxList();
			for (int i = 0; i < titleOfCourses.size(); i++) {
				String titleOfCourseString = titleOfCourses.get(i).getText();

				if (titleOfCourseString.equalsIgnoreCase("Smart Courses")) {

					result = cfObj.commonWaitForElementToBeVisible(driver, titleOfCourses.get(i), 10);
					if (!result) {
						libraryPageMsgList.add("The smart courses are not visible");
						return result;
					}

					cfObj.commonClick(titleBoxOfCourses.get(i));

					List<MobileElement> subCourseBoxes = libraryPage_OR.titleOfSubCoursesBoxList();
					List<MobileElement> subCourseTitles = libraryPage_OR.titleOfSubCoursesList();
					List<MobileElement> subCourseExpires = libraryPage_OR.courseExpireList();
					List<MobileElement> subCourseStatus = libraryPage_OR.courseStatusList();

					result = cfObj.commonWaitForElementToBeVisible(driver, subCourseTitles.get(0), 5);
					if (!result) {
						libraryPageMsgList.add("The title of subcourse is not visible");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, subCourseExpires.get(0), 5);
					if (!result) {
						libraryPageMsgList.add("The expiration of subcourse is not visible");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, subCourseStatus.get(0), 5);
					if (!result) {
						libraryPageMsgList.add("The status of subcourse is not visible");
						return result;
					}

					cfObj.commonClick(subCourseStatus.get(0));

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//androidx.appcompat.app.ActionBar.b[@content-desc=\"My Course\"]", "xpath", 5);
					if (!result) {
						cfObj.commonClick(libraryPage_OR.popUpLaterBtn());
					}

					driver.navigate().back();

					cfObj.commonClick(subCourseBoxes.get(0));

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

					driver.navigate().back();

					driver.navigate().back();

				} else if (titleOfCourseString.equalsIgnoreCase("Micro Courses")) {

					result = cfObj.commonWaitForElementToBeVisible(driver, titleOfCourses.get(i), 10);
					if (!result) {
						libraryPageMsgList.add("The micro courses are not visible");
						return result;
					}

					cfObj.commonClick(titleBoxOfCourses.get(i));

					List<MobileElement> subCourseTitles = libraryPage_OR.titleOfSubCoursesList();
					List<MobileElement> subCourseExpires = libraryPage_OR.courseExpireList();
					List<MobileElement> subCourseStatus = libraryPage_OR.courseStatusList();

					result = cfObj.commonWaitForElementToBeVisible(driver, subCourseTitles.get(0), 5);
					if (!result) {
						libraryPageMsgList.add("The title of subcourse is not visible");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, subCourseExpires.get(0), 5);
					if (!result) {
						libraryPageMsgList.add("The expiration of subcourse is not visible");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, subCourseStatus.get(0), 5);
					if (!result) {
						libraryPageMsgList.add("The status of subcourse is not visible");
						return result;
					}

					cfObj.commonClick(subCourseStatus.get(0));

					result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.myCourseBtn(), 5);
					if (!result) {
						cfObj.commonClick(libraryPage_OR.closePopUpInMicro());
					}

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

					driver.navigate().back();

					driver.navigate().back();

				}

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
					cfObj.commonClick(libraryPage_OR.getListTopicsOfCourse().get(0));
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

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "video_sorry", "id", 5);
			if (result) {
				System.out.println("The video list is empty");

			} else {

				// video box
				result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.videoBoxInNotification(), 5);
				if (!result) {
					libraryPageMsgList.add("The notification video box title is not visible");
					return result;
				}

				cfObj.commonClick(libraryPage_OR.videoBoxInNotification());

				result = cfObj.commonWaitForElementToBeVisible(driver,
						libraryPage_OR.videoTitlesInNotificationList().get(1), 5);
				if (!result) {
					libraryPageMsgList.add("The notification video title is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						libraryPage_OR.videoDatesInNotificationList().get(1), 5);
				if (!result) {
					libraryPageMsgList.add("The notification video date is not visible");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.textBoxInNotification(), 5);
			if (!result) {
				libraryPageMsgList.add("The notification text box title is not visible");
				return result;
			}

			// text box
			cfObj.commonClick(libraryPage_OR.textBoxInNotification());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "txt_sorry", "id", 5);
			if (result) {
				System.out.println("The text list is empty");

			} else {

				result = cfObj.commonWaitForElementToBeVisible(driver,
						libraryPage_OR.textTitlesInNotificationList().get(1), 5);
				if (!result) {
					libraryPageMsgList.add("The notification text box title is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						libraryPage_OR.textDatesInNotificationList().get(1), 5);
				if (!result) {
					libraryPageMsgList.add("The notification text box date is not visible");
					return result;
				}

				cfObj.commonClick(libraryPage_OR.textTitlesInNotificationList().get(1));

				result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.pdfDownloadBtn(), 5);
				if (!result) {
					libraryPageMsgList.add("The download pdf btn is not visible");
					return result;
				}

				cfObj.commonClick(libraryPage_OR.pdfDownloadBtn());

			}

			// quiz box
			result = cfObj.commonWaitForElementToBeVisible(driver, libraryPage_OR.quizBoxInNotification(), 5);
			if (!result) {
				libraryPageMsgList.add("The notification quiz box title is not visible");
				return result;
			}

			cfObj.commonClick(libraryPage_OR.quizBoxInNotification());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "quiz_sorry", "id", 5); // id galt hai
			if (result) {
				System.out.println("The quiz list is empty");

			} else {

				result = cfObj.commonWaitForElementToBeVisible(driver,
						libraryPage_OR.quizTitlesInNotificationList().get(1), 5);
				if (!result) {
					libraryPageMsgList.add("The notification quiz title is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						libraryPage_OR.quizDatesInNotificationList().get(1), 5);
				if (!result) {
					libraryPageMsgList.add("The notification quiz date is not visible");
					return result;
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

}
