package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.TestSeries_OR;
import pojo.testdata.TestDataTest;
import util.Common_Function;
import util.Common_Function.key;
import util.ConfigFileReader;

public class TestSeriesUtil {

	TestSeries_OR testSeries_OR;
	LoginUtil loginutillObj;
	HomePageUtil homePageUtilObj;
	ConfigFileReader configFileReader;
	public List<String> testseriesMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();

	public TestSeriesUtil(AppiumDriver<MobileElement> driver) {
		testSeries_OR = new TestSeries_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), testSeries_OR);
	}

	public boolean verifyAllNormalTest(AppiumDriver<MobileElement> driver, TestDataTest testDataTest) {
		boolean result = true;
		loginutillObj = new LoginUtil(driver);
		homePageUtilObj = new HomePageUtil(driver);
		configFileReader = new ConfigFileReader();
		try {

			result = loginutillObj.doSignUp(driver);
			if (!result) {
				testseriesMsgList.addAll(loginutillObj.loginMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListBottomMenuMyHome().get(0), 10);
			if (!result) {
				testseriesMsgList.add("The button of my home on bottom is not visible");
				return result;
			}

			cfObj.commonClick(testSeries_OR.getListBottomMenuMyHome().get(0));

			result = homePageUtilObj.clickOnTestseries(driver, testDataTest.getCourseName());
			if (!result) {
				testseriesMsgList.addAll(homePageUtilObj.homePageMsglist);
				return result;
			}

			result = verify_openCourseContent(driver, testDataTest);
			if (!result) {
				return result;
			}

			result = verifyFreeTest(driver);
			if (!result) {
				return result;
			}

			result = verifyPaymentFlow(driver, testDataTest);
			if (!result) {
				return result;
			}

			result = verifyStatusOfFreeTest(driver, testDataTest);
			if (!result) {
				return result;
			}

			result = verifyPaidTest(driver, testDataTest);
			if (!result) {
				return result;
			}

			result = verifyComingSoonTest(driver, testDataTest);
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			testseriesMsgList.add("verifyFreeTestseries_Exception " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verify_openCourseContent(AppiumDriver<MobileElement> driver, TestDataTest testDataTest) {
		boolean result = true;

		try {

			result = cfObj.scrollUtillTheElementFound(driver,
					"//android.widget.TextView[contains(@resource-id,'tv_str_show_content')]");

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.viewCourseContent(), 5);
			if (!result) {
				testseriesMsgList.add("The view btn of course content is not visible");
			}

			cfObj.commonClick(testSeries_OR.viewCourseContent());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "txt_content", "id", 10);
			if (!result) {
				testseriesMsgList.add("The view course content text is not visible");
				return result;
			}

			if (testDataTest.getCourseName().equalsIgnoreCase("shubh automate test1")) {
				cfObj.commonClick(testSeries_OR.namesOfCourseContentElements().get(0));
				cfObj.commonClick(testSeries_OR.namesOfsubCourseContentElements().get(0));

			} else if (testDataTest.getCourseName().equalsIgnoreCase("shubh automate t2")) {
				cfObj.commonClick(testSeries_OR.namesOfCourseContentElements().get(0));

			} else if (testDataTest.getCourseName().equalsIgnoreCase("shubh automate t12")) {
				cfObj.commonClick(testSeries_OR.namesOfCourseContentElements().get(0));
				cfObj.commonClick(testSeries_OR.namesOfsubCourseContentElements().get(0));

			} else {
				testseriesMsgList.add("The course name is wrong in test data");
				return false;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestName().get(0), 5);
			if (!result) {
				testseriesMsgList.add("The test series name is not visible after opening the folders");
				return result;
			}

		} catch (Exception e) {
			testseriesMsgList.add("verifyCourseContentException");
			result = false;
		}
		return result;
	}

	public boolean verifyFreeTest(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		int i = 0;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestName().get(0), 5);
			if (!result) {
				testseriesMsgList.add("The test series name is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestStatus().get(0), 5);
			if (!result) {
				testseriesMsgList.add("The test series status is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestDetails().get(0), 5);
			if (!result) {
				testseriesMsgList.add("The test series details is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestStatus().get(0), 5);
			if (!result) {
				testseriesMsgList.add("The test series status is not visible");
				return result;
			}

			String test_StatuString = testSeries_OR.getListOfTestStatus().get(0).getText();

			cfObj.commonClick(testSeries_OR.getListOfTestStatus().get(0));

			result = verifyTestFlow(driver, test_StatuString, i);
			if (!result) {
				return result;
			}

			result = verifyResultOverview(driver, i);
			if (!result) {
				return result;
			}

			cfObj.pressAndroidKey(driver, key.BACK, 1);

		} catch (Exception e) {
			testseriesMsgList.add("verifyFreeTestType1_Exception " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyPaymentFlow(AppiumDriver<MobileElement> driver, TestDataTest testDataTest) {
		boolean result = true;
		CourseDetailPage courseDetailPage = new CourseDetailPage(driver);
		PaymentPageUtil paymentPageUtilObj;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.buyNowBtnBelow(), 5);
			if (!result) {
				testseriesMsgList.add("The buy now button above is not visible");
				return result;
			}

			cfObj.commonClick(testSeries_OR.buyNowBtnBelow());

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.noOfOffersAvail(), 10);
			if (result) {

				String noOfOffersAvail = testSeries_OR.noOfOffersAvail().getText();
				String[] arr = noOfOffersAvail.split(" ");
				int countOfOffers = Integer.parseInt(arr[0]);

				if (countOfOffers > 0) {

					result = courseDetailPage.verifyInvalidCoupon(driver);
					if (!result) {
						return result;
					}
					result = courseDetailPage.selectCoupon_verifyAmount(driver);
					if (!result) {
						return result;
					}

					result = courseDetailPage.changeCoupon(driver);
					if (!result) {
						return result;
					}

					result = courseDetailPage.applyManualCoupon(driver);
					if (!result) {
						return result;
					}
				}

			}

			result = courseDetailPage.buyNowPack(driver);
			if (!result) {
				return result;
			}

			result = courseDetailPage.verifyViewDetails(driver);
			if (!result) {
				return result;
			}

			if ((ConfigFileReader.strEnv).equalsIgnoreCase("stag")
					|| (ConfigFileReader.strEnv).equalsIgnoreCase("dev")) {

				paymentPageUtilObj = new PaymentPageUtil(driver);

				result = paymentPageUtilObj.selectPaymentOption(driver, testDataTest.getPaymentMethod(),
						testDataTest.getIsKey());
				if (!result) {
					testseriesMsgList.addAll(paymentPageUtilObj.paymentPageMsgList);
					return result;
				}
			}

			result = courseDetailPage.courseBuyStatus(driver);
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			testseriesMsgList.add("verifyPaymentFlow_Exception " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyStatusOfFreeTest(AppiumDriver<MobileElement> driver, TestDataTest testDataTest) {
		boolean result = true;
		try {

			if (testDataTest.getCourseName().equalsIgnoreCase("shubh automate test1")) {
				cfObj.commonClick(testSeries_OR.folder1().get(0));
				cfObj.commonClick(testSeries_OR.subfolder1().get(0));

			} else if (testDataTest.getCourseName().equalsIgnoreCase("shubh automate t2")) {
				cfObj.commonClick(testSeries_OR.folder1().get(0));

			} else if (testDataTest.getCourseName().equalsIgnoreCase("shubh automate t12")) {
				cfObj.commonClick(testSeries_OR.folder1().get(0));
				cfObj.commonClick(testSeries_OR.subfolder1().get(0));

			} else {
				testseriesMsgList.add("The course name is wrong in test data");
				return false;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestName().get(0), 5);
			if (!result) {
				testseriesMsgList.add("The test series name is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestStatus().get(0), 5);
			if (!result) {
				testseriesMsgList.add("The test series status is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestDetails().get(0), 5);
			if (!result) {
				testseriesMsgList.add("The test series details is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestStatus().get(0), 5);
			if (!result) {
				testseriesMsgList.add("The test series status is not visible");
				return result;
			}

			String test_StatuString = testSeries_OR.getListOfTestStatus().get(0).getText();

			if (!test_StatuString.equalsIgnoreCase("Result")) {
				testseriesMsgList.add("The test status is not changed as the test was given on cdp");
				return false;
			}

		} catch (Exception e) {
			testseriesMsgList.add("verifyStatusOfFreeTest_Exception " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyPaidTest(AppiumDriver<MobileElement> driver, TestDataTest testDataTest) {
		boolean result = true;
		int i = 1;
		try {

			if (testDataTest.getCourseName().equalsIgnoreCase("shubh automate t12")) {
				cfObj.commonClick(testSeries_OR.folder1().get(1));
				cfObj.commonClick(testSeries_OR.subfolder1().get(0));

				result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestName().get(0), 5);
				if (!result) {
					testseriesMsgList.add("The test series name is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestStatus().get(0), 5);
				if (!result) {
					testseriesMsgList.add("The test series status is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestDetails().get(0), 5);
				if (!result) {
					testseriesMsgList.add("The test series details is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestStatus().get(0), 5);
				if (!result) {
					testseriesMsgList.add("The test series status is not visible");
					return result;
				}

				String test_StatuString = testSeries_OR.getListOfTestStatus().get(0).getText();

				cfObj.commonClick(testSeries_OR.getListOfTestStatus().get(0));

				result = verifyTestFlow(driver, test_StatuString, 0);
				if (!result) {
					return result;
				}

				result = verifyResultOverview(driver, 0);
				if (!result) {
					return result;
				}
			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestName().get(1), 5);
				if (!result) {
					testseriesMsgList.add("The test series name is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestStatus().get(1), 5);
				if (!result) {
					testseriesMsgList.add("The test series status is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestDetails().get(1), 5);
				if (!result) {
					testseriesMsgList.add("The test series details is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestStatus().get(1), 5);
				if (!result) {
					testseriesMsgList.add("The test series status is not visible");
					return result;
				}

				String test_StatuString = testSeries_OR.getListOfTestStatus().get(1).getText();

				cfObj.commonClick(testSeries_OR.getListOfTestStatus().get(1));

				result = verifyTestFlow(driver, test_StatuString, i);
				if (!result) {
					return result;
				}

				result = verifyResultOverview(driver, i);
				if (!result) {
					return result;
				}
			}

		} catch (Exception e) {
			testseriesMsgList.add("verifyPaidTest_Exception " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyTestFlow(AppiumDriver<MobileElement> driver, String test_StatuString, int selectNo) {
		boolean result = true;
		try {
			if (test_StatuString.equalsIgnoreCase("Start Now")) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "quiz_language", "id", 10);
				if (!result) {
					driver.navigate().back();
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "quiz_language", "id", 10);
				if (!result) {
					testseriesMsgList.add("The quiz language is not visible or the start test page is not this");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.startTestButton(), 10);
				if (!result) {
					testseriesMsgList.add("The start now button is not visible");
					return result;
				}

				String InsideTestStatus = testSeries_OR.startTestButton().getText();

				if (!InsideTestStatus.equalsIgnoreCase("START TEST")) {
					testseriesMsgList
							.add("The test is new and it should display Start Test rather than resume or result");
					return false;
				}

				cfObj.commonClick(testSeries_OR.startTestButton());

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "txt_gotit", "id", 10);
				if (result) {
					cfObj.commonClick(testSeries_OR.swipePopUpGotItBtn());
				} else {
					result = true;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "submit_test", "id", 5);
				if (!result) {
					testseriesMsgList.add("The submit button is not visible after click on got it btn of swipe pop up");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.save_NextButton(), 5);
				if (!result) {
					testseriesMsgList.add("The Save & Next button is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.quesNo(), 5);
				if (!result) {
					testseriesMsgList.add("The question number is not visible");
					return result;
				}

				String questionNoString = testSeries_OR.quesNo().getText();
				int quesNumber = Integer.parseInt(questionNoString);

				if (quesNumber != 1) {
					testseriesMsgList.add("The question number is wrong, it should be 1");
					return false;
				}

				List<MobileElement> listOfOptions = testSeries_OR.listOfOptionsInQues();

				for (int i = 0; i < listOfOptions.size(); i++) {

					result = cfObj.commonWaitForElementToBeVisible(driver, listOfOptions.get(i), 5);
					if (!result) {
						testseriesMsgList.add("The list of option " + i + " is not visible");
						return result;
					}

					cfObj.commonClick(listOfOptions.get(i));
				}

				cfObj.commonClick(listOfOptions.get(1));

				cfObj.commonClick(testSeries_OR.save_NextButton());

				result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.quesNo(), 5);
				if (!result) {
					testseriesMsgList.add("The question number is not visible");
					return result;
				}

				String questionNoNextString = testSeries_OR.quesNo().getText();
				int nextQuesNumber = Integer.parseInt(questionNoNextString);

				if (nextQuesNumber != 2) {
					testseriesMsgList.add("The question number is wrong, it should be 2");
					return false;
				}

				cfObj.commonClick(testSeries_OR.save_NextButton());

				String questionNoNextsString = testSeries_OR.quesNo().getText();
				int nextsQuesNumber = Integer.parseInt(questionNoNextsString);

				if (nextsQuesNumber != 3) {
					testseriesMsgList.add("The question number is wrong, it should be 3");
					return false;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.pauseButton(), 5);
				if (!result) {
					testseriesMsgList.add("The pause button is not visible");
					return result;
				}

				cfObj.commonClick(testSeries_OR.pauseButton());

				result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.yesPausePopUpButton(), 10);
				if (!result) {
					testseriesMsgList.add("The pop up not visible after clicking on pause button");
					return result;
				}

				cfObj.commonClick(testSeries_OR.yesPausePopUpButton());

				result = cfObj.commonWaitForElementToBeVisible(driver,
						testSeries_OR.getListOfTestStatus().get(selectNo), 10);
				if (!result) {
					testseriesMsgList.add("The test series status is not visible after pausing the test");
					return result;
				}

				String test_Status = testSeries_OR.getListOfTestStatus().get(selectNo).getText();

				if (!test_Status.equalsIgnoreCase("Resume")) {
					testseriesMsgList.add("The test status should be resume but is " + test_Status);
					return false;
				}

				cfObj.commonClick(testSeries_OR.getListOfTestStatus().get(selectNo));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "quiz_language", "id", 10);
				if (!result) {
					testseriesMsgList.add("The quiz language is not visible or the start test page is not this");
					return result;
				}

				Thread.sleep(2000);

				result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.startTestButton(), 10);
				if (!result) {
					testseriesMsgList.add("The start now button is not visible");
					return result;
				}

				String insideTestStatusAgain = testSeries_OR.startTestButton().getText();

				if (!insideTestStatusAgain.equalsIgnoreCase("RESUME TEST")) {
					testseriesMsgList.add(
							"The test is resumed and it should display Resume Test rather than start now or result");
					return false;
				}

				cfObj.commonClick(testSeries_OR.startTestButton());

				cfObj.commonClick(testSeries_OR.submitTestButton());

				result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.submitPopUpButton(), 10);
				if (!result) {
					testseriesMsgList.add("The start now button is not visible");
					return result;
				}

				cfObj.commonClick(testSeries_OR.submitPopUpButton());

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "toolbar_sub_text", "id", 20);
				if (!result) {
					testseriesMsgList.add("It is not result overview page and not visible ");
					return result;
				}

			}
		} catch (Exception e) {
			testseriesMsgList.add("verifyTestFlow_Exception " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyResultOverview(AppiumDriver<MobileElement> driver, int selectNo) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "toolbar_sub_text", "id", 20);
			if (!result) {
				testseriesMsgList.add("It is not result overview page and heading not visible ");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.solutionButton(), 10);
			if (!result) {
				testseriesMsgList.add("The solutions button is not visible");
				return result;
			}

			cfObj.commonClick(testSeries_OR.solutionButton());

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.showCorrectAns(), 10);
			if (!result) {
				testseriesMsgList
						.add("The show correct answer button is not visible or the answer page is not visible");
				result = true;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.quesNo(), 5);
			if (!result) {
				testseriesMsgList.add("The question number is not visible");
				return result;
			}

			String questionNoString = testSeries_OR.quesNo().getText();
			int quesNumber = Integer.parseInt(questionNoString);

			if (quesNumber != 1) {
				testseriesMsgList.add("The question number is wrong, it should be 1");
				return false;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.showCorrectAns(), 10);
			if (result) {
				cfObj.commonClick(testSeries_OR.showCorrectAns());
			} else {
				result = true;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[contains(@text,'Explanation')]", "xpath", 20);
			if (!result) {
				testseriesMsgList.add(
						"The Explation text is not visible or the answer is not visible after clicking on show correct ans");
				return result;
			}

			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "toolbar_sub_text", "id", 20);
			if (!result) {
				testseriesMsgList.add("It is not result overview page after coming back from solution page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.solutionButton(), 10);
			if (!result) {
				testseriesMsgList.add("The solutions button is not visible");
				return result;
			}

			cfObj.commonClick(testSeries_OR.resultOverviewButton());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "drop_down_title", "id", 10);
			if (!result) {
				testseriesMsgList.add("The result analysis text is not visible");
				return result;
			}

			List<MobileElement> titlesOfdropDownResult = testSeries_OR.titlesOfResultAnalysis();

			result = cfObj.commonWaitForElementToBeVisible(driver, titlesOfdropDownResult.get(0), 10);
			if (!result) {
				testseriesMsgList.add("The title is not visible in result analysis");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "selected_indicator", "id", 10);
			if (!result) {
				testseriesMsgList.add("The selected indicator is not visible");
				return result;
			}

			for (int i = 1; i < titlesOfdropDownResult.size(); i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, titlesOfdropDownResult.get(i), 10);
				if (!result) {
					testseriesMsgList.add("The title is not visible in result analysis");
					return result;
				}

				cfObj.commonClick(titlesOfdropDownResult.get(i));

				cfObj.commonClick(testSeries_OR.resultOverviewButton());

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "drop_down_title", "id", 10);
				if (!result) {
					testseriesMsgList.add("The result analysis text is not visible");
					return result;
				}

			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "drop_down_title", "id", 10);
			if (!result) {
				testseriesMsgList.add("The result analysis text is not visible");
				return result;
			}

			cfObj.commonClick(titlesOfdropDownResult.get(0));

			cfObj.commonClick(testSeries_OR.backButton());

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.backButton(), 5);
			if (result) {
				driver.navigate().back();
			} else {
				result = true;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestStatus().get(selectNo),
					5);
			if (!result) {
				testseriesMsgList.add("The test series status is not visible");
				return result;
			}

			String test_StatusFinish = testSeries_OR.getListOfTestStatus().get(selectNo).getText();

			if (!test_StatusFinish.equalsIgnoreCase("Result")) {
				testseriesMsgList.add("The test status should be result but it is " + test_StatusFinish);
				return false;
			}

		} catch (Exception e) {
			testseriesMsgList.add("verifyResultOverview_Exception " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyComingSoonTest(AppiumDriver<MobileElement> driver, TestDataTest testDataTest) {
		boolean result = true;
		try {

			if (testDataTest.getCourseName().equalsIgnoreCase("shubh automate t12")) {
				cfObj.commonClick(testSeries_OR.folder1().get(0));
				cfObj.commonClick(testSeries_OR.subfolder1().get(0));
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestName().get(2), 5);
			if (!result) {
				testseriesMsgList.add("The test series name is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestStatus().get(2), 5);
			if (!result) {
				testseriesMsgList.add("The test series status is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestDetails().get(2), 5);
			if (!result) {
				testseriesMsgList.add("The test series details is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestStatus().get(2), 5);
			if (!result) {
				testseriesMsgList.add("The test series status is not visible");
				return result;
			}

			String test_StatuString = testSeries_OR.getListOfTestStatus().get(2).getText();

			if (!test_StatuString.equalsIgnoreCase("Coming Soon")) {
				testseriesMsgList.add("The test status should be coming soon but the test status is wrong");
				return false;
			}

			cfObj.commonClick(testSeries_OR.getListOfTestStatus().get(2));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "fbMessage", "id", 5);
			if (!result) {
				testseriesMsgList.add("The coming soon pop up is not visible");
				return result;
			}

			String msgComingSoon = testSeries_OR.comingSoonPopUp().getText();

			if (msgComingSoon.equalsIgnoreCase("This test will be available soon.")) {
				return true;
			} else {
				testseriesMsgList.add("The coming soon pop up message is wrong");
				return false;
			}

		} catch (Exception e) {
			testseriesMsgList.add("verifyComingSoonTest_Exception " + e.getMessage());
			result = false;
		}
		return result;
	}

}