package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.TestSeries_OR;
import util.Common_Function;
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

	public boolean verifyFreeTestSeries(AppiumDriver<MobileElement> driver) {
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

			result = homePageUtilObj.clickOnFreeTestseries(driver);
			if (!result) {
				testseriesMsgList.addAll(homePageUtilObj.homePageMsglist);
				return result;
			}

			result = openTest(driver);
			if (!result) {
				return result;
			}

			result = verifyFreeTest(driver);
			if (!result) {
				return result;
			}

		} catch (Exception e) {
			testseriesMsgList.add("verifyFreeTestseries_Exception " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean openTest(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(testSeries_OR.folder1());
			cfObj.commonClick(testSeries_OR.subfolder1());

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

		} catch (Exception e) {
			testseriesMsgList.add("openTest_Exception " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyFreeTest(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestStatus().get(0), 5);
			if (!result) {
				testseriesMsgList.add("The test series status is not visible");
				return result;
			}

			String test_StatuString = testSeries_OR.getListOfTestStatus().get(0).getText();

			cfObj.commonClick(testSeries_OR.getListOfTestStatus().get(0));

			if (test_StatuString.equalsIgnoreCase("Start Now")) {

				Thread.sleep(2000);

				driver.navigate().back();

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
				if (!result) {
					testseriesMsgList.add("The swipe pop up is not visible after starting the test");
					result = true;
				}

				cfObj.commonClick(testSeries_OR.swipePopUpGotItBtn());

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

				result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestStatus().get(0), 10);
				if (!result) {
					testseriesMsgList.add("The test series status is not visible after pausing the test");
					return result;
				}

				String test_Status = testSeries_OR.getListOfTestStatus().get(0).getText();

				if (!test_Status.equalsIgnoreCase("Resume")) {
					testseriesMsgList.add("The test status should be resume but is " + test_Status);
					return false;
				}

				cfObj.commonClick(testSeries_OR.getListOfTestStatus().get(0));

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

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "toolbar_sub_text", "id", 10);
				if (!result) {
					testseriesMsgList.add("It is not result overview page and not visible ");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "view_solution_container", "id", 10);
				if (!result) {
					testseriesMsgList.add("The solutions button is not visible");
					return result;
				}

				driver.navigate().back();

				result = cfObj.commonWaitForElementToBeVisible(driver, testSeries_OR.getListOfTestStatus().get(0), 5);
				if (!result) {
					testseriesMsgList.add("The test series status is not visible");
					return result;
				}

				String test_StatusFinish = testSeries_OR.getListOfTestStatus().get(0).getText();

				if (!test_StatusFinish.equalsIgnoreCase("Result")) {
					testseriesMsgList.add("The test status should be result but is " + test_Status);
					return false;
				}

			}

		} catch (Exception e) {
			testseriesMsgList.add("verifyFreeTest_Exception " + e.getMessage());
			result = false;
		}
		return result;
	}
}